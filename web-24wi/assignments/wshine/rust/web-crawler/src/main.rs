use reqwest::{Error, Response};
use std::cmp::Ordering;
use std::collections::HashSet;
use std::env;

fn identify_link(line: &str) -> Option<String> {
    let mut link = line
        .split_once("<a href=")
        .unwrap()
        .1
        .split_whitespace()
        .collect::<Vec<&str>>()[0];
    link = link.split(">").next().unwrap();
    if link.len() > 1 {
        link = &link[1..link.len() - 1];
    }

    if link.starts_with("/") {
        //println!("{:?}", link);
        Some(link.to_string())
    } else {
        None
    }
}

fn identify_svg(line: &str) -> Option<String> {
    let mut svg_link = line
        .split_once("src=")
        .unwrap()
        .1
        .split_whitespace()
        .collect::<Vec<&str>>()[0];

    if svg_link.len() > 1 {
        svg_link = &svg_link[1..svg_link.len() - 1];
    }
    if svg_link.ends_with(".svg") {
        Some(svg_link.to_string())
    } else {
        None
    }
}

fn find_term(line: &str, search_term: &str) -> Option<String> {
    // todo: some how extract 5 word context around search term
    if line.contains(search_term) {
        Some(line.to_string())
    } else {
        None
    }
}
async fn validate_response(req: Result<Response, Error>) -> Result<Response, u16> {
    let status: u16;
    if req.is_ok() {
        status = req.as_ref().unwrap().status().as_u16();
    } else {
        status = req.as_ref().err().unwrap().status().unwrap().as_u16();
    }

    match status {
        200 => Ok(req.unwrap()),
        _ => Err(status),
    }
}
fn validate_starting_url(start_url: &str) -> String {
    let slash_count = start_url
        .chars()
        .filter(|c| *c == '/')
        .collect::<Vec<char>>()
        .len();

    match slash_count.cmp(&3) {
        Ordering::Less => panic!(
            "Exiting, url given is probably not a valid url. 
Make sure to include a / at the end."
        ),
        Ordering::Greater => {
            let base_url_end_index = start_url.match_indices("/").nth(2);
            start_url
                .split_at(base_url_end_index.unwrap().0)
                .0
                .to_string()
        }
        Ordering::Equal => {
            if start_url.ends_with(r"/") {
                start_url[0..start_url.len() - 1].to_string()
            } else {
                start_url.to_string()
            }
        }
    }
}
async fn search(res: reqwest::Response, search_term: &str) -> (Vec<String>, Vec<String>) {
    let body_text = res.text().await.expect("err getting text body");
    let mut next_links: Vec<String> = Vec::new();
    let mut matches: Vec<String> = Vec::new();

    for line in body_text.lines() {
        if line.contains("<a href=") {
            let link = identify_link(line);
            if link.is_some() {
                next_links.push(link.unwrap());
            }
        } else if line.contains("<img ") {
            let link = identify_svg(line);
            if link.is_some() {
                println!("svg pushed: {:#?}", link.as_ref().unwrap());
                next_links.push(link.unwrap());
            }
        } else {
            let search_result = find_term(line, search_term);
            if search_result.is_some() {
                matches.push(search_result.unwrap());
            }
        }
    }

    return (matches, next_links);
}

#[derive(Debug)]
struct Options {
    start_url: String,
    search_term: String,
    max_depth: u32,
}

#[derive(Debug)]
struct SearchHits {
    url: String,
    hits: Vec<String>,
}
#[derive(Debug)]
struct ReqError {
    url: String,
    status_code: u16,
}

#[tokio::main]
async fn main() {
    // args: start url, search term, max depth
    let options: Options;
    let args: Vec<String> = env::args().collect();
    match args.len() {
        4.. => {
            options = Options {
                start_url: args[1].to_string(),
                search_term: args[2].to_string(),
                max_depth: args[3].parse().expect("Not a valid number."),
            }
        }
        _ => {
            println!("Using default options.\n");
            options = Options {
                start_url: String::from("https://en.wikipedia.org/wiki/Sankey_diagram"),
                search_term: String::from("albedo"),
                max_depth: 1,
            }
        }
    }

    println!("{:#?}", options.start_url);
    println!("{:#?}", options.search_term);
    println!("{:#?}", options.max_depth);

    let base_url = validate_starting_url(&options.start_url);
    let mut crawl_results: Vec<SearchHits> = Vec::new();
    let mut crawl_errors: Vec<ReqError> = Vec::new();
    let mut crawl_next: Vec<String> = Vec::new();
    let mut visited: HashSet<String> = HashSet::new();
    let mut depth = 0;
    crawl_next.push(options.start_url.to_string());

    while depth <= options.max_depth {
        let mut next_links: Vec<String> = Vec::new();
        for url in &crawl_next {
            let hits: Vec<String>;
            let links: Vec<String>;

            if visited.contains(url) {
                continue;
            }
            println!("{:#?}", url);
            let request = reqwest::get(url).await;

            visited.insert(url.to_string());
            let result = validate_response(request).await;
            match result {
                Ok(req) => (hits, links) = search(req, &options.search_term).await,
                Err(status_code) => {
                    crawl_errors.push(ReqError {
                        url: url.to_string(),
                        status_code,
                    });
                    continue;
                }
            }
            for l in links {
                if !visited.contains(&l) {
                    next_links.push(format!("{}{}", &base_url, l));
                }
            }
            if hits.len() > 0 {
                crawl_results.push(SearchHits {
                    url: url.to_string(),
                    hits: hits.to_vec(),
                });
            }

            //println!("{:#?}", url);
            //println!("{:#?}", hits);
            //println!("{:#?}", next_links);
        }

        crawl_next = next_links;
        println!("{}", depth);
        depth += 1;
    }

    let svg_hits: Vec<&SearchHits> = crawl_results
        .iter()
        .filter(|h| h.url.ends_with(r".svg"))
        .collect();
    println!("Number of urls visited: {}", visited.len());
    println!("Number of hits: {}", crawl_results.len());
    println!("Number of errors: {}", crawl_errors.len());
    println!("svg hits {:#?}", svg_hits);
    println!(
        "visited: {:#?}",
        visited
            .iter()
            .filter(|v| v.ends_with(".svg"))
            .collect::<Vec<_>>()
    );
    println!("{:#?}", crawl_errors);
}

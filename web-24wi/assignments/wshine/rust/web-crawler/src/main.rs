use reqwest;
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
        return Some(link.to_string());
    } else {
        return None;
    }
}

fn find_term(line: &str, search_term: &str) -> Option<String> {
    // todo: some how extract 5 word context around search term
    if line.contains(search_term) {
        return Some(line.to_string());
    } else {
        return None;
    }
}

async fn crawl(
    url: &str,
    search_term: &str,
) -> Result<(Vec<String>, Vec<String>), reqwest::Error> {
    let response = reqwest::get(url).await?;
    let status_code = response.status();
    let body_text = response.text().await?;
    let mut next_links: Vec<String> = Vec::new();
    let mut matches: Vec<String> = Vec::new();

    for line in body_text.lines() {
        if line.contains("<a href=") {
            let link = identify_link(line);
            if link.is_some() {
                next_links.push(link.unwrap());
            }
        } else {
            let search_result = find_term(line, search_term);
            if search_result.is_some() {
                matches.push(search_result.unwrap());
            }
        }
    }

    return Ok((matches, next_links));
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
                start_url: String::from("https://www.w3schools.com"),
                search_term: String::from("Python"),
                max_depth: 3,
            }
        }
    }

    let mut crawl_results: Vec<SearchHits> = Vec::new();
    let mut crawl_next: Vec<String> = Vec::new();
    let mut visited: HashSet<String> = HashSet::new();
    let mut depth = 1;
    crawl_next.push(options.start_url.to_string());

    while depth <= options.max_depth {
        let mut next_links: Vec<String> = Vec::new();
        for url in &crawl_next {
            let hits: Vec<String>;
            let links: Vec<String>;

            println!("{:#?}", url);
            if visited.contains(url) {
                continue;
            }

            let res = crawl(url, &options.search_term).await;
            if res.is_ok() {
                (hits, links) = res.unwrap();
            } else {
                eprint!("request Error: {}", res.err().unwrap());
                continue;
            }

            visited.insert(url.to_string());

            for l in links {
                if !visited.contains(&l) {
                    next_links.push(format!("{}{}", &options.start_url.to_string(), l));
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
    println!("Number of urls visited: {}", visited.len());
    println!("Number of hits: {}", crawl_results.len());

}

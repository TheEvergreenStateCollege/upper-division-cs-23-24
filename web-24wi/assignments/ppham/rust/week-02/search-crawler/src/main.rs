use std::env;
use reqwest::get;

fn get_links(body: &str) -> Vec<&str> {
    let mut results: Vec<&str> = Vec::new();

    // split by the beginning of an html anchor
    println!("Body {body}");
    let link_begins: Vec<&str> = body.split("<a href=\"").collect();
    for link_begin in link_begins {
       let links: Vec<&str> = link_begin.split("\"").collect();
       if links.len() > 0 {
          results.push(links[0]); 
       }
    }
    results
}

// start recursive crawling at the given url bounded by the given depth 
// print out the surround 10 words of
async fn begin_crawl(start_url: &str, search_term: &str, max_depth: u32) {
    // chaining .await will yield our query result
    let result = reqwest::get(start_url).await;

    let response = result.unwrap();

    match response.status() {
        reqwest::StatusCode::OK => {
            let body = response.text().await;
            let body_str: String = body.unwrap();
            let hits = get_links(body_str.as_str());
            println!("success! {:?}", hits);
        },
        reqwest::StatusCode::UNAUTHORIZED => {
            println!("need to grab a new token");
        },
        _ => {
            panic!("uh oh! something unexpected happened.");
        }
    }

}

struct Args<'a> {
    url: &'a str,
    search_term: &'a str,
    max_depth: u32,
}

const DEFAULT_ARGS: Args = Args {
    url: "https://en.wikipedia.org/wiki/antikythera_mechanism",
    search_term: "comput", // "comput" is the stem for "computer", "computation", "compute", etc.
    max_depth: 100,
};

#[tokio::main]
async fn main() {
    // collect args
    
    let args: Vec<String> = env::args().collect();

    let start_url = if args.len() < 2 { DEFAULT_ARGS.url } else { args[1].as_str() };
    let search_term = if args.len() < 3 { DEFAULT_ARGS.search_term } else { args[2].as_str() };
    let max_depth = if args.len() < 4 { DEFAULT_ARGS.max_depth } else { args[3].parse().unwrap() };

    let cmd = args[0].as_str();

    if args.len() < 4 {
        println!("Usage: {cmd} <start_url> <search_query> <max_depth>");
    }
    println!("  Start URL: {start_url}");
    println!("  Search term: {search_term}");
    println!("  Max depth: {max_depth}");
    begin_crawl(start_url, search_term, max_depth).await;
    
}

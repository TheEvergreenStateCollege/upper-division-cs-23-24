use reqwest::get;
use scraper::{Html, Selector};

async fn get_request(url: &str) -> Result<(), reqwest::Error> {
    let response = reqwest::get(url).await?;
    let status_code = response.status();
    let text = response.text().await?;
    let document = Html::parse_document(&text);
    let selector = Selector::parse("a").unwrap();
    println!("{}", status_code);
    for e in document.tree {
        println!("{:#?}", e);
    }
    //for link in document.select(&selector) {
    //    println!("{}", link
    //        .value()
    //        .attr("href")
    //        .expect("href not found")
    //    );
    //}


    Ok(())
}

#[tokio::main]
async fn main() {
    let _ = get_request("https://www.w3schools.com/").await;
}

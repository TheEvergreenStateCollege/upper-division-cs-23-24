#![allow(non_snake_case)]

mod breed;
mod pet;
mod results;
mod search;

use dioxus::prelude::*;
use search::SearchParams;

fn main() {
    dioxus_logger::init(log::LevelFilter::Info).unwrap();
    dioxus_web::launch(App);
}

fn App(cx: Scope) -> Element {
    cx.render(rsx! {
        h1 { "Adopt me!" },
        SearchParams {},
    })
}

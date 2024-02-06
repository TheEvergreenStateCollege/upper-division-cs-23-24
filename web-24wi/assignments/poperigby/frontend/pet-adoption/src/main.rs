#![allow(non_snake_case)]

mod breed;
mod details;
mod pet;
mod results;
mod search;

use details::Details;
use dioxus::prelude::*;
use dioxus_router::prelude::*;
use search::SearchParams;

fn main() {
    dioxus_logger::init(log::LevelFilter::Info).unwrap();
    dioxus_web::launch(App);
}

#[derive(Routable, PartialEq, Debug, Clone)]
pub enum Route {
    #[route("/")]
    SearchParams {},
    #[route("/details:id")]
    Details { id: i64 },
}

fn App(cx: Scope) -> Element {
    cx.render(rsx! {
        h1 { "Adopt me!" },
        Router::<Route> { }
    })
}

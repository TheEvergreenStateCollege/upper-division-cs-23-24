#![allow(non_snake_case)]

mod api;
mod details;
mod pet;
mod results;
mod search;

use crate::api::{QueryError, QueryKeys, QueryValue};
use details::Details;
use dioxus::prelude::*;
use dioxus_query::prelude::*;
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
    #[route("/details/:id")]
    Details { id: usize },
}

fn App(cx: Scope) -> Element {
    use_init_query_client::<QueryValue, QueryError, QueryKeys>(cx);
    let client = use_query_client::<QueryValue, QueryError, QueryKeys>(cx);

    cx.render(rsx! {
        div {
            header {
                h1 {
                    "Adopt me!"
                },
            },
            main {
                Router::<Route> { }
            },
        }
    })
}

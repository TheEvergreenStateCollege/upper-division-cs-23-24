#![allow(non_snake_case)]

mod api;
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
    #[route("/details/:id")]
    Details { id: i64 },
}

fn App(cx: Scope) -> Element {
    cx.render(rsx! {
        div {
            class: "p-0 m-0",
            background: "url(http://pets-images.dev-apis.com/pets/wallpaperA.jpg)",
            header {
                class: "w-full mb-10 text-center p-7 bg-gradient-to-b from-yellow-400 via-orange-500 to-red-500",
                h1 { 
                    class: "text-6xl text-white hover:text-gray-200",
                    "Adopt me!"
                },
            },
            main {
                Router::<Route> { }
            },
        }
    })
}

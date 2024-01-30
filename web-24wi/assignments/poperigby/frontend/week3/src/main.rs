#![allow(non_snake_case)]

mod search_params;

use dioxus::prelude::*;
use search_params::SearchParams;

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

#[derive(PartialEq, Props)]
struct PetProps<'a> {
    // The 'a specifies that every str will live as long as the PetProps struct
    name: &'a str,
    animal: &'a str,
    breed: &'a str,
}

#[component]
fn Pet<'a>(cx: Scope<'a, PetProps<'a>>) -> Element {
    cx.render(rsx! {
        div {
            h1 { "{cx.props.name}" }
            h2 { "{cx.props.animal}" }
            h2 { "{cx.props.breed}" }
        }
    })
}

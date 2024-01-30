#![allow(non_snake_case)]

use dioxus::prelude::*;

fn main() {
    dioxus_logger::init(log::LevelFilter::Info).unwrap();
    dioxus_web::launch(App);
}

fn App(cx: Scope) -> Element {
    // Setup a state variable with a default value
    let name = use_state(cx, || "John Doe".to_string());

    cx.render(rsx! {
        input {
            value: "{name}",
        },
        button {
            onclick: |event| log::info!("Clicked! {event:?}"),
            "Click me ( ͡° ͜ʖ ͡°)"
        }
    })
}

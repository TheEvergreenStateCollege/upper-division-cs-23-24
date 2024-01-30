#![allow(non_snake_case)]

use dioxus::prelude::*;
use dioxus_tutorial::{Preview, PreviewState, Stories};

fn main() {
    dioxus_web::launch(App);
}

fn App(cx: Scope) -> Element {
    // A piece of state that gets shared down the hierarchy
    use_shared_state_provider(cx, || PreviewState::Unset);

    cx.render(rsx! {
        div {
            display: "flex",
            flex_direction: "row",
            width: "100%",
            div {
                width: "50%",
                Stories {}
            }
            div {
                width: "50%",
                Preview {}
            }
        }
    })
}

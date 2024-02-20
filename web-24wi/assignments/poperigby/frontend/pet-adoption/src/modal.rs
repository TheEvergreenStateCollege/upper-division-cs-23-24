use dioxus::prelude::*;

pub fn Modal(cx: Scope) -> Element {
    cx.render(rsx! {
        div {
            class: "modal-background",
            div {
                class: "modal-dialog",
                p {
                    "Are you sure you want to adopt this pet?",
                },
                div {
                    class: "modal-buttons",
                    button {
                        "Cancel",
                    },
                    button {
                        "Okay"
                    }
                }
            }
        }
    })
}

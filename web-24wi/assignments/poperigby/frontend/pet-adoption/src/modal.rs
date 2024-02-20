use dioxus::prelude::*;

pub fn Modal(cx: Scope) -> Element {
    cx.render(rsx! {
        div {
            class: "modal-background",
            div {
                class: "modal-dialog",
                button {
                    "Cancel",
                },
                button {
                    "Okay"
                }
            }
        }
    })
}

use dioxus::prelude::*;

#[derive(PartialEq, Props)]
pub struct ModalProps {
    pet_name: String,
}

pub fn Modal(cx: Scope<ModalProps>) -> Element {
    cx.render(rsx! {
        div {
            class: "modal-background",
            div {
                class: "modal-dialog",
                p {
                    "Are you sure you want to adopt {cx.props.pet_name}?",
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

use dioxus::prelude::*;

#[derive(PartialEq, Props)]
pub struct ModalProps<'a> {
    pet_name: String,
    modal_active: &'a UseState<bool>,
}

pub fn Modal<'a>(cx: Scope<'a, ModalProps<'a>>) -> Element {
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
                        onclick: |_| cx.props.modal_active.set(false),
                        "No",
                    },
                    button {
                        onclick: |_| cx.props.modal_active.set(false),
                        "Yes"
                    }
                }
            }
        }
    })
}

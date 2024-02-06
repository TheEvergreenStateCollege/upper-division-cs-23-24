use crate::pet::{Pet, PetItem};
use dioxus::prelude::*;

#[derive(PartialEq, Props)]
pub struct ResultsProps<'a> {
    pub pets: &'a [PetItem],
}

pub fn Results<'a>(cx: Scope<'a, ResultsProps<'a>>) -> Element {
    cx.render(rsx! {
        div {
            class: "search",
            if cx.props.pets.is_empty() {
                rsx! {
                    h1 {
                        "No pets found ;("
                    }
                }
            } else {
                rsx! {
                    for pet in cx.props.pets {
                        Pet { name: &pet.name, animal: &pet.animal, breed: &pet.breed  }
                    }
                }
            }
        }
    })
}

use crate::{api::PetItem, pet::Pet};
use dioxus::prelude::*;

#[derive(PartialEq, Props)]
pub struct ResultsProps<'a> {
    pub pets: &'a [PetItem],
}

pub fn Results<'a>(cx: Scope<'a, ResultsProps<'a>>) -> Element {
    cx.render(rsx! {
        div {
            class: "grid gap-4 grid-cols-1 sm:grid-cols-2 lg:grid-cols-3",
            if cx.props.pets.is_empty() {
                rsx! {
                    h1 {
                        "No pets found ;("
                    }
                }
            } else {
                rsx! {
                    for pet in cx.props.pets {
                        Pet {
                            id: pet.id,
                            name: &pet.name,
                            animal: &pet.animal,
                            city: &pet.city,
                            state: &pet.state,
                            description: &pet.description,
                            breed: &pet.breed,
                            images: pet.images.clone(),
                        }
                    }
                }
            }
        }
    })
}

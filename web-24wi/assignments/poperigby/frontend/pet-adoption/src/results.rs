use crate::pet::{Pet, PetItem};
use dioxus::prelude::*;

#[derive(PartialEq, Props)]
pub struct ResultsProps<'a> {
    pub pets: &'a [PetItem],
}

pub fn Results<'a>(cx: Scope<'a, ResultsProps<'a>>) -> Element {
    cx.render(rsx! {
        for pet in cx.props.pets {
            Pet { name: &pet.name, animal: &pet.animal, breed: &pet.breed  }
        }
    })
}

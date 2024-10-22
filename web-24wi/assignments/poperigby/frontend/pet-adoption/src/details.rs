use crate::{
    api::{fetch_pet, QueryKeys, QueryValue},
    modal::Modal,
};
use cruet::Inflector;
use dioxus::prelude::*;
use dioxus_query::prelude::*;

#[derive(PartialEq, Props)]
pub struct DetailsProps {
    id: usize,
}

pub fn Details(cx: Scope<DetailsProps>) -> Element {
    let pet = use_query(cx, || vec![QueryKeys::Pet(cx.props.id)], fetch_pet);
    let modal_active = use_state(cx, || false);

    let render = if let QueryResult::Ok(QueryValue::PetItem(p)) = pet.result().value() {
        log::info!("{:?}", p);
        cx.render(rsx! {
            div {
                class: "details",
                ImageCarousel {
                    images: p.images.clone()
                }
                h1 {
                    p.name.clone()
                },
                h2 {
                    "{p.animal.to_sentence_case()} — {p.breed} — {p.city}, {p.state}"
                },
                button {
                    onclick: |_| {
                        modal_active.set(true)
                    },
                    "Adopt {p.name}"
                },
                p {
                    "{p.description}"
                },
                if **modal_active {
                    rsx! {
                        Modal {
                            pet_name: p.name.clone(),
                            modal_active: modal_active,
                        }
                    }
                }
            }
        })
    } else {
        cx.render(rsx! {
            h1 {
                "Uh, oh!"
            }
        })
    };

    render
}

#[derive(PartialEq, Props)]
pub struct ImageCarouselProps {
    #[props(default = vec!["http://pets-images.dev-apis.com/pets/none.jpg".to_string()])]
    images: Vec<String>,
}

fn ImageCarousel(cx: Scope<ImageCarouselProps>) -> Element {
    let active = use_state(cx, || 0);

    cx.render(rsx! {
        div {
            class: "carousel",
            img {
                src: "{cx.props.images.get(*active.get()).unwrap()}",
                alt: "Animal",
            },
            div {
                class: "carousel-smaller",
                for (index, photo) in cx.props.images.iter().enumerate() {
                    img {
                        class: if *active.get() == index { "active" } else { "" },
                        key: "{photo}",
                        src: "{photo}",
                        alt: "Animal Thumbnail",
                        onclick: move |_| {
                            active.set(index);
                        },
                    }
                }
            }
        }
    })
}

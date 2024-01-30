#![allow(non_snake_case)]

use dioxus::prelude::*;

static ANIMALS: &[&str] = &["Bird", "Cat", "Dog", "Rabbit", "Reptile"];

fn main() {
    dioxus_logger::init(log::LevelFilter::Info).unwrap();
    dioxus_web::launch(App);
}

fn App(cx: Scope) -> Element {
    cx.render(rsx! {
        h1 { "Adopt me!" },
        SearchParams {},
    })
}

#[derive(PartialEq, Props)]
struct PetProps<'a> {
    // The 'a specifies that every str will live as long as the PetProps struct
    name: &'a str,
    animal: &'a str,
    breed: &'a str,
}

#[component]
fn Pet<'a>(cx: Scope<'a, PetProps<'a>>) -> Element {
    cx.render(rsx! {
        div {
            h1 { "{cx.props.name}" }
            h2 { "{cx.props.animal}" }
            h2 { "{cx.props.breed}" }
        }
    })
}

#[component]
fn SearchParams(cx: Scope) -> Element {
    let location = use_state(cx, || "".to_string());
    let animal = use_state(cx, || "".to_string());
    let breed = use_state(cx, || "".to_string());
    let breeds = ["Mutt"];

    cx.render(rsx! {
        div {
            class: "search-params",
            form {
                label {
                    r#for: "location",
                    "Location ",
                    input {
                        id: "location",
                        value: "{location}",
                        placeholder: "Location",
                        onchange: |event| location.set(event.value.clone())
                    },
                },
                label {
                    r#for: "animal",
                    "Animal ",
                    select {
                        id: "animal",
                        value: "{animal}",
                        onchange: |event| {
                            animal.set(event.value.clone());
                            breed.set("".to_string());
                        },
                        for animal in ANIMALS {
                            option {
                                value: "{animal}",
                                "{animal}"
                            }
                        },
                    }
                },
                label {
                    r#for: "breed",
                    "Breed ",
                    select {
                        id: "breed",
                        disabled: !breeds.len() as i64,
                        value: "{breed}",
                        onchange: |event| breed.set(event.value.clone()),
                        for breed in breeds {
                            option {
                                value: "{breed}",
                                "{breed}"
                            }
                        }
                    }
                }
                button { "Submit" },
            }
        }
    })
}

use dioxus::prelude::*;
use serde::{Deserialize, Serialize};

static ANIMALS: &[&str] = &["Bird", "Cat", "Dog", "Rabbit", "Reptile"];

#[component]
pub fn SearchParams(cx: Scope) -> Element {
    let animal = use_state(cx, || "".to_string());
    let location = use_state(cx, || "".to_string());
    let breed = use_state(cx, || "".to_string());

    let breeds = ["Mutt"];

    let pets = use_future(cx, (), |_| {
        request_pets(animal.to_string(), location.to_string(), breed.to_string())
    });

    cx.render(rsx! {
        div {
            class: "search-params",
            form {
                label {
                    r#for: "location",
                    "Location",
                    input {
                        id: "location",
                        value: "{location}",
                        placeholder: "Location",
                        onchange: |event| location.set(event.value.clone())
                    },
                },
                label {
                    r#for: "animal",
                    "Animal",
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
                    "Breed",
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
        match pets.value() {
            Some(Ok(list)) => {
                rsx! { h1 { "HELLO" } }
            },
            Some(Err(err)) => {
                rsx! { "An error occurred while fetching pets: {err}" }
            },
            None => {
                rsx! { "Loading pets..." }
            }
        }
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

async fn request_pets(
    animal: String,
    location: String,
    breed: String,
) -> Result<Vec<PetItem>, reqwest::Error> {
    let url = format!(
        "http://pets-v2.dev-apis.com/pets?animal={animal}&location={location}&breed={breed}"
    );

    Ok(reqwest::get(url).await?.json::<PetsData>().await?.pets)
}

#[derive(Clone, Debug, PartialEq, Serialize, Deserialize)]
struct PetsData {
    numberOfResults: i64,
    startIndex: i64,
    endIndex: i64,
    hasNext: bool,
    pets: Vec<PetItem>,
}

#[derive(Clone, Debug, PartialEq, Serialize, Deserialize)]
struct PetItem {
    id: i64,
    name: String,
    animal: String,
    city: String,
    description: String,
    breed: String,
    images: Vec<String>,
}

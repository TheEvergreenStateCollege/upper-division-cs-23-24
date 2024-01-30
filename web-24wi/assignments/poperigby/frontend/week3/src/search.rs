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
    })
}

async fn request_pets(
    animal: String,
    location: String,
    breed: String,
) -> Result<PetItem, reqwest::Error> {
    let url = format!(
        "http://pets-v2.dev-apis.com/pets?animal=${animal}&location=${location}&breed=${breed}"
    );
    reqwest::get(url).await?.json().await
}

#[derive(Clone, Debug, PartialEq, Serialize, Deserialize)]
struct PetItem {
    id: i64,
    name: String,
    animail: String,
    city: String,
    description: String,
    breed: String,
    images: Vec<String>,
}

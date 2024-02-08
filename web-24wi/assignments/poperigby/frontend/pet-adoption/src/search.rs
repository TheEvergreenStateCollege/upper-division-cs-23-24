use crate::{
    api::{request_breed_list, request_pets},
    results::Results,
};
use dioxus::prelude::*;

static ANIMALS: &[&str] = &["Bird", "Cat", "Dog", "Rabbit", "Reptile"];

#[component]
pub fn SearchParams(cx: Scope) -> Element {
    let animal = use_state(cx, || "".to_string());
    let location = use_state(cx, || "".to_string());
    let breed = use_state(cx, || "".to_string());

    let pets = use_future(cx, (), |_| {
        request_pets(animal.to_string(), location.to_string(), breed.to_string())
    });
    let breeds = use_future(cx, (animal,), |(animal,)| {
        request_breed_list(animal.to_string().to_lowercase())
    });

    cx.render(rsx! {
        div {
            class: "my-0 mx-auto w-11/12",
            form {
                class: "p-10 mb-10 rounded-lg bg-gray-200 shadow-lg flex flex-col justify-center items-center",
                // Check the API for new pets when we press submit in the search form
                onsubmit: |_| {
                    pets.restart();
                },
                label {
                    r#for: "location",
                    "Location: ",
                    input {
                        id: "location",
                        class: "search-input",
                        r#type: "text",
                        value: "{location}",
                        placeholder: "Location",
                        onchange: |event| location.set(event.value.clone())
                    },
                },
                label {
                    r#for: "animal",
                    "Animal: ",
                    select {
                        id: "animal",
                        class: "search-input",
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
                    "Breed: ",
                    select {
                        class: "search-input grayed-out-disabled",
                        id: "breed",
                        onchange: |event| {
                            breed.set(event.value.clone());
                        },
                        match breeds.value() {
                            Some(Ok(list)) => {
                                log::info!("{:?}", list.first());
                                rsx! {
                                    for breed in list {
                                        option {
                                            value: "{breed}",
                                            "{breed}"
                                        }
                                    }
                                }
                            },
                            Some(Err(err)) => {
                                // TODO: Handle case where animal is empty. Don't log an error in
                                // that case.
                                log::error!("Error loading breeds: {err}");
                                rsx! {
                                    option {
                                        ""
                                    }
                                }
                            },
                            None => {
                                // TODO: Disabled it in this case
                                rsx! {
                                    option {
                                        ""
                                    }
                                }
                            },
                        },
                        // disabled: !breeds.len() as i64,
                    }
                }
                button { 
                    class: "rounded px-6 py-2 text-white hover:opacity-50 border-none bg-orange-500",
                    "Submit"
                },
            }
            match pets.value() {
                Some(Ok(list)) => {
                    rsx! {
                        Results { pets: list }
                    }
                },
                Some(Err(err)) => {
                    rsx! {
                        h1 {
                            "An error occurred while fetching pets: {err}"
                        }
                    }
                },
                None => {
                    rsx! {
                        h1 {
                            "Loading pets..."
                        }
                    }
                }
            }
        }
    })
}

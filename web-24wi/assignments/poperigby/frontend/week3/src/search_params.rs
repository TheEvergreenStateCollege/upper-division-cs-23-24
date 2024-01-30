use dioxus::prelude::*;

static ANIMALS: &[&str] = &["Bird", "Cat", "Dog", "Rabbit", "Reptile"];

#[component]
pub fn SearchParams(cx: Scope) -> Element {
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

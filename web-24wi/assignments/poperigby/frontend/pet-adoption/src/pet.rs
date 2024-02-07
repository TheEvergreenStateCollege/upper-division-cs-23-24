use super::Route;
use cruet::Inflector;
use dioxus::prelude::*;
use dioxus_router::prelude::*;

#[derive(PartialEq, Props)]
pub struct PetProps<'a> {
    // The 'a specifies that every str will live as long as the PetProps struct
    pub id: i64,
    pub name: &'a str,
    pub animal: &'a str,
    pub city: &'a str,
    pub state: &'a str,
    pub description: &'a str,
    pub breed: &'a str,
    // TODO: This feels icky
    pub images: Vec<String>,
}

#[component]
pub fn Pet<'a>(cx: Scope<'a, PetProps<'a>>) -> Element {
    let mut hero = "http://pets-images.dev-apis.com/pets/none.jpg";
    if !cx.props.images.is_empty() {
        hero = &cx.props.images[0];
    }

    let animal = cx.props.animal.to_sentence_case();
    let location = format!("{}, {}", cx.props.city, cx.props.state);

    cx.render(rsx! {
        Link {
            // Link this Pet component to its appropriate details page, using
            // the current Pet's `id` as the page Details page `id`.
            to: Route::Details { id: cx.props.id },
            class: "pet",
            div {
                class: "image-container",
                img {
                    src: hero,
                    alt: cx.props.name,
                }
            },
            div {
                class: "info",
                h1 {
                    cx.props.name
                },
                h2 {
                    "{animal} - {cx.props.breed} - {location}"
                }
            }
        }
    })
}

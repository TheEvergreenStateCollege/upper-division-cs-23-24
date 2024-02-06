use super::Route;
use dioxus::prelude::*;
use dioxus_router::prelude::*;
use serde::{Deserialize, Serialize};

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

    let location = format!("{}, {}", cx.props.city, cx.props.state);

    cx.render(rsx! {
        Link {
            // Link this Pet component to its appropriate details page
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
                    "{cx.props.animal} - {cx.props.breed} - {location}"
                }
            }
        }
    })
}

pub async fn request_pets(
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
pub struct PetItem {
    pub id: i64,
    pub name: String,
    pub animal: String,
    pub city: String,
    pub state: String,
    pub description: String,
    pub breed: String,
    pub images: Vec<String>,
}

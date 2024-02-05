use dioxus::prelude::*;
use serde::{Deserialize, Serialize};

#[derive(PartialEq, Props)]
pub struct PetProps<'a> {
    // The 'a specifies that every str will live as long as the PetProps struct
    pub name: &'a str,
    pub animal: &'a str,
    pub breed: &'a str,
}

#[component]
pub fn Pet<'a>(cx: Scope<'a, PetProps<'a>>) -> Element {
    cx.render(rsx! {
        div {
            h1 { "{cx.props.name}" }
            h2 { "{cx.props.animal}" }
            h2 { "{cx.props.breed}" }
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
    pub description: String,
    pub breed: String,
    pub images: Vec<String>,
}

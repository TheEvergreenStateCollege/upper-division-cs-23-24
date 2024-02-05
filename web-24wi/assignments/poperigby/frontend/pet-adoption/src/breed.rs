use dioxus::prelude::*;
use serde::{Deserialize, Serialize};

#[derive(Clone, Debug, PartialEq, Serialize, Deserialize)]
struct BreedList {
    animal: String,
    breeds: Vec<String>,
}

pub async fn request_breed_list(animal: String) -> Result<Vec<String>, reqwest::Error> {
    let url = format!("http://pets-v2.dev-apis.com/breeds?animal={animal}");

    Ok(reqwest::get(url).await?.json::<BreedList>().await?.breeds)
}

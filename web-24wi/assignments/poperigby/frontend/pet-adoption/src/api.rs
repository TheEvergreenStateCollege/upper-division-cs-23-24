use serde::{Deserialize, Serialize};

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

pub async fn request_breed_list(animal: String) -> Result<Vec<String>, reqwest::Error> {
    let url = format!("http://pets-v2.dev-apis.com/breeds?animal={animal}");

    Ok(reqwest::get(url).await?.json::<BreedList>().await?.breeds)
}

#[derive(Clone, Debug, PartialEq, Serialize, Deserialize)]
struct BreedList {
    animal: String,
    breeds: Vec<String>,
}

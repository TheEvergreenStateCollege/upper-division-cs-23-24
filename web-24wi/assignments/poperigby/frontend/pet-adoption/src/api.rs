use dioxus_query::prelude::*;
use serde::{Deserialize, Serialize};

#[derive(Clone, PartialEq, Eq, Hash)]
pub enum QueryKeys {
    Pet(usize),
}

#[derive(Clone, PartialEq, Eq, Hash, Debug)]
pub enum QueryError {
    PetNotFound(usize),
    Unknown,
}

#[derive(Clone, PartialEq, Eq, Hash, Debug)]
pub enum QueryValue {
    PetItem(PetItem),
}

pub async fn fetch_pet(keys: Vec<QueryKeys>) -> QueryResult<QueryValue, QueryError> {
    if let Some(QueryKeys::Pet(id)) = keys.first() {
        let url = format!("http://pets-v2.dev-apis.com/pets?id={id}");
        if let Some(pet) = reqwest::get(url)
            .await
            .expect("Failed to make request")
            .json::<PetsData>()
            .await
            .expect("Failed to deserialize")
            .pets
            .first()
        {
            QueryResult::Ok(QueryValue::PetItem(pet.clone()))
        } else {
            QueryResult::Err(QueryError::PetNotFound(*id))
        }
    } else {
        QueryResult::Err(QueryError::Unknown)
    }
}

pub async fn request_pets(
    animal: String,
    location: String,
    breed: String,
) -> Result<Vec<PetItem>, reqwest::Error> {
    let (animal, location, breed) = (
        animal.to_lowercase().replace(' ', "_"),
        location.to_lowercase().replace(' ', "_"),
        // I guess breed is uppercase and has spaces :\
        breed,
    );

    let url = format!(
        "http://pets-v2.dev-apis.com/pets?animal={animal}&location={location}&breed={breed}"
    );
    log::info!("{}", url);

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

#[derive(Clone, Debug, PartialEq, Serialize, Deserialize, Hash, Eq)]
pub struct PetItem {
    pub id: usize,
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

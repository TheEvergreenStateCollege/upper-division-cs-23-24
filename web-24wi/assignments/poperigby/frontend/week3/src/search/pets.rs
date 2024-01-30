use serde::{Deserialize, Serialize};

#[derive(Clone, Debug, PartialEq, Serialize, Deserialize)]
pub struct PetItem {
    id: i64,
    name: String,
    animail: String,
    city: String,
    description: String,
    breed: String,
    images: Vec<String>,
}

async fn request_pets(
    animal: &str,
    location: &str,
    breed: &str,
) -> Result<PetItem, reqwest::Error> {
    let url = format!(
        "http://pets-v2.dev-apis.com/pets?animal=${animal}&location=${location}&breed=${breed}"
    );
    reqwest::get(url).await?.json().await
}

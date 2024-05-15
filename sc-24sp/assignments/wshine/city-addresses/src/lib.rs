pub mod city_drawer;

use crate::city_drawer::{Road};

pub trait CityAddresser {
    fn build(roads: &Vec<Road>) -> Self;
    fn get_address_string(x: usize, y: usize) -> String;
}

#[cfg]
mod test {

    #[test]
    fn olympia_address() {

    }
}
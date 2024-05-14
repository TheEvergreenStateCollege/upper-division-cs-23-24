#![allow(unused)]
use city_addresses::city::*;

fn main() {
    println!("Hello, city!");

    let city = City::new();

    println!("{}", city);

    println!("{:#?}", city.addresses.len());
}

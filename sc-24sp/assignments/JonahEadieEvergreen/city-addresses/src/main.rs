pub mod city_drawer;

use crate::city_drawer::*;

fn main() {
    println!("Hello, city!");
    let mut roads: Vec::<Road> = gen_random_roads(Direction::Vertical);
    roads.append(&mut gen_random_roads(Direction::Horizontal));

    let mut addresses: Vec::<Address> = gen_random_addresses(&roads, 30);
    
    city_drawer(&mut roads, &mut addresses);
}

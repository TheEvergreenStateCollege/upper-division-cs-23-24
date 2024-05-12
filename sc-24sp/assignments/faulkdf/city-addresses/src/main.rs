use rand::Rng;

pub mod city_drawer;

use crate::city_drawer::{city_drawer, city_builder, Address, AddressesMap, Road, RoadDirection, BOUND};
use std::collections::HashMap;

// Can we have a "parent type" to Avenue and Street,
// let's call it Road
fn gen_random_roads(bound: usize, direction: RoadDirection) -> Vec<Road> {
    let mut directional_roads = Vec::<Road>::new();
    let mut w = 0;
    loop {
        let next_w = rand::thread_rng().gen_range(3..10);
        w += next_w;
        
        // check if we've exceeded the bound
        if w >= bound {
            break;
        }
        let new_road = Road {coord: w, direction: direction };
        println!("Added {}", &new_road);
        directional_roads.push(new_road);
        

    }   
    directional_roads
}


fn main() {
    let unaddress: &String = &String::from("Unaddressed");

    println!("Hello, city!");
    let size = 50;

    let mut addresses = AddressesMap::new();

    let mut roads = gen_random_roads(size, RoadDirection::NorthSouth);

    roads.extend(gen_random_roads(size, RoadDirection::EastWest));

    let mut grid = [['.'; BOUND]; BOUND];

    city_builder(&mut addresses, &mut grid, &mut roads);

    println!("The number of generated addresses is {}", addresses.len());

    let mut count = 0;
    for (key,value) in &addresses {
        if count >= 5 {
            break;
        }
        let address_string = addresses.get(&key).unwrap_or(unaddress);
        println!("The address at coordinates {:?} is {} ", key, address_string);
        count += 1;
    }


    city_drawer(&grid);


}

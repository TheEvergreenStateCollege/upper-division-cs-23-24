use rand::Rng;
use std::collections::HashMap;

pub mod city_drawer;

use crate::city_drawer::*;

type AddressMap = HashMap::<(usize,usize),String>;

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
        directional_roads.push(Road {coord: w, direction: direction });
        println!("Added Road {:?}", w);

    }   
    directional_roads
}

fn addresses_matrix (addresses: &AddressMap) -> Vec<Vec<String>> {
    let mut matrix = vec![vec!["".into(); BOUND]; BOUND];

    for a in addresses {
        matrix[a.0.0][a.0.1] = a.1.clone();
    }

    return matrix;
}

fn main() {
    println!("Hello, city!");
    let size = 50;

    let mut roads = gen_random_roads(size, RoadDirection::NorthSouth);

    roads.extend(gen_random_roads(size, RoadDirection::EastWest));

    let mut grid = [['.'; BOUND]; BOUND];

    let mut addresses = AddressMap::new();

    city_builder(&mut grid, &mut roads, &mut addresses);

    city_drawer(&grid);

    let matrix = addresses_matrix(&addresses);

    let mut num_addresses = 0;
    let mut num_streets = 0;
    let mut num_avenues = 0;

    for i in 0..BOUND {
        for j in 0..BOUND {
            if matrix[i][j] == "" {
                // No address here
                continue;
            }

            num_addresses += 1;
            match matrix[i][j].split(' ').nth(1).unwrap() {
                "Avenue" => { num_avenues += 1; },
                "Street" => { num_streets += 1; },
                _ => { panic!(); /* TODO: replace this with something sensible */ }
            };

            println!("{}. ({},{}): {}", num_addresses, i, j, matrix[i][j]);
        }
    }

    print!("Number of addresses: {}\n\
            Number of avenues:   {}\n\
            Number of streets:   {}\n",
            num_addresses, num_avenues, num_streets);


}

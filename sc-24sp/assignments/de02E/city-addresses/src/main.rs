use rand::Rng;

pub mod city_drawer;

use crate::city_drawer::{Road,Address,city_drawer,BOUND};

// Can we have a "parent type" to Avenue and Street,
// let's call it Road
fn gen_random_roads(bound: usize) -> Vec<Road> {
    let mut directional_roads = Vec::<Road>::new();
    let mut w = 0;
    loop {
        let next_w = rand::thread_rng().gen_range(3..10);
        w += next_w;

        // check if we've exceeded the bound
        if w >= bound {
            break;
        }
        directional_roads.push(Road {x: w});
        println!("Added Road {:?}", w);
        

    }   
    directional_roads
}

fn main() {
    println!("Hello, city!");
    let size = 50;

    let mut n_s_avenues = gen_random_roads(size);

    let mut e_w_streets = gen_random_roads(size);

    city_drawer(&mut n_s_avenues, &mut e_w_streets);
}
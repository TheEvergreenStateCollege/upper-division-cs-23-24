use city_drawer::{Road, RoadDirection};
use rand::Rng;

pub mod city_drawer;

use crate::city_drawer::{city_drawer, HEIGHT, WIDTH};

fn main() {
    println!("Hello, city!");
    let mut n_s_avenues = create_roads(RoadDirection::Vertical, HEIGHT);

    let mut e_w_streets = create_roads(RoadDirection::Horizontal, WIDTH);

    city_drawer(&mut n_s_avenues, &mut e_w_streets);
}

fn create_roads(direction: RoadDirection, bound: usize) -> Vec<Road> {
    let mut roads: Vec<Road> = vec![];
    let mut current_size = 0;
    loop {
        let location = rand::thread_rng().gen_range(3..10);
        current_size += location;

        if current_size >= bound {
            break;
        }

        roads.push(Road {
            direction,
            location: current_size,
        })
    }

    roads
}

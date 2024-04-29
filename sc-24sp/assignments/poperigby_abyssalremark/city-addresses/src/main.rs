use city_drawer::{Road, RoadDirection};
use rand::Rng;

mod city;
pub mod city_drawer;

use crate::city_drawer::city_drawer;

fn main() {
    println!("Hello, city!");

    let width = 50;
    let height = 50;

    let n_s_avenues = create_roads(RoadDirection::Vertical, height);
    let e_w_streets = create_roads(RoadDirection::Horizontal, width);

    city_drawer(&n_s_avenues, &e_w_streets, width, height);
}

fn create_roads(direction: RoadDirection, bound: usize) -> Vec<Road> {
    let mut roads: Vec<Road> = vec![];
    let mut current_location = 0;
    loop {
        let offset = rand::thread_rng().gen_range(3..10);
        current_location += offset;

        if current_location >= bound {
            break;
        }

        roads.push(Road {
            direction,
            location: current_location,
        })
    }

    roads
}

use rand::Rng;

pub mod city_drawer;

use crate::city_drawer::{
    city_drawer, AddressAvenue, AddressStreet, Avenue, Street, HEIGHT, WIDTH,
};

fn main() {
    println!("Hello, city!");
    let mut n_s_avenues = Vec::<Avenue>::new();
    let mut i = 0;
    loop {
        let next_x = rand::thread_rng().gen_range(1..10);
        i += next_x;

        if i >= WIDTH {
            break;
        }
        n_s_avenues.push(Avenue { x: i });
        println!("Added avenue {:?}", i);
    }

    let mut e_w_streets = Vec::<Street>::new();
    let mut j = 0;
    loop {
        let next_y = rand::thread_rng().gen_range(0..10);
        j += next_y;

        if j >= HEIGHT {
            break;
        }
        e_w_streets.push(Street { y: j });
        println!("Added street {:?}", j);
    }
    city_drawer(&mut n_s_avenues, &mut e_w_streets);
}

#[derive(Clone, Copy)]
enum RoadDirection {
    Vertical,
    Horizontal,
}

struct Road {
    direction: RoadDirection,
    location: u8,
}

fn create_roads(direction: RoadDirection, bound: u8) -> Vec<Road> {
    let mut roads: Vec<Road> = vec![];
    let mut total_size = 0;
    while total_size < bound {
        let location = rand::thread_rng().gen_range(3..10);
        total_size += location;

        roads.push(Road {
            direction,
            location,
        })
    }

    roads
}

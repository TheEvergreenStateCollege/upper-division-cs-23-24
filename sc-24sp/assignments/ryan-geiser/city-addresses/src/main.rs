use rand::Rng;

pub mod city_drawer;

use crate::city_drawer::{Avenue, Street, city_drawer, WIDTH, HEIGHT};

pub struct AddressAvenue {
    pub x: usize,
}

pub struct AddressStreet {
    pub y: usize,
}

pub fn generate_city_layout(width: usize, height: usize) -> (Vec<Avenue>, Vec<Street>) {
    let mut n_s_avenues = Vec::<Avenue>::new();
    let mut e_w_streets = Vec::<Street>::new();

    let mut i = 0;
    while i < width {
        let next_x = rand::thread_rng().gen_range(1..10); // Adjust range as needed
        i += next_x;
        if i < width {
            n_s_avenues.push(Avenue { x: i });
        }
    }

    let mut j = 0;
    while j < height {
        let next_y = rand::thread_rng().gen_range(1..10); // Adjust range as needed
        j += next_y;
        if j < height {
            e_w_streets.push(Street { y: j });
        }
    }

    (n_s_avenues, e_w_streets)
}

fn main() {
    println!("Hello, city!");
    let (mut n_s_avenues, mut e_w_streets) = generate_city_layout(WIDTH, HEIGHT);
    city_drawer(&mut n_s_avenues, &mut e_w_streets);
}

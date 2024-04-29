use rand::Rng;

pub mod city_drawer;

use crate::city_drawer::{city_drawer, draw_grid, Address, Grid, Road, RoadDirection, BOUND};

// Can we have a "parent type" to Avenue and Street,
// let's call it Road
fn gen_random_roads(bound: usize, ctor: fn(usize) -> Road) -> Vec<Road> {
    let mut directional_roads = Vec::<Road>::new();
    let mut w = 0;
    loop {
        let next_w = rand::thread_rng().gen_range(3..10);
        w += next_w;

        // check if we've exceeded the bound
        if w >= bound {
            break;
        }
        directional_roads.push(ctor(w));
        println!("Added Road {:?}", w);
    }
    directional_roads
}

fn main() {
    println!("Hello, city!");
    let size = 50;

    let n_s_avenues = gen_random_roads(size, |x| Road {
        road_type: RoadDirection::NorthSouth,
        coord: x,
    });

    let e_w_streets = gen_random_roads(size, |y: usize| Road {
        road_type: RoadDirection::EastWest,
        coord: y,
    });

    let mut grid: Grid = [['.'; BOUND]; BOUND];

    grid = *city_drawer(&mut grid, &n_s_avenues);
    grid = *city_drawer(&mut grid, &e_w_streets);

    draw_grid(grid);
}

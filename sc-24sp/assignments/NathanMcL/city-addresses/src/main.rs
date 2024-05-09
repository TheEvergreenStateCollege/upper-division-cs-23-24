use rand::Rng;

pub mod city_drawer;

use crate::city_drawer::{Road,RoadDirection,Grid,draw_grid,city_drawer,BOUND};

// Can we have a "parent type" to Avenue and Street,
// let's call it Road
fn gen_random_roads(bound: usize, road_type: RoadDirection) -> Vec<Road> {
    let mut directional_roads = Vec::<Road>::new();
    let mut w = 0;
    while w < bound {
        let next_w = rand::thread_rng().gen_range(3..10);
        w += next_w;
        
        // check if we've exceeded the bound
        if w < bound {
            let new_road = Road { coord: w, road_type: road_type.clone() };
            println!("Added {}", &new_road);
            directional_roads.push(new_road);
            
        }
        
    }   
    directional_roads
}

fn main() {
    println!("Hello, city!");
    let size = 50;

    let n_s_avenues = gen_random_roads(size, RoadDirection::NorthSouth);
    
    let e_w_streets = gen_random_roads(size, RoadDirection::EastWest); 
    
    let mut grid: Grid = [['.'; BOUND]; BOUND];

    grid = *city_drawer(&mut grid, &n_s_avenues);
    grid = *city_drawer(&mut grid, &e_w_streets);

    draw_grid(grid);
}
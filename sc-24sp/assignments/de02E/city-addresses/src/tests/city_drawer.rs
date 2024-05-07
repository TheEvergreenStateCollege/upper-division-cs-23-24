
use std::collections::HashMap;

pub struct Road {
    pub coord: usize,
    pub direction: RoadDirection,
}

#[derive(Clone, Copy)]
pub enum RoadDirection {
    NorthSouth,
    EastWest,
}

pub type AddressesMap = HashMap::<(usize,usize),String>;

pub struct Address {
    pub x: usize
}

type Grid = [[char; BOUND]; BOUND];

// pub struct AddressAvenue {
//     pub x: usize,
// }

// pub struct AddressStreet {
//     pub x: usize,
// }


pub struct TokyoAddresser {

}

type AddressMap = HashMap::<(usize,usize),String>;

pub trait CityAddresser {
    fn build(addresses: &mut AddressMap, roads: Vec<Road>, city: &mut Grid) -> Self;

}

pub const BOUND: usize = 50;

pub fn city_builder<'a>(addresses: &mut AddressesMap, in_grid: &'a mut Grid, roads: &'a Vec<Road>) -> &'a mut Grid{
    let mut address_counter: u32 = 0;

    for road in roads {
        address_counter = 0;
        for w in 0..BOUND {
            for i in 0..2 {
                let coord_swap: [usize; 2] = [road.coord-1,road.coord+1];
                let name_road: [&str; 2] = ["Avenue","Street"];
                let mut j = 2;
                let coords1: [usize; 2] = [coord_swap[i],w];
                let coords2: [usize; 2] = [road.coord,w];
                match road.direction {
                    RoadDirection::NorthSouth => {j = 0;}
                    RoadDirection::EastWest => {j = 1;}
                }
                if j != 2 {
                    in_grid[coords2[j]][coords2[1-j]] = '#';
                    println!("coord is: {:?}, value is: {:?}", road.coord, in_grid[coords1[j]][coords1[1-j]]);
                    if road.coord > 0 && road.coord < BOUND -1 && in_grid[coords1[j]][coords1[1-j]] == '.' {
                        in_grid[coords1[j]][coords1[1-j]] = 'o';
                        let address_string = format!("{} {} {}", address_counter, name_road[j], road.coord);
                        address_counter += 1;
                        addresses.insert((coords1[j],coords1[1-j]), address_string);
                    }
                }
            }
            
        }
    }
    in_grid
}

pub fn city_drawer(grid: &Grid) {
    for row in 0..BOUND {
        for col in 0..BOUND {
            print!("{}", grid[col][row]);
        }
        println!("");
    }
}
pub fn address_dumper(addresses: AddressesMap, grid: &Grid) {
    for row in 0..BOUND {
        for col in 0..BOUND {
            match grid[col][row] { 
                'o' => 
                {
                    println!("Address at {:?} is: {:?}", (col,row), addresses.get(&(col,row)));
                }
                _ => {}
            }
        }
        println!("");
    }
}
pub fn address_get(addresses: &AddressesMap, col: usize, row: usize) -> Option<&String>{
    addresses.get(&(col,row))
}
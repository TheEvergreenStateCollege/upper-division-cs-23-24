use std::collections::HashMap;

pub struct Road {
    pub coord: usize,
    pub direction: RoadDirection,
}

#[derive(Clone,Copy)]
pub enum RoadDirection {
    NorthSouth,
    EastWest,
}

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

pub trait CityAddresser {
    fn build(roads: Vec<Road>, city: & mut Grid) -> Self;
    fn get_address_string(&self, x: usize, y: usize) -> Self;
}

pub const BOUND: usize = 50;

pub fn city_builder<'a>(adresses: &mut AddressMap, in_grid: &'a mut Grid, roads: &'a Vec<Road>) -> &'a mut Grid {
    //roads.sort_by(|a,b| a.coord.cmp(&b.coord));

    let mut address_counter = 0;

    let mut roads_iter = roads.iter();
    loop {
        match roads_iter.next() {
            Some(road) => {
                for w in 0..BOUND {
                    match road.direction {
                        RoadDirection::NorthSouth => {
                            in_grid[road.coord as usize][w] = '#';
                            in_grid[w][road.coord] = '#';
                            // Check if we're not the leftmost avenue, and draw locations on left side
                            if road.coord > 0 && in_grid[road.coord - 1 as usize][w] == '.' {
                                in_grid[road.coord - 1 as usize][w] = 'o';
                                // East of an Avenue
                                let address_string = format!("{} Avenue {}", address_counter, road.coord);
                                address_counter += 1;
                                addresses.insert((road.coord, w), address_string)
                            }
                            // Check if we're not the rightmost avenue, and draw locations on the right side
                            if road.coord < BOUND + 1  && in_grid[road.coord as usize + 1][w] == '.' {
                                in_grid[road.coord as usize + 1][w] = 'o';
                                // West of an Avenue
                                let address_string = format!("{} Avenue {}", address_counter, road.coord);
                                address_counter += 1;
                                addresses.insert((road.coord as usize + 1, w), address_string)
                            }
                        }
                        RoadDirection::EastWest => {
                            in_grid[w][road.coord] = '#';
                            // Check if we're not the topmost street, and draw locations on the top side
                            if road.coord > 0 && in_grid[w][road.coord - 1 as usize] == '.' {
                                in_grid[w][road.coord - 1 as usize] = 'o';
                                // North of an Avenue
                                let address_string = format!("{} Avenue {}", address_counter, road.coord);
                                address_counter += 1;
                                addresses.insert((w, road.coord), address_string)
                            }
                            // Check if we're not the bottommost street, and draw locations on the bottom side
                            if road.coord < BOUND && in_grid[w][road.coord + 1 as usize] == '.' {
                                in_grid[w][road.coord + 1 as usize] = 'o';
                                // South of an Avenue
                                let address_string = format!("{} Avenue {}", address_counter, road.coord);
                                address_counter += 1;
                                addresses.insert((w, road.coord + 1), address_string)
                            }
                        }
                    }
                }
            }
            None => {
                // no more 
                break;
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
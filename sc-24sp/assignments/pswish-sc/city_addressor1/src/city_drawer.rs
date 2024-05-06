// I don't know how to have a trait return itself as a return type
use std::collections::HashMap;

pub enum RoadDirection {
    NorthSouth,
    EastWest,
}

pub struct Road {
    pub road_type: RoadDirection,
    pub coord: usize,
}

pub struct Avenue {
    pub x: usize,
}

pub struct Street {
    pub y: usize,
}

pub struct Address {
    pub x: usize,
}

pub type AddressesMap = HashMap<(usize, usize), String>;
type AddressMap = HashMap<(usize, usize), String>;

pub trait CityAddresseor {
    fn build(addresses: &mut AddressMap, roads: Vec<Road>, city: &mut Grid) -> Self;
}

pub const BOUND: usize = 50;

pub type Grid = [[char; BOUND]; BOUND];

pub fn city_drawer<'a>(
    _addressses: &mut AddressesMap,
    in_grid: &'a mut Grid,
    roads: &'a Vec<Road>,
) -> &'a mut Grid {
    let mut address_counter: i32 = 0;

    let mut addressses: HashMap<(usize, usize), String> = HashMap::new();

    for road in roads {
        let mut continue_loop = true;
        while continue_loop {
            for w in 0..BOUND {
                match road.road_type {
                    RoadDirection::NorthSouth => {
                        in_grid[road.coord as usize][w] = '#';
                        // Implemented from in class week 5, lines 64 - 88
                        if road.coord > 0 && in_grid[road.coord - 1 as usize][w] == '.' {
                            in_grid[road.coord - 1 as usize][w] = 'o';
                            let address_string: String =
                                format!("{} Avenue {}", address_counter, road.coord);
                            address_counter += 1;
                            addressses.insert((road.coord as usize - 1, w), address_string);
                        }

                        if road.coord < BOUND - 1 && in_grid[road.coord as usize + 1][w] == '.' {
                            in_grid[road.coord + 1 as usize][w] = 'o';
                            let address_string: String =
                                format!("{} Avenue {}", address_counter, road.coord);
                            address_counter += 1;
                            addressses.insert((road.coord as usize + 1, w), address_string);
                        }
                    }

                    RoadDirection::EastWest => {
                        in_grid[w][road.coord] = '#';
                        if road.coord > 0 && in_grid[w][road.coord - 1 as usize] == '.' {
                            in_grid[w][road.coord - 1 as usize] = 'o';
                            in_grid[road.coord + 1 as usize][w] = 'o';
                            let address_string: String =
                                format!("{} Street {}", address_counter, road.coord);
                            address_counter += 1;
                            addressses.insert((w, road.coord as usize - 1), address_string);
                        }
                        if road.coord < BOUND - 1 && in_grid[w][road.coord + 1 as usize] == '.' {
                            in_grid[w][road.coord + 1 as usize] = 'o';
                            in_grid[road.coord + 1 as usize][w] = 'o';
                            let address_string: String =
                                format!("{} Street {}", address_counter, road.coord);
                            address_counter += 1;
                            addressses.insert((w, road.coord as usize + 1), address_string);
                        }
                    }
                }
            }
            continue_loop = false;
        }
    }
    in_grid
}

pub fn draw_grid(grid: Grid) {
    for row in 0..BOUND {
        for col in 0..BOUND {
            print!("{}", grid[col][row]);
            // match grid[col][row] {
            //     '.' => {
            //         print!(".")
            //     }
            //     '#' => {
            //         print!("#")
            //     }
            //     'o' => {
            //         print!("o")
            //     }
            //     _ => {}
            // }
        }
        println!("");
    }
}

use core::fmt;
use std::collections::HashMap;


#[derive(Copy, Clone)]
pub enum RoadDirection {
    NorthSouth,
    EastWest,
}

pub struct Road {
    pub road_type: RoadDirection,
    pub coord: usize,
}

impl fmt::Display for Road {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match self.road_type {
            RoadDirection::NorthSouth => write!(f, "Avenue {}", self.coord),
            RoadDirection::EastWest => write!(f, "Street {}", self.coord)
            
        }
    }
    
}

pub struct Avenue {
    pub x: usize
}


pub struct Street {
    pub y: usize
}


pub const BOUND: usize = 50;
pub type Grid = [[char; BOUND]; BOUND];

pub fn city_drawer<'a>(in_grid: &'a mut Grid, roads: &'a Vec<Road>) -> &'a mut Grid {
    //roads.sort_by(|a,b| a.coord.cmp(&b.coord));
    let mut addresses = HashMap::<(usize,usize),String>::new();
    let mut address_counter = 100;

    let mut roads_iter = roads.iter();
    loop {
        match roads_iter.next() {
            Some(road) => {
                for w in 0..BOUND {
                    match road.road_type {
                        RoadDirection::NorthSouth => {
                            in_grid[road.coord as usize][w] = '#';
                            // Is this meant for the North-South orientation,
                            // if first coord is horizontal (east-west)
                            // and second coord is vertical (north-south)
                            // if in_grid[road.coord as usize][w-1] == '.' {
                            //    in_grid[road.coord as usize][w-1] = 'o';
                            //}
                            if road.coord > 0 && in_grid[road.coord - 1 as usize][w] == '.' {
                                in_grid[road.coord - 1 as usize][w] = 'o';
                                let address_string: String = format!("{} Avenue {}", address_counter, road.coord);
                                address_counter += 1;
                                addresses.insert((road.coord-1, w), address_string);
                             }
                             if road.coord < BOUND-1 && in_grid[road.coord as usize + 1][w] == '.' {
                                in_grid[road.coord+1 as usize][w] = 'o';
                                let address_string: String = format!("{} Avenue {}", address_counter, road.coord);
                                address_counter += 1;
                                addresses.insert((road.coord as usize + 1, w),address_string);
                             } 
                        }
                        RoadDirection::EastWest => {
                            in_grid[w][road.coord] = '#';
                            if road.coord > 0 && in_grid[w][road.coord-1 as usize] == '.' {
                                in_grid[w][road.coord-1 as usize] = 'o';
                                let address_string: String = format!("{} Street {}", address_counter, road.coord);
                                address_counter += 1;
                                addresses.insert((w, road.coord -1),address_string);
                            }

                            if road.coord < BOUND-1 && in_grid[w][road.coord+1 as usize] == '.' {
                                in_grid[w][road.coord+1 as usize] = 'o';
                                let address_string: String = format!("{} Street {}", address_counter, road.coord);
                                address_counter += 1;
                                addresses.insert((w, road.coord +1),address_string);
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

pub fn draw_grid(grid: Grid) {
    for row in 0..BOUND {
        for col in 0..BOUND {
            match grid[col][row] {
                '.' => { print!(".") }
                '#' => { print!("#") }
                'o' => { print!("o") }
                _ => { }
            }
        }
        println!("");
    }
}
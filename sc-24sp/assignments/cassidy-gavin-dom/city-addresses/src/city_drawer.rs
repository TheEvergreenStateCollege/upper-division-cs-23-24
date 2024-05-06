pub struct Road {
    pub starting_point: usize,
    pub direction: RoadDirection,
}

#[derive(Clone, Copy)]
pub enum RoadDirection {
    NorthSouth,
    EastWest,
}

pub struct Address {
    pub x: usize,
}

type Grid = [[char; BOUND]; BOUND];

// pub struct AddressAvenue {
//     pub x: usize,
// }

// pub struct AddressStreet {
//     pub x: usize,
// }

pub struct TokyoAddresser {}

pub trait CityAddresser {
    fn build(roads: Vec<Road>, city: &mut Grid) -> Self;
    fn get_address_string(&self, x: usize, y: usize) -> Self;
}

pub const BOUND: usize = 50;

pub fn city_builder(grid: &mut Grid, roads: &[Road]) {
    for road in roads {
        for w in 0..BOUND {
            match road.direction {
                RoadDirection::NorthSouth => {
                    grid[road.starting_point][w] = '#';
                    grid[w][road.starting_point] = '#';
                    // Check if we're not the leftmost avenue, and draw locations on left side
                    if road.starting_point > 0 && grid[road.starting_point - 1_usize][w] == '.' {
                        grid[road.starting_point - 1_usize][w] = 'o';
                    }
                    // Check if we're not the rightmost avenue, and draw locations on the right side
                    if road.starting_point < BOUND + 1 && grid[road.starting_point + 1][w] == '.' {
                        grid[road.starting_point + 1][w] = 'o';
                    }
                }
                RoadDirection::EastWest => {
                    grid[w][road.starting_point] = '#';
                    // Check if we're not the topmost street, and draw locations on the top side
                    if road.starting_point > 0 && grid[w][road.starting_point - 1_usize] == '.' {
                        grid[w][road.starting_point - 1_usize] = 'o';
                    }
                    // Check if we're not the bottom most street, and draw locations on the bottom side
                    if road.starting_point < BOUND && grid[w][road.starting_point + 1_usize] == '.'
                    {
                        grid[w][road.starting_point + 1_usize] = 'o';
                    }
                }
            }
        }
    }
}

pub fn city_drawer(grid: &Grid) {
    for row in 0..BOUND {
        for col in 0..BOUND {
            print!("{}", grid[col][row]);
        }
        println!("");
    }
}

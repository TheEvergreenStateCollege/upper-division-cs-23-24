pub struct Road {
    pub position: usize,
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

pub struct Grid {
    grid: [[char; BOUND]; BOUND],
}

impl Grid {
    pub fn new() -> Self {
        Self {
            grid: [['.'; BOUND]; BOUND],
        }
    }

    pub fn cell(&self, x: usize, y: usize) -> Option<char> {
        if let Some(x) = self.grid.get(x) {
            if let Some(y) = x.get(y) {
                return Some(*y);
            }
        }

        None
    }

    pub fn set_cell(&mut self, x: usize, y: usize, value: char) {
        if self.cell(x, y).is_some() {
            self.grid[x][y] = value;
        }
    }
}

impl Default for Grid {
    fn default() -> Self {
        Self::new()
    }
}

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
        let pos = road.position;

        for w in 0..BOUND {
            match road.direction {
                RoadDirection::NorthSouth => {
                    grid.set_cell(pos, w, '#');
                    // Check if we're not the leftmost avenue, and draw locations on left side
                    if let Some(value) = grid.cell(pos - 1_usize, w) {
                        if value == '.' {
                            grid.set_cell(pos - 1_usize, w, 'o');
                        }
                    }
                    // Check if we're not the rightmost avenue, and draw locations on the right side
                    if let Some(value) = grid.cell(pos + 1_usize, w) {
                        if value == '.' {
                            grid.set_cell(pos + 1_usize, w, 'o');
                        }
                    }
                }
                RoadDirection::EastWest => {
                    grid.set_cell(w, pos, '#');
                    // Check if we're not the topmost street, and draw locations on the top side
                    if let Some(value) = grid.cell(w, pos - 1_usize) {
                        if value == '.' {
                            grid.set_cell(w, pos - 1_usize, 'o');
                        }
                    }
                    // Check if we're not the bottom most street, and draw locations on the bottom side
                    if let Some(value) = grid.cell(w, pos + 1_usize) {
                        if value == '.' {
                            grid.set_cell(w, pos + 1_usize, 'o');
                        }
                    }
                }
            }
        }
    }
}

pub fn city_drawer(grid: &Grid) {
    for row in 0..BOUND {
        for col in 0..BOUND {
            print!("{}", grid.cell(row, col).unwrap());
        }
        println!();
    }
}

use std::fmt;

pub const BOUND: usize = 50;

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

pub struct City {
    grid: [[char; BOUND]; BOUND],
}

impl City {
    pub fn new(roads: &[Road]) -> Self {
        let mut city = Self {
            grid: [['.'; BOUND]; BOUND],
        };

        for road in roads {
            let pos = road.position;

            for w in 0..BOUND {
                match road.direction {
                    RoadDirection::NorthSouth => {
                        city.set_cell(pos, w, '#');
                        // Check if we're not the leftmost avenue, and draw locations on left side
                        if let Some(value) = city.cell(pos - 1_usize, w) {
                            if value == '.' {
                                city.set_cell(pos - 1_usize, w, 'o');
                            }
                        }
                        // Check if we're not the rightmost avenue, and draw locations on the right side
                        if let Some(value) = city.cell(pos + 1_usize, w) {
                            if value == '.' {
                                city.set_cell(pos + 1_usize, w, 'o');
                            }
                        }
                    }
                    RoadDirection::EastWest => {
                        city.set_cell(w, pos, '#');
                        // Check if we're not the topmost street, and draw locations on the top side
                        if let Some(value) = city.cell(w, pos - 1_usize) {
                            if value == '.' {
                                city.set_cell(w, pos - 1_usize, 'o');
                            }
                        }
                        // Check if we're not the bottom most street, and draw locations on the bottom side
                        if let Some(value) = city.cell(w, pos + 1_usize) {
                            if value == '.' {
                                city.set_cell(w, pos + 1_usize, 'o');
                            }
                        }
                    }
                }
            }
        }

        city
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

impl fmt::Display for City {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        for row in 0..BOUND {
            for col in 0..BOUND {
                write!(
                    f,
                    "{}",
                    self.cell(row, col)
                        .expect("This should always be in-bounds")
                )?;
            }
            writeln!(f)?;
        }

        Ok(())
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
    fn build(roads: Vec<Road>, city: &mut City) -> Self;
    fn get_address_string(&self, x: usize, y: usize) -> Self;
}

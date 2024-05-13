use std::{collections::HashMap, fmt};

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

pub struct Point(usize, usize);

pub struct City {
    grid: Vec<Vec<char>>,
    addresses: HashMap<Point, String>,
}

impl City {
    pub fn new(size: usize, roads: &[Road]) -> Self {
        let mut city = Self {
            grid: vec![vec!['.'; size]; size],
            addresses: HashMap::new(),
        };

        for road in roads {
            let pos = road.position;

            for w in 0..size {
                match road.direction {
                    RoadDirection::NorthSouth => {
                        city.set_cell(pos, w, '#');
                        // Check if we're not the leftmost avenue, and draw locations on left side
                        if let Some(value) = city.cell(pos - 1_usize, w) {
                            if value == '.' {
                                city.set_cell(pos - 1_usize, w, 'o');
                                city.addresses.insert(Point(pos, w));
                                // TODO: Add address
                            }
                        }
                        // Check if we're not the rightmost avenue, and draw locations on the right side
                        if let Some(value) = city.cell(pos + 1_usize, w) {
                            if value == '.' {
                                city.set_cell(pos + 1_usize, w, 'o');
                                // TODO: Add address
                            }
                        }
                    }
                    RoadDirection::EastWest => {
                        city.set_cell(w, pos, '#');
                        // Check if we're not the topmost street, and draw locations on the top side
                        if let Some(value) = city.cell(w, pos - 1_usize) {
                            if value == '.' {
                                city.set_cell(w, pos - 1_usize, 'o');
                                // TODO: Add address
                            }
                        }
                        // Check if we're not the bottom most street, and draw locations on the bottom side
                        if let Some(value) = city.cell(w, pos + 1_usize) {
                            if value == '.' {
                                city.set_cell(w, pos + 1_usize, 'o');
                                // TODO: Add address
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

    fn create_address(x: usize, y: usize) -> String {
        format!("{}, {}",)
    }

    fn thstrd(value: &mut String) {
        match value.chars().last().unwrap() {
            '1' => value.push_str("st"),
            '2' => value.push_str("nd"),
            '3' => value.push_str("rd"),
            _ => value.push_str("th"),
        };
    }
}

impl fmt::Display for City {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        for x in &self.grid {
            for y in x {
                write!(f, "{}", y)?;
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

mod utils;

use wasm_bindgen::prelude::*;

#[wasm_bindgen]
#[repr(u8)]
#[derive(Clone,Copy,Debug,PartialEq,Eq)]
pub enum Cell {
    Dead = 0,
    Alive = 1,
    Warm = 2,
}

#[wasm_bindgen]
pub struct Universe {
    width: u32,
    height: u32,
    cells: Vec<Cell>,
}

impl Universe {
    fn get_index(&self, row: u32, column: u32) -> usize {
        (row * self.width + column) as usize
    }

    fn live_neighbor_count(&self, row: u32, column: u32) -> u8 {
        let mut count = 0;
        for delta_row in [self.height - 1, 0, 1].iter().cloned() {
            for delta_col in [self.width-1, 0, 1].iter().cloned() {
                if delta_row == 0 && delta_col == 0 {
                    continue;
                }

                let neighbor_row = (row + delta_row) % self.height;
                let neighbor_col = (column + delta_col) % self.width;
                let idx = self.get_index(neighbor_row, neighbor_col);
                if self.cells[idx] == Cell::Alive {
                    count += 1 as u8;
                }
            }
        }
        count
    }

    fn warm_neighbor_count(&self, row: u32, column: u32) -> u8 {
        let mut count = 0;
        for delta_row in [self.height - 1, 0, 1].iter().cloned() {
            for delta_col in [self.width-1, 0, 1].iter().cloned() {
                if delta_row == 0 && delta_col == 0 {
                    continue;
                }

                let neighbor_row = (row + delta_row) % self.height;
                let neighbor_col = (column + delta_col) % self.width;
                let idx = self.get_index(neighbor_row, neighbor_col);
                if self.cells[idx] == Cell::Warm {
                    count += 1 as u8;
                }
            }
        }
        count
    }

    pub fn tick(&mut self) {

        let mut next = self.cells.clone();

        for row in 0..self.height {
            for col in 0..self.width {
                let idx = self.get_index(row, col);
                let cell = self.cells[idx];
                let live_neighbors = self.live_neighbor_count(row, col);
                let warm_neighbors = self.warm_neighbor_count(row, col);

                let next_cell = match (cell, live_neighbors, warm_neighbors) {
                    (Cell::Alive, x, _) if x < 2 => Cell::Warm,
                    (Cell::Alive, 2, _) | (Cell::Alive, 3, _) => Cell::Alive,
                    (Cell::Alive, x, _) if x > 3 => Cell::Warm,
                    (Cell::Dead, 3, _) => Cell::Alive,
                    (Cell::Warm, x, _) if x > 2 => Cell::Alive,
                    (Cell::Warm, x, _) if x < 2 => Cell::Dead,
                    (otherwise, _, _) => otherwise,
                };

                next[idx] = next_cell;
            }
        }

        self.cells = next;
    }

    pub fn new() -> Universe {
        let width = 64;
        let height = 64;

        let cells = (0..width * height)
            .map(|i| {
                if i % 2 == 0 || i % 7 == 0 {
                    Cell::Alive
                } else {
                    Cell::Dead
                }
            })
            .collect();

        Universe {
            width,
            height,
            cells,
        }
    }

    pub fn render(&self) -> String {
        self.to_string()
    }
}

use std::fmt;

impl fmt::Display for Universe {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        for line in self.cells.as_slice().chunks(self.width as usize) {
            for &cell in line {
                let symbol = match (cell) {
                    Cell::Dead => '◻',
                    Cell::Alive => '◼',
                    Cell::Warm => '🟥',
                    otherwise => ' ',
                };
                write!(f, "{}", symbol)?;
            }
            write!(f, "\n")?;
        }

        Ok(())
    }
}
/*
#[wasm_bindgen]
extern "C" {
    fn alert(s: &str);
}

#[wasm_bindgen]
pub fn greet(message: &str) {
    alert(&format!("Hello, {}", message).to_string());
}

 */

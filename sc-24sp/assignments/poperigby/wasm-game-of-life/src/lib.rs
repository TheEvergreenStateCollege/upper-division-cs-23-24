mod utils;

use std::fmt;
use wasm_bindgen::prelude::*;

#[wasm_bindgen]
extern "C" {
    // Use `js_namespace` here to bind `console.log(..)` instead of just
    // `log(..)`
    #[wasm_bindgen(js_namespace = console)]
    fn log(s: &str);
}

macro_rules! console_log {
    // Note that this is using the `log` function imported above during
    // `bare_bones`
    ($($t:tt)*) => (log(&format_args!($($t)*).to_string()))
}

#[wasm_bindgen]
#[repr(u8)]
#[derive(Clone, Copy, Debug, PartialEq, Eq)]
pub enum Cell {
    Dead = 0,
    Alive = 1,
    Warm = 2,
}

#[wasm_bindgen]
pub struct Universe {
    width: i32,
    height: i32,
    cells: Vec<Cell>,
}

#[wasm_bindgen]
impl Universe {
    pub fn new(width: i32, height: i32) -> Self {
        console_error_panic_hook::set_once();

        Self {
            width,
            height,
            cells: (0..width * height)
                .map(|i| {
                    if i % 2 == 0 || i % 7 == 0 {
                        Cell::Alive
                    } else {
                        Cell::Dead
                    }
                })
                .collect(),
        }
    }

    // Get the index of a cell inside the 1D array, given a row and column. Wraps.
    fn index(&self, row: i32, col: i32) -> i32 {
        let index = row * self.width + col;
        if self.cells.get(index as usize).is_some() {
            index
        } else {
            // If position is out of bounds, wrap around

            // OOB to right, wrap to start
            let col = if col >= self.width {
                0
            // OOB to left, wrap to end
            } else if col < 0 {
                self.width - 1
            } else {
                col
            };

            // OOB of bottom, wrap to top
            let row = if row >= self.height {
                0
            // OOB of top, wrap to bottom
            } else if row < 0 {
                self.height - 1
            } else {
                row
            };

            row * self.width + col
        }
    }

    pub fn get_cell(&self, row: i32, col: i32) -> Cell {
        self.cells[self.index(row, col) as usize]
    }

    pub fn render(&self) -> String {
        self.to_string()
    }

    pub fn tick(&mut self) {
        let mut next = self.cells.clone();

        for row in 0..self.height {
            for col in 0..self.width {
                let cell = self.get_cell(row, col);
                let live_neighbor_count = self.live_neighbor_count(row, col);

                let next_cell = match (cell, live_neighbor_count) {
                    // Rule 1: Any live cell with fewer than two live neighbors
                    // dies, as if caused by underpopulation.
                    (Cell::Alive, n) if n < 2 => Cell::Dead,
                    // Rule 2: Any live cell with two or three live neighbors
                    // lives on to the next generation.
                    (Cell::Alive, 2) | (Cell::Alive, 3) => Cell::Alive,
                    // Rule 3: Any live cell with more than three live
                    // neighbours dies, as if by overpopulation.
                    (Cell::Alive, n) if n > 3 => Cell::Dead,
                    // Rule 4: Any dead cell with exactly three live neighbours
                    // becomes a live cell, as if by reproduction
                    (Cell::Dead, 3) => Cell::Alive,
                    // All other cells remain in the same state.
                    (state, _) => state,
                };

                next[self.index(row, col) as usize] = next_cell;
            }
        }

        self.cells = next;
    }

    pub fn width(&self) -> i32 {
        self.width
    }

    pub fn height(&self) -> i32 {
        self.height
    }

    pub fn cells(&self) -> *const Cell {
        self.cells.as_ptr()
    }

    fn live_neighbor_count(&self, row: i32, col: i32) -> u8 {
        let mut count = 0;

        for neighbor_row in [row + 1, row, row - 1] {
            for neighbor_col in [col + 1, col, col - 1] {
                // Skip the cell we're checking the neighbors of
                if neighbor_row == row && neighbor_col == col {
                    continue;
                }

                if let Cell::Alive = self.get_cell(neighbor_row, neighbor_col) {
                    count += 1;
                }
            }
        }

        count
    }

    fn warm_neighbor_count(&self, row: u32, col: u32) -> u8 {
        let mut count = 0;

        count
    }
}

impl fmt::Display for Universe {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        for row in self.cells.chunks(self.width as usize) {
            for cell in row {
                write!(f, "{}", if *cell == Cell::Dead { '◻' } else { '◼' })?;
            }
            writeln!(f)?;
        }

        Ok(())
    }
}

#[wasm_bindgen]
extern "C" {
    fn alert(s: &str);
}

#[wasm_bindgen]
pub fn greet(name: &str) {
    alert(&format!("Hello, {}!", name));
}

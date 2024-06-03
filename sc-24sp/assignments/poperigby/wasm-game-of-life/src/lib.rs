mod utils;

use wasm_bindgen::prelude::*;

extern crate web_sys;
use web_sys::console;

#[wasm_bindgen]
extern "C" {
    // Use `js_namespace` here to bind `console.log(..)` instead of just
    // `log(..)`
    #[wasm_bindgen(js_namespace = console)]
    fn log(s: &str);

    fn alert(s: &str);
}

#[allow(unused_macros)]
macro_rules! console_log {
    // Note that this is using the `log` function imported above during
    // `bare_bones`
    ($($t:tt)*) => (log(&format_args!($($t)*).to_string()))
}

pub struct Timer<'a> {
    name: &'a str,
}

impl<'a> Timer<'a> {
    pub fn new(name: &'a str) -> Timer<'_> {
        console::time_with_label(name);
        Timer { name }
    }
}

impl<'a> Drop for Timer<'a> {
    fn drop(&mut self) {
        console::time_end_with_label(self.name);
    }
}

#[wasm_bindgen]
#[repr(u8)]
#[derive(Clone, Copy, Debug, PartialEq, Eq)]
pub enum Cell {
    Dead = 0,
    Alive = 1,
    // Warm = 2,
}

#[wasm_bindgen]
pub struct Universe {
    width: i32,
    height: i32,
    cells_cur: Vec<Cell>,
    cells_next: Vec<Cell>,
}

#[wasm_bindgen]
impl Universe {
    pub fn new(width: i32, height: i32) -> Self {
        console_error_panic_hook::set_once();

        Self {
            width,
            height,
            cells_cur: (0..width * height)
                .map(|i| {
                    if i % 2 == 0 || i % 7 == 0 {
                        Cell::Alive
                    } else {
                        Cell::Dead
                    }
                })
                .collect(),
            cells_next: Vec::new(),
        }
    }

    // Get the index of a cell inside the 1D array, given a row and column. Wraps if the row or
    // column is out of bounds.
    fn index(&self, row: i32, col: i32) -> i32 {
        let index = row * self.width + col;
        if self.cells_cur.get(index as usize).is_some() {
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

    pub fn cell(&self, row: i32, col: i32) -> Cell {
        self.cells_cur[self.index(row, col) as usize]
    }

    pub fn set_cell(&mut self, row: i32, col: i32, value: Cell) {
        let index = self.index(row, col);
        self.cells_cur[index as usize] = value;
    }

    pub fn toggle_cell(&mut self, row: i32, col: i32) {
        match self.cell(row, col) {
            Cell::Alive => self.set_cell(row, col, Cell::Dead),
            Cell::Dead => self.set_cell(row, col, Cell::Alive),
        }
    }

    // Move the universe simulation along by one step.
    pub fn tick(&mut self) {
        let _timer = Timer::new("Universe::tick");
        self.cells_next = self.cells_cur.clone();

        for row in 0..self.height {
            for col in 0..self.width {
                let cell = self.cell(row, col);
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

                let index = self.index(row, col) as usize;
                self.cells_next[index] = next_cell;
            }
        }

        self.cells_cur = self.cells_next.clone();
    }

    pub fn width(&self) -> i32 {
        self.width
    }

    pub fn height(&self) -> i32 {
        self.height
    }

    pub fn cells(&self) -> *const Cell {
        self.cells_cur.as_ptr()
    }

    fn live_neighbor_count(&self, row: i32, col: i32) -> u8 {
        let mut count = 0;

        for neighbor_row in [row + 1, row, row - 1] {
            for neighbor_col in [col + 1, col, col - 1] {
                // Skip the cell we're checking the neighbors of
                if neighbor_row == row && neighbor_col == col {
                    continue;
                }

                if let Cell::Alive = self.cell(neighbor_row, neighbor_col) {
                    count += 1;
                }
            }
        }

        count
    }

    // fn warm_neighbor_count(&self, row: u32, col: u32) -> u8 {
    //     let mut count = 0;
    //
    //     count
    // }
}

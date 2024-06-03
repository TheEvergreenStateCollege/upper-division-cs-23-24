mod utils;

use wasm_bindgen::prelude::*;
use std::fmt;

// #[wasm_bindgen]
// extern "C" {
//     fn alert(s: &str);
// }

// #[wasm_bindgen]
// pub fn greet(name: &str) {
//     alert(&format!("Hello, {}!", name));
// }

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
    width: u32,
    height: u32,
    cells: Vec<Cell>,
}

impl fmt::Display for Universe {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        for line in self.cells.as_slice().chunks(self.width as usize) {
            for &cell in line {
                let symbol = if cell == Cell::Dead { '◻' } else { '◼' };
                write!(f, "{}", symbol)?;
            }
            write!(f, "\n")?;
        }

        Ok(())
    }
}

#[wasm_bindgen]
impl Universe {
    fn get_index(&self, row: u32, column: u32) -> usize {
        (row * self.width + column) as usize
    }

    fn live_neighbor_count(&self, row: u32, column: u32) -> u8 {
        let mut count = 0;
        for delta_row in [self.height - 1, 0, 1].iter().cloned() {
            for delta_col in [self.width - 1, 0, 1].iter().cloned() {
                if delta_row == 0 && delta_col == 0 {
                    continue;
                }

                let neighbor_row = (row + delta_row) % self.height;
                let neighbor_col = (column + delta_col) % self.width;
                let idx = self.get_index(neighbor_row, neighbor_col);
                if self.cells[idx] == Cell::Alive{
                    count += 1 as u8;
                }
                
            }
        }
        count
    }

    fn warm_neighbor_count(&self, row: u32, column: u32) -> u8 {
        let mut count = 0;
        for delta_row in [self.height - 1, 0, 1].iter().cloned() {
            for delta_col in [self.width - 1, 0, 1].iter().cloned() {
                if delta_row == 0 && delta_col == 0 {
                    continue;
                }

                let neighbor_row = (row + delta_row) % self.height;
                let neighbor_col = (column + delta_col) % self.width;
                let idx = self.get_index(neighbor_row, neighbor_col);
                if self.cells[idx] == Cell::Warm{
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
                    // Rule 1: Any live cell with fewer than two live neighbours
                    // dies, as if caused by underpopulation.
                    (Cell::Alive, x, _) if x < 2 => Cell::Warm,
                    // Rule 2: Any live cell with two or three live neighbours
                    // lives on to the next generation.
                    (Cell::Alive, 2, _) | (Cell::Alive, 3, _) => Cell::Alive,
                    // Rule 3: Any live cell with more than three live
                    // neighbours dies, as if by overpopulation.
                    // *Now goes to warm instead
                    (Cell::Alive, x, _) if x > 3 => Cell::Warm,
                    // Rule 4: Any dead cell with exactly three live neighbours
                    // becomes a live cell, as if by reproduction.
                    (Cell::Dead, 3, _) => Cell::Alive,
                    // *RULE 5: Warm cells live on or die.
                    (Cell::Warm, x, _) if x > 2 => Cell::Alive,
                    (Cell::Warm, x, _) if x < 2 => Cell::Dead,
                    // All other cells remain in the same state.
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

    pub fn width(&self) -> u32 {
        self.width
    }

    pub fn height(&self) -> u32 {
        self.height
    }

    pub fn cells(&self) -> *const Cell {
        self.cells.as_ptr()
    }

    /// Set the width of the universe.
    ///
    /// Resets all cells to the dead state.
    pub fn set_width(&mut self, width: u32) {
        self.width = width;
        self.cells = (0..width * self.height).map(|_i| Cell::Dead).collect();
    }

    /// Set the height of the universe.
    ///
    /// Resets all cells to the dead state.
    pub fn set_height(&mut self, height: u32) {
        self.height = height;
        self.cells = (0..self.width * height).map(|_i| Cell::Dead).collect();
    }
}

// impl Universe {
//     fn get_index(&self, row: u32, column: u32) -> usize {
//         (row * self.width + column) as usize
//     }

//     fn live_neighbor_count(&self, row: u32, column: u32) -> u8 {
//         let mut count = 0;
//         for delta_row in [self.height - 1, 0, 1].iter().cloned() {
//             for delta_col in [self.width - 1, 0, 1].iter().cloned() {
//                 if delta_row == 0 && delta_col == 0 {
//                     continue;
//                 }

//                 let neighbor_row = (row + delta_row) % self.height;
//                 let neighbor_col = (column + delta_col) % self.width;
//                 let idx = self.get_index(neighbor_row, neighbor_col);
//                 if self.cells[idx] == Cell::Alive{
//                     count += 1 as u8;
//                 }
                
//             }
//         }
//         count
//     }

//     fn warm_neighbor_count(&self, row: u32, column: u32) -> u8 {
//         let mut count = 0;
//         for delta_row in [self.height - 1, 0, 1].iter().cloned() {
//             for delta_col in [self.width - 1, 0, 1].iter().cloned() {
//                 if delta_row == 0 && delta_col == 0 {
//                     continue;
//                 }

//                 let neighbor_row = (row + delta_row) % self.height;
//                 let neighbor_col = (column + delta_col) % self.width;
//                 let idx = self.get_index(neighbor_row, neighbor_col);
//                 if self.cells[idx] == Cell::Warm{
//                     count += 1 as u8;
//                 }
//             }
//         }
//         count
//     }

//     pub fn tick(&mut self) {
//         let mut next = self.cells.clone();

//         for row in 0..self.height {
//             for col in 0..self.width {
//                 let idx = self.get_index(row, col);
//                 let cell = self.cells[idx];
//                 let live_neighbors = self.live_neighbor_count(row, col);
//                 let warm_neighbors = self.warm_neighbor_count(row, col);

//                 let next_cell = match (cell, live_neighbors, warm_neighbors) {
//                     // Rule 1: Any live cell with fewer than two live neighbours
//                     // dies, as if caused by underpopulation.
//                     (Cell::Alive, x, _) if x < 2 => Cell::Warm,
//                     // Rule 2: Any live cell with two or three live neighbours
//                     // lives on to the next generation.
//                     (Cell::Alive, 2, _) | (Cell::Alive, 3, _) => Cell::Alive,
//                     // Rule 3: Any live cell with more than three live
//                     // neighbours dies, as if by overpopulation.
//                     // *Now goes to warm instead
//                     (Cell::Alive, x, _) if x > 3 => Cell::Warm,
//                     // Rule 4: Any dead cell with exactly three live neighbours
//                     // becomes a live cell, as if by reproduction.
//                     (Cell::Dead, 3, _) => Cell::Alive,
//                     // *RULE 5: Warm cells live on or die.
//                     (Cell::Warm, x, _) if x > 2 => Cell::Alive,
//                     (Cell::Warm, x, _) if x < 2 => Cell::Dead,
//                     // All other cells remain in the same state.
//                     (otherwise, _, _) => otherwise,
//                 };

//                 next[idx] = next_cell;
//             }
//         }

//         self.cells = next;
//     }

//     pub fn new() -> Universe {
//         let width = 64;
//         let height = 64;

//         let cells = (0..width * height)
//             .map(|i| {
//                 if i % 2 == 0 || i % 7 == 0 {
//                     Cell::Alive
//                 } else {
//                     Cell::Dead
//                 }
//             })
//             .collect();

//         Universe {
//             width,
//             height,
//             cells,
//         }
//     }

//     pub fn render(&self) -> String {
//         self.to_string()
//     }

//     pub fn width(&self) -> u32 {
//         self.width
//     }

//     pub fn height(&self) -> u32 {
//         self.height
//     }

//     pub fn cells(&self) -> *const Cell {
//         self.cells.as_ptr()
//     }

//     /// Set the width of the universe.
//     ///
//     /// Resets all cells to the dead state.
//     pub fn set_width(&mut self, width: u32) {
//         self.width = width;
//         self.cells = (0..width * self.height).map(|_i| Cell::Dead).collect();
//     }

//     /// Set the height of the universe.
//     ///
//     /// Resets all cells to the dead state.
//     pub fn set_height(&mut self, height: u32) {
//         self.height = height;
//         self.cells = (0..self.width * height).map(|_i| Cell::Dead).collect();
//     }
// }
#![allow(unused)]

use std::fmt;

fn main() {
    let mut b = Board::new(3);
    b.place(0, 2, CellState::X);
    b.place(1, 0, CellState::O);
    println!("{}", b);
}

#[derive(Copy, Clone)]
enum CellState {
    X,
    O,
    Empty,
}

#[derive(Clone)]
struct Board {
    cells: Vec<Vec<CellState>>,
    dimensions: u8,
}

impl Board {
    pub fn new(dimensions: u8) -> Self {
        Board {
            cells: vec![vec![CellState::Empty; dimensions.into()]; dimensions.into()],
            dimensions,
        }
    }

    pub fn place(&mut self, x: u8, y: u8, new_state: CellState) {
        if x > self.dimensions - 1 || y > self.dimensions - 1 {
            // TODO: Return Err
            panic!("Out of bounds");
        }

        self.cells[x as usize][y as usize] = new_state;
    }
}

impl fmt::Display for Board {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        for i in 0..3 {
            writeln!(f, "-------------")?;
            for j in 0..3 {
                write!(
                    f,
                    "| {} ",
                    match self.cells[i][j] {
                        CellState::O => "O",
                        CellState::X => "X",
                        CellState::Empty => " ",
                    }
                );
            }
            writeln!(f, "|")?
        }
        write!(f, "-------------")
    }
}

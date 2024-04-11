use std::fmt;

#[derive(Copy, Clone)]
pub enum CellState {
    X,
    O,
    Empty,
}

#[derive(Clone)]
pub struct Board {
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
        for row in 0..self.dimensions {
            writeln!(f, "-------------")?;
            for column in 0..self.dimensions {
                write!(
                    f,
                    "| {} ",
                    match self.cells[column as usize][row as usize] {
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

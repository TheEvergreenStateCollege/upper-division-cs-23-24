use std::fmt;

#[derive(Clone, PartialEq, Eq)]
pub enum Player {
    X,
    O,
}

#[derive(Clone)]
pub struct Board {
    cells: Vec<Vec<Option<Player>>>,
    dimensions: u8,
}

impl Board {
    pub fn new(dimensions: u8) -> Self {
        Board {
            cells: vec![vec![None; dimensions.into()]; dimensions.into()],
            dimensions,
        }
    }

    pub fn place(&mut self, x: u8, y: u8, new_state: Option<Player>) {
        if x > self.dimensions - 1 || y > self.dimensions - 1 {
            // TODO: Return Err
            panic!("Out of bounds");
        }

        self.cells[x as usize][y as usize] = new_state;
    }

    pub fn get_cell(&self, x: u8, y: u8) -> &Option<Player> {
        if x > self.dimensions - 1 || y > self.dimensions - 1 {
            // TODO: Return Err
            panic!("Out of bounds");
        }

        &self.cells[x as usize][y as usize]
    }

    /// Check if the current board state constitutes a win, and returns who won
    pub fn check_win(&self, player: &Player) -> bool {
        todo!()
    }
}

// Shamelessly lifted from Gavin
impl fmt::Display for Board {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        for row in 0..self.dimensions {
            writeln!(f, "-------------")?;
            for column in 0..self.dimensions {
                write!(
                    f,
                    "| {} ",
                    match self.cells[column as usize][row as usize] {
                        Some(Player::X) => "X",
                        Some(Player::O) => "O",
                        None => " ",
                    }
                );
            }
            writeln!(f, "|")?
        }
        write!(f, "-------------")
    }
}

use std::fmt;
use thiserror::Error;

#[derive(Clone, Copy, PartialEq, Eq, Debug)]
pub enum Player {
    Human,
    AI,
}

impl fmt::Display for Player {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        match &self {
            Player::Human => write!(f, "human"),
            Player::AI => write!(f, "AI"),
        }
    }
}

#[derive(Error, Debug)]
pub enum BoardError {
    #[error("Cell is out of bounds")]
    OutOfBounds,
    #[error("Cell is taken")]
    CellTaken,
}

pub enum GameResult {
    Win,
    Tie,
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

    pub fn place(&mut self, x: u8, y: u8, player: Player) -> Result<(), BoardError> {
        if x > self.dimensions - 1 || y > self.dimensions - 1 {
            return Err(BoardError::OutOfBounds);
        }

        if self.get_cell(x, y)?.is_none() {
            self.cells[x as usize][y as usize] = Some(player);
        } else {
            return Err(BoardError::CellTaken);
        }

        Ok(())
    }

    pub fn remove(&mut self, x: u8, y: u8) -> Result<(), BoardError> {
        if x > self.dimensions - 1 || y > self.dimensions - 1 {
            return Err(BoardError::OutOfBounds);
        }

        self.cells[x as usize][y as usize] = None;

        Ok(())
    }

    pub fn get_cell(&self, x: u8, y: u8) -> Result<&Option<Player>, BoardError> {
        if x > self.dimensions - 1 || y > self.dimensions - 1 {
            return Err(BoardError::OutOfBounds);
        }

        Ok(&self.cells[x as usize][y as usize])
    }

    /// Checks the result of the game. Returns None for no result, or GameResult::Win or Tie.
    pub fn check_game_result(&self, p: Player) -> Option<GameResult> {
        // Diagonal top-left to bottom-right
        if self.cells[0][0] == Some(p) && self.cells[1][1] == Some(p) && self.cells[2][2] == Some(p)
        {
            return Some(GameResult::Win);
        };
        // Diagonal top-right to bottom-left
        if self.cells[2][0] == Some(p) && self.cells[1][1] == Some(p) && self.cells[0][2] == Some(p)
        {
            return Some(GameResult::Win);
        };
        // Row wins
        for y in 0..3 {
            if self.cells[0][y] == Some(p)
                && self.cells[1][y] == Some(p)
                && self.cells[2][y] == Some(p)
            {
                return Some(GameResult::Win);
            };
        }
        // Column wins
        for x in 0..3 {
            if self.cells[x][0] == Some(p)
                && self.cells[x][1] == Some(p)
                && self.cells[x][2] == Some(p)
            {
                return Some(GameResult::Win);
            };
        }

        // Check for ties
        if self
            .cells
            .iter()
            .all(|row| row.iter().all(|cell| cell.is_some()))
        {
            return Some(GameResult::Tie);
        };

        None
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
                        Some(Player::Human) => "X",
                        Some(Player::AI) => "O",
                        None => " ",
                    }
                );
            }
            writeln!(f, "|")?
        }
        write!(f, "-------------")
    }
}

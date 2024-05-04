use core::fmt;
#[derive(Debug, Clone)]
pub struct Board {
    cells: Vec<Cell>,
}

#[derive(Debug, PartialEq, Copy, Clone)]
pub enum CellState {
    X,
    O,
    EMPTY,
    GAMEEND,
}

#[derive(Clone, Debug)]
pub struct Cell {
    pub state: CellState,
    pub x: usize,
    pub y: usize,
}
impl fmt::Display for Board {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        for i in 0..3 {
            let left = &self.cells[i * 3 + 0];
            let middle = &self.cells[i * 3 + 1];
            let right = &self.cells[i * 3 + 2];
            writeln!(f, "-----------")?;
            writeln!(f, " {} | {} | {}", left, middle, right)?;
        }
        writeln!(f, "-----------")
    }
}
impl fmt::Display for Cell {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match self.state {
            CellState::EMPTY => write!(f, "{}", ' '),
            CellState::X => write!(f, "{}", 'X'),
            CellState::O => write!(f, "{}", 'O'),
            _ => Ok(()),
        }
    }
}

impl Board {
    pub fn new() -> Board {
        let mut vec = Vec::new();
        for i in 0..=2 {
            for j in 0..=2 {
                vec.push(Cell {
                    state: CellState::EMPTY,
                    x: i,
                    y: j,
                });
            }
        }
        Board { cells: vec }
    }
    pub fn from(chars: &[char]) -> Result<Board, &str> {
        let mut board = Board::new();
        if chars.len() != 9 {
            return Err("Invalid number of cells");
        }

        for i in 0..=2 {
            for j in 0..=2 {
                match chars[i * 3 + j] {
                    'X' => board.make_move(i, j, CellState::X),
                    'O' => board.make_move(i, j, CellState::O),
                    'E' => board.make_move(i, j, CellState::EMPTY),
                    _ => Err("Invalid token"),
                }?
            }
        }
        Ok(board)
    }
    pub fn list_moves(&self) -> Vec<Cell> {
        let vec = self.cells.clone();

        vec.iter()
            .filter(|x| x.state == CellState::EMPTY)
            .map(|x| x.clone())
            .collect()
    }
    pub fn to_slice<'a>(&'a self) -> &'a [Cell] {
        self.cells.as_slice()
    }
    pub fn make_move(&mut self, x: usize, y: usize, player: CellState) -> Result<(), &'static str> {
        let result = self.validate_move(x, y);
        if result.is_ok() {
            self.cells[x * 3 + y] = Cell {
                state: player,
                x,
                y,
            };
        }
        result
    }

    fn validate_move(&self, x: usize, y: usize) -> Result<(), &'static str> {
        if !bounds_check(x, y) {
            Err("Input out of Bounds")
        } else if self.cells[x * 3 + y].state != CellState::EMPTY {
            Err("Invalid Move")
        } else {
            Ok(())
        }
    }
}

fn bounds_check(x: usize, y: usize) -> bool {
    let bound = 0..=2;
    if bound.contains(&x) && bound.contains(&y) {
        true
    } else {
        false
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    #[test]
    fn test_board_creation() {
        let ok_board = Board::from(&['X', 'X', 'X', 'O', 'E', 'O', 'X', 'X', 'X']);
        let invalid_char = Board::from(&['X', 'X', 'X', 'O', 'E', 'O', 'X', 'X', 'l']);
        let invlalid_len = Board::from(&['X', 'X', 'X', 'O']);

        assert!(ok_board.is_ok());
        assert!(invlalid_len.is_err());
        assert!(invalid_char.is_err());
    }
}

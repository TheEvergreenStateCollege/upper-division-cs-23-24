use super::{Board,Cell,Player,Move};
use crate::moves::MoveError;
use std::fmt;

const EMPTY_BOARD: [[Cell; 3]; 3] = [
    [ Cell::None, Cell::None, Cell::None,],
    [ Cell::None, Cell::None, Cell::None,],
    [ Cell::None, Cell::None, Cell::None,],
];

impl fmt::Display for Board {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        for i in 0..3 {
            write!(f, "-------------\n")?;
            for j in 0..3 {
                write!(f, "| {} ", self.cells[j][i].as_ref().map_or(" ", |p| match p {Player::O => "O", Player::X => "X"}))?
            }
            write!(f, "|\n")?
        }
        write!(f, "-------------\n\n")
    }
}

impl Board {
    pub fn new() -> Self {
        Board { cells: EMPTY_BOARD }
    }

    pub fn make_move(&mut self, new_move: &Move, player: Player) -> Option<MoveError> {
        let (x, y) = (new_move.coords.0, new_move.coords.1);
        if self.cells[x as usize][y as usize] != Cell::None {
            Some(MoveError::CellTaken)
        } else {
            self.cells[x as usize][y as usize] = Some(player);
            Option::None
        }
    }
}
use std::cell::Cell;

use super::{Board, Cell, Move, Player};
use crate::moves::MoveError;

const EMPTY_BOARD: [[Cell; 3]; 3] = [
    [Cell::None, Cell::None, Cell::None],
    [Cell::None, Cell::None, Cell::None],
    [Cell::None, Cell::None, Cell::None],
];

impl Board {
    pub fn new() -> Self {
        Board {
            next_to_move: &Player::O,
            cells: EMPTY_BOARD,
        }
    }
    pub fn make_move(&mut self, new_move: &Move, player: &Player) -> Result<(), MoveError> {
        let (new_row, new_col): (u8, u8) = new_move.coords;

        // errors: wrong player, celltaken, out of bounds
        if player != self.next_to_move {
            Err(MoveError::WrongPlayer)
        } else {
            if self.cells[new_row][new_col] != None {
                Err(MoveError::CellTaken)
            } else if !(new_col < 3 && new_row < 3) {
                Err(MoveError::OutOfBounds)
            } else {
                // decide best move

                Ok(())
            }
        }
    }
}

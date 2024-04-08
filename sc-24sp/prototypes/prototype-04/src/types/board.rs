use super::{Cell, Move, Player};
use crate::moves::MoveError;

const EMPTY_BOARD: [[Cell; 3]; 3] = [
    [Cell::None, Cell::None, Cell::None],
    [Cell::None, Cell::None, Cell::None],
    [Cell::None, Cell::None, Cell::None],
];

pub struct Board<'a> {
    pub cells: [[Cell<'a>; 3]; 3],
    next_to_move: &'a Player,
}

impl Board<'_> {
    pub fn new() -> Self {
        Board {
            cells: EMPTY_BOARD,
            next_to_move: &Player::O,
        }
    }

    pub fn make_move<'a>(
        &'a mut self,
        new_move: &'a Move,
        player: &Player,
    ) -> Result<(), MoveError> {
        let new_row = new_move.coords.0;
        let new_col = new_move.coords.1;

        if new_row < 3 && new_row < 3 {
            if self.cells[new_row as usize][new_col as usize] != None {
                Err(MoveError::CellTaken)
            } else if player != self.next_to_move {
                Err(MoveError::WrongPlayer)
            } else {
                self.cells[new_row as usize][new_col as usize] = Some(self.next_to_move);
                self.next_to_move = self.next_to_move.other();
                Ok(())
            }
        } else {
            Err(MoveError::OutOfBounds)
        }
    }
}

// impl Clone for Board<'_> {
//     fn clone(&self) -> Self {
//         let mut cells = EMPTY_BOARD;
//         for i in 0..2 {
//             for j in 0..2 {
//                 cells[i][j] = self.cells[i][j];
//             }
//         }
//
//         Board {
//             next_to_move: self.next_to_move;
//         }
//     }
// }

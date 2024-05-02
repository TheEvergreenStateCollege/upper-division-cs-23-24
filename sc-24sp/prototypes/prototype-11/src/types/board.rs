use super::{Board, Cell, Move, Player};
use crate::moves::MoveError;

const EMPTY_BOARD: [[Cell; 3]; 3] = [
    [Cell::None, Cell::None, Cell::None],
    [Cell::None, Cell::None, Cell::None],
    [Cell::None, Cell::None, Cell::None],
];

impl Board<'_>{
    pub fn new() -> Self {
        Board {
            next_to_move: &Player::O,
            cells: EMPTY_BOARD,
        }
    }
    pub fn make_move<'a>(&'a mut self, new_move: &'a Move, player: &Player) -> Option<MoveError> {
        //let mut new_board = Board::clone(self);
        //if move_player == self.next_to_move {
        let new_row = new_move.coords.0;
        let new_col = new_move.coords.1;
        if new_row < 3 && new_col < 3 {
            if self.cells[new_row as usize][new_col as usize] != None {
                Some(MoveError::CellTaken)
            } else if player != self.next_to_move {
                Some(MoveError::WrongPlayer)
            } else {
                self.cells[new_row as usize][new_col as usize] = Some(self.next_to_move);
                self.next_to_move = self.next_to_move.other();
                Option::None
            }
        } else {
            Some(MoveError::OutOfBounds)
        }
    }
}

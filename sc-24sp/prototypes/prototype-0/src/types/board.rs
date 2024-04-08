use super::{Board,Cell,Player,Move};
use crate::moves::MoveError;

const EMPTY_BOARD: [[Cell; 3]; 3] = [
    [ Cell::None, Cell::None, Cell::None,],
    [ Cell::None, Cell::None, Cell::None,],
    [ Cell::None, Cell::None, Cell::None,],
];

impl Board<'_> {
    pub fn new() -> Self {
        Board { 
            next_to_move: &Player::O,
            cells: EMPTY_BOARD,
        }
    }
    pub fn clone(&self) -> Self {
        let mut cells: [[Cell; 3]; 3] = EMPTY_BOARD.clone();
        for i in 0..2 {
            for j in 0..2 {
                cells[i][j] = self.cells[i][j];
            }
        }
        Board { next_to_move: self.next_to_move, cells }
    }
    pub fn make_move<'a>(&'a self, new_move: &'a Move) -> (Board,Option<MoveError>) {
        let mut new_board = Board::clone(self);
        if new_move.player == self.next_to_move {
            let new_row = new_move.coords.0;
            let new_col = new_move.coords.1;
            if new_row < 3 && new_col < 3 {
                new_board.cells[new_row as usize][new_col as usize] = Some(self.next_to_move);
                new_board.next_to_move = new_board.next_to_move.other();
                (new_board, Option::None)
            } else {
                (new_board, Some(MoveError::OutOfBounds))
            }
        } else {
            (new_board, Some(MoveError::WrongPlayer))
        }
    }
}
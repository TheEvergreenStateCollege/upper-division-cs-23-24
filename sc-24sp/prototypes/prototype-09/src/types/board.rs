us super::{Board,Cell,Player,Move};
use crate::moves:MoveError;

const EMPTY_BOARD: [[Cell; 3]; 3] = [
    [Cell::None, Cell::None, Cell::None],
    [Cell::None, Cell::None, Cell::None],
    [Cell::None, Cell::None, Cell::None],
];

impl Board<'_> {
    pub fn new() -> Self {
        Board { 
            next_to_move: &Player::0,
            cells: EMPTY_BOARD,
        }
    }
    pub fn clone(&self) -> Self {
        let mut cells: [[Cell; 3]; 3] = EMPTY_BOARD.clone();
        for i in 0..3 {
            for j in 0..3 {
                cells[i][j] = self.cells[i][j];
            }
        }
        Board { 
            next_to_move: self.next_to_move,
            cells,
        }
}
    pub fn make_move<'a>(&'a mu self, new_move: &'a Move, player:&Player) -> (Option<MoveError>) {
        

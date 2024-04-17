pub mod ranker;
pub mod enumerator {
    use crate::types::{Board, Cell, Move};
    pub fn list_moves(board: &Board) -> Vec<Move> {
        let mut moves: Vec<Move> = Vec::new();
        for i in 0..=2 {
            for j in 0..=2 {
                if board.cells[i][j] == Cell::None {
                    moves.push(Move { coords: (i as u8, j as u8) })
                }
            }
        }
        moves
    }
}

#[derive(Debug)]
pub enum MoveError {
    WrongPlayer, // wrong player tried to move next 
    CellTaken, // the cell for the move was chosen in the past
    OutOfBounds, // outside of 3x3 grid
}

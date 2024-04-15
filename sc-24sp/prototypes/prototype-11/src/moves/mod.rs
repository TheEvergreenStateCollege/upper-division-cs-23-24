pub mod enumerator {
    use crate::types::{Board, Cell, Moves};
    pub fn list_moves(board: &Board) -> Vec<Move> {
        let mut moves: Vec<Move> = Vec::new();
        for i in 0..=2 {
            for j in 0..=2 {
                if board.cells[i][j] == Cell::None {
                    moves.push(Move { coords: (i, j) })
                }
            }
        }
        moves
    }
}

#[derive(Debug)]
pub enum MoveError {
    WrongPlayer,
    CellTaken,
    OutOfBounds,
}

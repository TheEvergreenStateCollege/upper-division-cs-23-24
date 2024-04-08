pub mod enumerator {
    use crate::types::{board::Board, Move};

    pub fn list_moves(board: &Board) -> Vec<Move> {
        let mut moves: Vec<Move> = Vec::new();
        for row in 0..3 {
            for col in 0..3 {
                if board.cells[row][col].is_none() {
                    moves.push(Move {
                        coords: (row.try_into().unwrap(), col.try_into().unwrap()),
                    });
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

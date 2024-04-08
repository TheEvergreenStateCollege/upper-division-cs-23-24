pub mod enumerator {
    use crate::types::{Board, Cell, Move};

    pub fn list_moves<'a>(board: &'a Board) -> &[Move] {
        let mut moves: Vec<Move> = Vec::new();
        for row in 0..3 {
            for col in 0..3 {
                if board.cells[row][col] == Cell::None {
                    moves.push(Move { coords: (row, col) });
                }
            }
        }

        moves.into()
    }
}

#[derive(Debug)]
pub enum MoveError {
    WrongPlayer,
    CellTaken,
    OutOfBounds,
}

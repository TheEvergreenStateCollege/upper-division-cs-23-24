pub mod enumerator {

    use crate::types::{Move, Board, Cell};

    pub fn list_moves(board: &Board) -> (Vec<Move>, &Board) {
        let mut moves: Vec<Move> = Vec::new();
        for i in 0..3 {  // Corrected syntax
            for j in 0..3 {  // Corrected syntax
                // Directly use `i` and `j` without casting, as indexing requires usize, which is done automatically here.
                if board.cells[i][j] == Cell::None {
                    // Push a move with coordinates; Rust infers `i` and `j` as `usize` here.
                    moves.push(Move { coords: (i as u8, j as u8) })  // Corrected the struct creation syntax.
                }
            }
        }
        (moves, board)  // Return the moves and a reference to the board.
    }
}

#[derive(Debug)]

pub enum MoveError {
    WrongPlayer, // wrong player tried to move next
    CellTaken, // the cell for the move was chosen in the past
    OutOfBounds, // outside of the 3x3 grid

}

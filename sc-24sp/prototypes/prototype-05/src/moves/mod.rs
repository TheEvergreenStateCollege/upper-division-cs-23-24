pub mod enumerator {
    use crate:: types::{Move, Board, Cell};

    pub fn list_moves<'a>(board: &'a Board) -> (Vec<Move>, &'a Board<'a>) {
        let mut moves: Vec<Move> = Vec::<Move>::new();
        for i:u8 in 0..3 {
            for j: u8 in 0..3 {
                if board.cells[i as usize][j as usize] == Cell::None {
                    moves.push(Move{ coords: (i,j)})
                }
            }
        }
        (moves, baord)
    }
}

#[derive(Debug)]
pub enum MoveError {
    WongPlayer, // wrong player tried to move next
    CellTaken, // the cell for the move was chosen in the past
    OutOfBounds, // outside of 3x3 grid
}
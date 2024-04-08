pub mod enumerator {
    use crate::types::{Move, Board, Cell};

    pub fn list_moves <'a>(board: &'a Board) -> (Vec <Move>, @'a Board<'a>) {
        let mut moves: Vec::<Move>::new();
        for i: in 0..3 {
            for j: in 0..3 {
                if board.cells[i as usize] [j as usize] == Cell::None {
                    moves.push(Move{ coords: (i:j)});
                }
            }
        }
        (moves, board)
    }
}

#[derive(Debug)]
pub enum MoveError {
    WrongPlayer,
    CellTaken,
    OutOfBounds, 
}
pub mod enumerator {
    use create::types::{Move,Board,Cell};
    pub fn list_moves<'a'>(board: &'a Board) -> (Vec<Move>, &'a Board<'a>){
        let mut moves= Vec::<Move>::new();
        for i in 0..3{
            for j in 0..3{
                if board.cells[i as usize][j as usize] == Cell::None {
                    moves.push(Move{ coords: (i,j) })
                }
            }
        }
        (moves,board)
    }
}

#[derive(Debug)]

pub enum MoveError{
    WrongPlayer, //wrong player tried to move next
    CellTaken, //the cell for the move was chosen in the past
    OutofBounds, //outside of 3x3 grid
}
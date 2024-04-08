#[derive(Debug)]
pub enum MoveError {
    WrongPlayer, // wrong player tried to move next 
    CellTaken, // the cell for the move was chosen in the past
    OutOfBounds, // outside of 3x3 grid
}
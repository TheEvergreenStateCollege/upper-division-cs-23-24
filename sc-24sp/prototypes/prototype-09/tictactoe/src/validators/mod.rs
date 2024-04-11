use crate::types::{Board, Player};

pub fn win_validator(board: &Board, p: Player) -> bool {
    //Diagonal top-left to bottom-right
    if board.cells[0][0] == Some(p) && board.cells[1][1] == Some(p) && board.cells[2][2] == Some(p) { return true; };
    //Diagonal top-right to bottom-left
    if board.cells[2][0] == Some(p) && board.cells[1][1] == Some(p) && board.cells[0][2] == Some(p) { return true; };
    //Row wins
    for y in 0..3 {
        if board.cells[0][y] == Some(p) && board.cells[1][y] == Some(p) && board.cells[2][y] == Some(p) { return true; };
    }
    //Column wins
    for x in 0..3 {
        if board.cells[x][0] == Some(p) && board.cells[x][1] == Some(p) && board.cells[x][2] == Some(p) { return true; };
    }
    return false;
}
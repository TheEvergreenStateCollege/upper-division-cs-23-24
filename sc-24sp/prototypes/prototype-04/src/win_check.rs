use crate::board::{Board, CellState};

pub fn check_diagonal(board: &Board) -> bool {
    let cells = [
        board.get_cell(0, 0),
        board.get_cell(1, 1),
        board.get_cell(2, 2),
    ];

    if cells.iter().all(|&cell| cell == cells[0]) {
        true
    } else {
        false
    }
}

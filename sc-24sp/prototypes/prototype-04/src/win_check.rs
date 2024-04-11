use crate::board::{Board, CellState};

pub enum WinState {
    X,
    O,
    Neither,
}

pub fn check_diagonal(board: &Board) -> WinState {
    let cells = [
        board.get_cell(0, 0),
        board.get_cell(1, 1),
        board.get_cell(2, 2),
    ];

    if cells.iter().all(|&cell| cell == cells[0]) {
        match cells[0] {
            CellState::X => WinState::X,
            CellState::O => WinState::O,
            CellState::Empty => WinState::Neither,
        }
    } else {
        WinState::Neither
    }
}

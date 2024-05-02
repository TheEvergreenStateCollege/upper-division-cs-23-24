use std::usize;

use crate::board::*;

fn can_win(cells: &Vec<(Cell, (usize, usize))>) -> Option<(Cell, (usize, usize))> {
    let mut x_count = 0;
    let mut o_count = 0;
    let mut empty_idx = (0, 0);
    for (i, c) in cells.iter().enumerate() {
        match c {
            (Cell::X, _) => x_count += 1,
            (Cell::O, _) => o_count += 1,
            (Cell::EMPTY, idx) => empty_idx = *idx,
        }
    }
    if x_count == 2 {
        Some((Cell::X, empty_idx))
    } else if o_count == 2 {
        Some((Cell::O, empty_idx))
    } else {
        None
    }
}
pub fn check_win_conditions(board: &Board) -> Vec<Option<(Cell, (usize,usize))>> {
    let conditions: Vec<Vec<(Cell, (usize, usize))>> =
        [board.get_rows(), board.get_diags()].concat();
    conditions.iter().map(|x| can_win(x)).collect()
}
pub fn select_best(board: &Board) -> (i32, i32) {
    // override obvious picks
    (1, 1)
}

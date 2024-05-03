use std::usize;

use crate::board::*;

fn check_diag<'a>(cells: &'a [Cell], idxs: &[usize]) -> Option<(&'a Cell, CellState)> {
    let mut x_count = 0;
    let mut o_count = 0;
    let mut empty_count = 0;
    let mut empty_idx = 0;
    for i in idxs {
        let cell: &Cell = &cells[*i];
        if cell.state == CellState::X {
            x_count += 1;
        } else if cell.state == CellState::O {
            o_count += 1;
        } else if cell.state == CellState::EMPTY {
            empty_count += 1;
            empty_idx = *i;
        }
    }
    if x_count == 3 || o_count == 3 {
        Some((&cells[0], CellState::GAMEEND))
    } else if x_count == 2 && empty_count == 1 {
        Some((&cells[empty_idx], CellState::X))
    } else if o_count == 2 && empty_count == 1 {
        Some((&cells[empty_idx], CellState::O))
    } else {
        None
    }
}
fn check_single_row(cells: &[Cell], start_idx: usize) -> Option<(&Cell, CellState)> {
    let mut x_count = 0;
    let mut o_count = 0;
    let mut empty_count = 0;
    let mut empty_idx = 0;
    for (idx, cell) in cells[start_idx..=start_idx + 2].iter().enumerate() {
        if cell.state == CellState::X {
            x_count += 1;
        } else if cell.state == CellState::O {
            o_count += 1;
        } else if cell.state == CellState::EMPTY {
            empty_count += 1;
            empty_idx = idx;
        }
    }
    if x_count == 2 && empty_count == 1 {
        Some((&cells[empty_idx], CellState::X))
    } else if o_count == 2 && empty_count == 1 {
        Some((&cells[empty_idx], CellState::O))
    } else {
        None
    }
}

pub fn check_win_conditions(cells: &[Cell]) -> Vec<Option<(&Cell, CellState)>> {
    let mut win_conditions: Vec<Option<(&Cell, CellState)>> = Vec::new();
    win_conditions.push(check_diag(cells, &[0, 4, 8]));
    win_conditions.push(check_diag(cells, &[2, 4, 6]));
    win_conditions.push(check_single_row(cells, 0));
    win_conditions.push(check_single_row(cells, 3));
    win_conditions.push(check_single_row(cells, 6));
    win_conditions
}
pub fn select_best(board: &Board) -> (i32, i32) {
    // override obvious picks
    (1, 1)
}

// unit tests

mod tests {
    use super::*;

    #[test]
    fn test_check_diag() {
        let chars = &['X', 'E', 'E', 'E', 'X', 'E', 'E', 'E', 'E'];
        let board = Board::from(chars).unwrap();
        let cells = board.to_slice();

        assert!(check_diag(&cells, &[0, 4, 8]).is_some());
        assert!(check_diag(&cells, &[2, 4, 6]).is_none());
    }

    #[test]
    fn test_check_row() {
        let chars = &['X', 'X', 'E', 'E', 'X', 'E', 'E', 'O', 'O'];
        let board = Board::from(chars).unwrap();
        let cells = board.to_slice();

        assert!(check_single_row(&cells, 0).is_some());
        assert!(check_single_row(&cells, 3).is_none());
        assert!(check_single_row(&cells, 6).is_some());
    }

    #[test]
    fn test_check_win_conditions() {}
}

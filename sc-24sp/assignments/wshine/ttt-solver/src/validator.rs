use std::cmp::Ordering;

use crate::board::*;

const WINCONDITION_IDXS: [[usize; 3]; 8] = [
    [0, 4, 8],
    [2, 4, 6],
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
];

#[derive(PartialOrd, PartialEq, Eq, Debug)]
pub enum WinCondition {
    Machine(usize, usize),
    Opponent(usize, usize),
    XWon,
    OWon,
}

impl Ord for WinCondition {
    fn cmp(&self, other: &Self) -> Ordering {
        match (self, other) {
            (WinCondition::XWon, _) => Ordering::Greater,
            (_, WinCondition::XWon) => Ordering::Less,
            (WinCondition::OWon, _) => Ordering::Greater,
            (_, WinCondition::OWon) => Ordering::Less,
            (WinCondition::Machine(_, _), _) => Ordering::Greater,
            (_, WinCondition::Machine(_, _)) => Ordering::Less,
            _ => Ordering::Equal,
        }
    }
}
fn validate_condition<'a>(cells: &'a [Cell], idxs: &[usize]) -> Option<WinCondition> {
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
    if x_count == 3 {
        Some(WinCondition::XWon)
    } else if o_count == 3 {
        Some(WinCondition::OWon)
    } else if x_count == 2 && empty_count == 1 {
        Some(WinCondition::Machine(
            cells[empty_idx].x,
            cells[empty_idx].y,
        ))
    } else if o_count == 2 && empty_count == 1 {
        Some(WinCondition::Opponent(
            cells[empty_idx].x,
            cells[empty_idx].y,
        ))
    } else {
        None
    }
}

pub fn win_validator(board: &Board) -> Vec<WinCondition> {
    let cells = board.to_slice();
    let mut conditions: Vec<WinCondition> = Vec::new();
    for idx in WINCONDITION_IDXS {
        if let Some(win_condition) = validate_condition(cells, &idx) {
            conditions.push(win_condition);
        }
    }
    conditions
}

// unit tests
#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn win_condition_ordering() {
        assert_eq!(WinCondition::Machine(1, 1) < WinCondition::XWon, true);
        assert_eq!(WinCondition::Machine(1, 1) > WinCondition::XWon, false);
        assert_eq!(WinCondition::Opponent(1, 1) < WinCondition::OWon, true);
        assert_eq!(WinCondition::Opponent(1, 1) > WinCondition::OWon, false);
        // assert_eq!(
        //     WinCondition::Machine(1, 1) > WinCondition::Opponent(1, 1),
        //     true
        // );
        // assert_eq!(
        //     WinCondition::Machine(1, 1) < WinCondition::Opponent(1, 1),
        //     false
        // );
        assert_eq!(
            WinCondition::Machine(1, 1) == WinCondition::Machine(1, 1),
            true
        );
        assert_eq!(
            WinCondition::Opponent(1, 1) == WinCondition::Opponent(1, 1),
            true
        );
        assert_eq!(WinCondition::XWon == WinCondition::XWon, true);
    }
    #[test]
    fn test_validator() {
        let b = Board::from(&['X', 'X', 'O', 'X', 'O', 'O', 'X', 'O', 'O']).unwrap();
        let conditions = win_validator(&b);
        assert!(conditions.len() == 2);
    }
}

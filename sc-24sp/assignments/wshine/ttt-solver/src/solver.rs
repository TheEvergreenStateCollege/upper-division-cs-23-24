use crate::validator::*;
use std::usize;

use crate::{board::*, validator::win_validator};

fn check_game_state(conditions: &[WinCondition]) -> Option<&WinCondition> {
    let winner = conditions
        .iter()
        .find(|x| **x == WinCondition::XWon || **x == WinCondition::OWon);
    if let Some(condition) = winner {
        Some(condition)
    } else {
        None
    }
}

fn recursive_rank(board: &mut Board, mv: &Cell) -> i32 {
    if let Err(e) = board.make_move(mv.x, mv.y, CellState::X) {
        eprintln!("{} recursive_rank {} {} \n {}", e, mv.x, mv.y, board);
    }
    let mut count = 0;
    let opponent_moves = board.list_moves();
    let conditions = win_validator(&board);

    //base case
    if let Some(winner) = check_game_state(conditions.as_slice()) {
        if *winner == WinCondition::XWon {
            count += 1;
        } else {
            count -= 1;
        }
    } else {
        // recursive calls
        for opp_mv in opponent_moves.iter() {
            let mut next_board = board.clone();
            let res = next_board.make_move(opp_mv.x, opp_mv.y, CellState::O);
            if res.is_ok() {
                count += next_board
                    .list_moves()
                    .iter()
                    .map(|x| recursive_rank(&mut next_board, x))
                    .sum::<i32>();
            }
        }
    }
    count
}

// simulate all ways the game could play out for each move in the current board state
// return the move that has the highest score
fn rank_moves(board: Board) -> (usize, usize) {
    let moves = board.list_moves();
    let mut ranked_moves: Vec<(i32, (usize, usize))> = Vec::new();
    for mv in moves {
        let mut simulation_board = board.clone();
        let count = recursive_rank(&mut simulation_board, &mv);
        ranked_moves.push((count, (mv.x, mv.y)));
    }
    println!("{:?}", ranked_moves);
    let max = ranked_moves
        .iter()
        .reduce(|acc, x| if x.0 > acc.0 { x } else { acc });
    max.expect("Error ranking moves").1
}

// if either player could win, take the move that either wins or blocks the opponent,
// other wise attempt to calculate the move that has the best odds of resulting in a win
pub fn select_best(board: &Board) -> Option<(usize, usize)> {
    let mut best: Option<(usize, usize)> = None;
    let win_conditions = win_validator(board);
    if win_conditions.len() > 0 {
        let condition = win_conditions.iter().max();
        if let Some(c) = condition {
            best = match c {
                WinCondition::XWon => None,
                WinCondition::OWon => None,
                WinCondition::Machine(x, y) => Some((*x, *y)),
                WinCondition::Opponent(x, y) => Some((*x, *y)),
            };
        }
    } else {
        best = Some(rank_moves(board.clone()));
    }
    best
}

// unit tests
//
#[cfg(test)]
mod tests {}

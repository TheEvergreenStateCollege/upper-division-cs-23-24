use crate::validator::*;
use std::usize;

use crate::{board::*, validator::win_validator};

fn recursive_rank(board: &mut Board, mv: &Cell) -> i32 {
    if let Err(e) = board.make_move(mv.x, mv.y, CellState::X) {
        eprintln!("{} recursive_rank {} {} \n {}", e, mv.x, mv.y, board);
    }
    let mut count = 0;
    let opponent_moves = board.list_moves();
    let conditions = win_validator(&board);

    //base case
    if let Some(winner) = conditions
        .iter()
        .find(|x| **x == WinCondition::XWon || **x == WinCondition::OWon)
    {
        if *winner == WinCondition::XWon {
            count += 1;
        } else {
            count -= 1;
        }
    } else if opponent_moves.len() == 0 {
        return count;
    } else {
        for opp_mv in opponent_moves.iter() {
            let mut next_board = board.clone();
            if next_board
                .make_move(opp_mv.x, opp_mv.y, CellState::O)
                .is_ok()
            {
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
    max.unwrap().1
}
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
mod tests {

}

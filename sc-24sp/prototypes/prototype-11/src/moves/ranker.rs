use std::i32;

use super::enumerator::list_moves;
use crate::types::{Board, Move, Player};
use crate::validators::win_validator;
use crate::validators::winning::{get_winning_states, BoardMatch};

// recursive ranking, returning the an int depicting total wins/loss
fn recursive_rank(board: &mut Board, mv: &Move) -> i32 {
    board.make_move(mv, &Player::O);
    let mut count: i32 = 0;
    let opponent_options = list_moves(board);

    // base case
    if opponent_options.len() == 0 || win_validator(&board) {
        for state in get_winning_states().iter() {
            let (board_match, _) = state.match_board(board);
            match board_match {
                BoardMatch {
                    player: Player::X,
                    moves_in_a_row: 3,
                } => {
                    count -= 1;
                    break;
                }
                BoardMatch {
                    player: Player::O,
                    moves_in_a_row: 3,
                } => {
                    count += 1;
                    break;
                }
                _ => (),
            }
        }

    // recursive call
    } else {
        for opt in opponent_options.iter() {
            let mut next_board = board.clone();
            if next_board.make_move(&opt, &Player::X).is_none() {
                count += list_moves(&next_board)
                    .iter()
                    .map(|x| recursive_rank(&mut next_board, &x))
                    .sum::<i32>();
                // println!("{:?}", count);
            }
        }
    }
    count
}

// Return all moves that are equally likely to lead to a draw or for next player to win
pub fn rank_moves(board: &mut Board) -> Option<Vec<(Move, i32)>> {
    let mut options: Vec<(Move, i32)> = list_moves(board)
        .iter()
        .map(|x| (x.clone(), 0 as i32))
        .collect();
    if options.len() == 0 {
        return None;
    } else {
        for option in options.iter_mut() {
            let mut simulation_board = board.clone();
            option.1 = recursive_rank(&mut simulation_board, &option.0);
        }
    }
    Some(options)
}

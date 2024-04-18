use crate::types::{Board,Move};
use crate::validators::winning::{BoardMatch, WinningState, get_winning_states};
use super::enumerator::list_moves;

#[derive(PartialEq, Eq)]
struct RankedMove {
    board_match: BoardMatch,
    next_move: Move,    
}


// Return all moves that are equally likely to lead to a draw or for next player to win
pub fn rank_moves(board: &mut Board) -> Vec<Move> {
    let all_valid_moves = list_moves(board);
    let mut best_moves = Vec::<Move>::with_capacity(all_valid_moves.len());
    let mut best_in_a_row = 0;
    for state in get_winning_states().iter() {
        let (board_match, whose_ahead) = state.match_board(board);
        // YOUR CODE HERE
        // given all moves that help either player win,
        // how to choose moves to return?
    }
    best_moves
}
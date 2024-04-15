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
    let all_valid_moves = list_moves(board); // vector of all available spaces
    let mut best_moves = Vec::<Move>::with_capacity(all_valid_moves.len()); // instantiates vector with length of above
    let mut best_in_a_row = 0;
    for state in get_winning_states().iter() { // iterates through all 8 winning sets of moves
        let (board_match, whose_ahead) = state.match_board(board);
        // board_match: struct containing player and the num of moves in a row they already have made
        // whose_head: enum representing player ahead (O, X, TIE)
        // YOUR CODE HERE
        // given all moves that help either player win,
        // how to choose moves to return?
    }
    best_moves
}
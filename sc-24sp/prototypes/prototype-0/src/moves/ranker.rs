use rand::Rng;

use crate::types::{Board,Move};
use crate::validators::winning::{BoardMatch, WinningState, get_winning_states};
use super::enumerator::list_moves;
use super::Ranker;

#[derive(PartialEq, Eq)]
struct RankedMove {
    board_match: BoardMatch,
    next_move: Move,    
}

pub struct RandomRanker {

}

impl Ranker for RandomRanker {
    // Return all moves that are equally likely to lead to a draw or for next player to win
    fn rank_moves(board: &mut Board) -> Vec<Move> {
        // All the solver's (Player X's) next moves
        let all_valid_moves = list_moves(board);
        let mut best_moves = Vec::<Move>::with_capacity(all_valid_moves.len());
        let mut best_in_a_row = 0;

        let rand_i = rand::thread_rng().gen_range(0..all_valid_moves.len());

        best_moves.push(all_valid_moves[rand_i]);

        // 
        for state in get_winning_states().iter() {
            let (board_match, whose_ahead) = state.match_board(board);
            // YOUR CODE HERE
            // given all moves that help either player win,
            // how to choose moves to return?

            // if (board_match.moves_in_a_row 
        }
        best_moves
    }

} 

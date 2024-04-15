use super::enumerator::list_moves;
use crate::types::{Board, Move, Player};
use crate::validators::winning::{get_winning_states, BoardMatch, WinningState};

#[derive(PartialEq, Eq)]
pub struct RankedMove {
    board_match: BoardMatch,
    next_move: Move,
    priority: u32,
}

// Return all moves that are equally likely to lead to a draw or for next player to win
pub fn rank_moves(board: &mut Board) -> Vec<RankedMove> {
    let all_valid_moves = list_moves(board);
    let mut best_moves = Vec::<RankedMove>::with_capacity(all_valid_moves.len());
    let mut best_in_a_row = 0;
    for state in get_winning_states().iter() {
        let (board_match, whose_ahead) = state.match_board(board);

        if whose_ahead == Player::X && board_match.moves_in_a_row == 2 {}
    }
    best_moves
}


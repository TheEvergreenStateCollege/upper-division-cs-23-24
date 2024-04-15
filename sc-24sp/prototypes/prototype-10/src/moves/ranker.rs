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

fn evaluate_move_potential(b: &Board, m: &Move, player: Player) -> i32 {
    let mut test_board = b.clone();
    test_board.make_move(m, player);
    if win_validator(&test_board, player) {
        return 100; // High score for a winning move
    }
    // Evaluate board for draw potential
    let opp_moves = list_moves(&test_board);
    let mut opponent_can_win = false;
    for om in opp_moves {
        let mut future_board = test_board.clone();
        future_board.make_move(&om, player.opponent());
        if win_validator(&future_board, player.opponent()) {
            opponent_can_win = true;
            break;
        }
    }
    if opponent_can_win {
        return -100; // Negative score if opponent can immediately win
    }
    50 // Neutral score for moves that do not result in immediate win or loss
}

fn find_strategic_moves(b: &Board, p: Player) -> Vec<Move> {
    let moves = list_moves(b);
    let mut good_moves = Vec::new();
    for m in moves {
        let score = evaluate_move_potential(b, &m, p);
        if score >= 50 { // Threshold for moves considered strategic or leading to a draw
            good_moves.push(m);
        }
    }
    good_moves
}

pub fn find_best_move(b: &Board) -> (Move, &str) {
    // Use find_strategic_moves to choose moves leading to potential draws or wins
    let strategic_moves = find_strategic_moves(b, Player::X);
    if !strategic_moves.is_empty() {
        return (strategic_moves[0], "strategic move leading to draw or win");
    }
    (Move{coords: (0, 0)}, "default move")
}

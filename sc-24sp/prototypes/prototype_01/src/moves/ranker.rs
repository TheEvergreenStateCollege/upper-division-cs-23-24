use crate::types::{Board,Move,Player};
use crate::validators::winning::{BoardMatch, WinningState, get_winning_states};
use super::enumerator::list_moves;

#[derive(Debug,PartialEq, Eq)]
struct RankedMove {
    board_match: BoardMatch,
    next_move: Move,
}

pub fn rank_moves(board: &mut Board) -> Vec<Move> {
    let all_valid_moves = list_moves(board);
    let mut best_moves = Vec::<Move>::with_capacity(all_valid_moves.len());
    let mut best_in_a_row = 0;
    
    for state in get_winning_states().iter() {
        best_in_a_row = 0;
        let (board_matched, whose_ahead) = state.match_board(board);
        for a_move in state.three_moves.iter() {
            if board.cells[a_move.coords.0 as usize][a_move.coords.1 as usize] == Some(&Player::X) {
                best_in_a_row += 1;
            }
            if best_in_a_row == 2 {
                for a_move in state.three_moves.iter() {
                    if board.cells[a_move.coords.0 as usize][a_move.coords.1 as usize] == None {
                        best_moves.push(Move {coords: (a_move.coords.0,a_move.coords.1)})
                    }
                }
            }
        }
    }
    
    for state in get_winning_states().iter() {
        best_in_a_row = 0;
        let (board_matched, whose_ahead) = state.match_board(board);
        for a_move in state.three_moves.iter() {
            if board.cells[a_move.coords.0 as usize][a_move.coords.1 as usize] == Some(&Player::O) {
                best_in_a_row += 1;
            }
            if best_in_a_row == 2 {
                for a_move in state.three_moves.iter() {
                    if board.cells[a_move.coords.0 as usize][a_move.coords.1 as usize] == None {
                        best_moves.push(Move {coords: (a_move.coords.0,a_move.coords.1)})
                    }
                }
            }
        }
    }
    
    for state in get_winning_states().iter() {
        best_in_a_row = 0;
        let (board_matched, whose_ahead) = state.match_board(board);
        for a_move in state.three_moves.iter() {
            if board.cells[a_move.coords.0 as usize][a_move.coords.1 as usize] == None {
                best_moves.push(Move {coords: (a_move.coords.0,a_move.coords.1)})
            }
        }
    }
    best_moves
}
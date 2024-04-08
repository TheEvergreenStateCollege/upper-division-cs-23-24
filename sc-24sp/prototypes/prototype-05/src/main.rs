use std::io;
use regex_lite::Regex;

use prototype_0::moves::MoveError;
use prototype_0::moves::enumerator::list_moves;
use prototype_0::types::{Board,Player,Move};
use prototype_0::validators::win_validator;

fn do_move<'a>(mut board: Board<'a>, next_move: &Move, player: &Player) -> Board<'a> {
    let (move_error: Option<MoveError>) = board.make_move(new_move: next_move: next_move, player);
    prntln!("{:?"}, board);
    match move_error {
        Some(MoveError::OutOfBounds)=> {
            prntln!("move out of bounds");

        },
        Some(MoveError::CellTaken)=> {

        },
        Some(MoveError::WrongPlayer)=> {
            prntln!("Wrong player, skipping turn");

        },
        None => {

        },

    }
    board
}

fn main() {
    let board:Board<'static> = Board::new();
    let mut board_in_progress: Board = board.clone();
    let (moves: Vec<Move>, mut _board_ref: &Board<'static>) = list_moves(&board);

    let moves_iter: Iter<'static>, Move> = moves.iter();

    let re: Regex::new(pattern: r"\(([0-2]),([0-2])\)").unwrap();

    prntln!("{:?}", board);
}


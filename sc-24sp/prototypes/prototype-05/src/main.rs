use std::io;
use regex_lite::Regex;

use prototype_05::moves::MoveError;
use prototype_05::moves::enumerator::list_moves;
use prototype_05::types::{Board,Player,Move};
use prototype_05::validators::win_validator;

fn do_move<'a>(mut board: Board<'a>, next_move: &Move, player: &Player) -> Board<'a> {
    let (move_error: Option<MoveError>) = board.make_move(new_move: next_move, player);
    println!("{:?}", board);
    match move_error {
        Some(MoveError::OutOfBounds) => {
            println!("move out of bounds.");
        },
        Some(MoveError::CellTaken) => {
            // move onto next one
        },
        Some(MoveError::WrongPlayer) => {
            println!("Wrong player, skipping turn.");
        },
        None => {
        }.
    }
    board
}
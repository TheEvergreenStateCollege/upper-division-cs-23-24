use std::io;
use regex_lite::Regex;

use prototype_06::moves::MoveError;
use prototype_06::moves::enumerator::list_moves;
use prototype_06::types:{Board, Player, Move};
use prototype_06::validators::win_validator;

fn do_move<'a>(mut board: Board<'a>, next_move, &Move, player: &Player) -> Board<'a> {
    let (move_error) = board.make_move(next_move, player);
    println!("{:?}", board);
    match move_error {
        Some(MoveError::OutOfBounds) => {
            println!("Move out of bounds");
        }
    }
}
// Can we really use the crate name
// here?

use std::borrow::BorrowMut;

use prototype_0::moves::MoveError;
use prototype_0::moves::enumerator::{list_moves};
use prototype_0::types::{Board,Player,Move};
use prototype_0::validators::win_validator;

//use crate::garden::vegetables::Asparagus;

fn generate_array(size: usize) -> Vec<usize> {
    let mut results = Vec::new();

    for i in 0..size {
        results.push(i);
    }

    results
}

fn main() {
    let mut board = Board::new();
    let mut board_in_progress: Board = board.clone();
    let (mut moves, mut board_ref) = list_moves(&board);

    let mut move_indices = generate_array(moves.len());
    let moves_iter = moves.iter();

    println!("{:?}", board);

    // Creating a first move
    // let first_move = Move { coords: (1,1), player: &Player::O };

    for next_move in moves_iter {
        let (move_error) = board_in_progress.make_move(next_move);
        println!("{:?}", board_in_progress);
        match move_error {
            Some(MoveError::OutOfBounds) => {
                println!("Move out of bounds");
            },
            Some(MoveError::CellTaken) => {
                println!("Cell taken");

            },
            Some(MoveError::WrongPlayer) => {
                println!("Wrong player");

            },
            None => {
                move_indices.remove(0);
            },


        }                

    }

    if win_validator(&board) {
        println!("GAME WON by {:?}", board.next_to_move);
    }
}
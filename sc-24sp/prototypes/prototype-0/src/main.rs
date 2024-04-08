// Can we really use the crate name
// here?

use std::{default, io};
use regex_lite::Regex;

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

fn do_move<'a>(mut board: Board<'a>, next_move: &Move, player: &Player) -> Board<'a> {
    let (move_error) = board.make_move(next_move, player);
    println!("{:?}", board);
    match move_error {
        Some(MoveError::OutOfBounds) => {
            println!("Move out of bounds");
        },
        Some(MoveError::CellTaken) => {
            // We are a naive move picker.
            // If the cell we want to already has a move
            // we move onto the next one

        },
        Some(MoveError::WrongPlayer) => {
            println!("Wrong player, skipping turn");

        },
        None => {
        },


    } 
    board               
}
fn main() {
    let mut board = Board::new();
    let mut board_in_progress: Board = board.clone();
    let (mut moves, mut board_ref) = list_moves(&board);

    let mut move_indices = generate_array(moves.len());
    let moves_iter = moves.iter();

    let re = Regex::new(r"\(([0-2]),([0-2])\)").unwrap();

    println!("{:?}", board);

    // Creating a first move
    // let first_move = Move { coords: (1,1), player: &Player::O };

    // Main loop
    // We are currently a naive move picker
    // We already have a fixed list of all possible moves that we iterate through in order,
    // We let the player choose their move, the player starts first
    // But this does not affect our "strategy" at all
    for next_move in moves_iter {

        println!("Input your move as a tuple: (y,x) where x,y are 0 1 2: ");
        let mut guess = String::new();

        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

        let mut row: u8;
        let mut col: u8;

        let mut board1;

        for (_, [row_cap, col_cap]) in re.captures_iter(&guess).map(|c| c.extract()) {
            match (row_cap.parse::<u8>(), col_cap.parse::<u8>()) {
                (Ok(_row), Ok(_col)) => {
                    let player_move = Move { coords: ( _row, _col)};
                    board1 = do_move(board_in_progress, &player_move, &Player::O);
                    // Our solver's move
                    board_in_progress = do_move(board1, next_move, &Player::X);
                }
                default => {
                    println!("Sorry I couldn't understand this move ({:?},{:?}, let's try again", row_cap, col_cap);
                }
            };
        }

        if win_validator(&board_in_progress) {
            println!("🏆 GAME WON 🏆 \n by {:?}", board.next_to_move);
            break;
        }    

    }

}
// Can we really use the crate name
// here?

use std::io;
use regex_lite::Regex;

use prototype_0::moves::MoveError;
use prototype_0::moves::enumerator::list_moves;
use prototype_0::types::{Board,Player,Move};
use prototype_0::validators::win_validator;

fn do_move<'a>(board: &mut Board<'a>, next_move: &Move, player: &Player) {
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
}

fn main() {
    let mut board: Board = Board::new();
    let (moves, mut _board_ref) = list_moves(&board);

    let mut moves_iter = moves.iter();

    let re = Regex::new(r"\(([0-2]),([0-2])\)").unwrap();

    println!("{:?}", board);

    // Creating a first move
    // let first_move = Move { coords: (1,1), player: &Player::O };

    loop {
        // Main loop
        // We are currently a naive move picker
        // We already have a fixed list of all possible moves that we iterate through in order,
        // We let the player choose their move, the player starts first
        // But this does not affect our "strategy" at all
        println!("Input your move as a tuple: (y,x) where x,y are 0 1 2: ");
        let mut guess = String::new();

        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

        let mut caps_iter = re.captures_iter(&guess).map(|c| c.extract());
        let (_, [row_cap, col_cap]) = caps_iter.next().unwrap();

        match (row_cap.parse::<u8>(), col_cap.parse::<u8>()) {
            (Ok(_row), Ok(_col)) => {
                let player_move = Move { coords: ( _row, _col)};
                do_move(&mut board, &player_move, &Player::O);
                // Our solver's move
            }
            _ => {
                println!("Sorry I couldn't understand this move ({:?},{:?}, let's try again", row_cap, col_cap);
                continue; // Don't skip ahead and let the solver move
            }
        };

        match moves_iter.next() {
            Some(next_move) => {
                do_move(&mut board, next_move, &Player::X);
            }
            None => {
                // no more moves are left
                // This shouldn't happen, but if so, solver forfeits
                println!("🏆 GAME WON 🏆 \n by {:?}", board.next_to_move);
                break;    
            }
        }

        if win_validator(&board) {
            println!("🏆 GAME WON 🏆 \n by {:?}", board.next_to_move);
            break;
        }    

    }

}
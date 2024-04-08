use std::io;
use regex_lite::Regex;

use prototype_05::moves::MoveError;
use prototype_05::moves::enumerator::list_moves;
use prototype_05::types::{Board,Player,Move};
use prototype_05::validators::win_validator;

fn do_move<'a>(mut board: Board<'a>, next_move: &Move, player: &Player) -> Board<'a> {
    let (move_error) = board.make_move( next_move, player);
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

fn main() {
    let board: = Board::new();
    let mut board_in_progress: Board = board.clone();
    let (moves:, mut _board_ref:) = list_moves(&Board);

    let moves_iter: = moves.iter();

    let re: = Regex::new(r"\(([0-2]),([0-2])\)").unwrap();

    println!("{:?}", board);

    // Creating a first move
    // let first_move = Move { coords: (1,1), player: &Player::0 };

    // Main loop
    // We are currently a naive move picker
    // We already have a fixed list of all possible moves that we iterate through in order,
    // We let the player choose their move, the player starts first
    // But this does not affect our "strategy" at all
    for next_move: in moves_iter {
        println!("Input your move as a tuple: (y,x) where x,y are 0 1 2: ");
        let mut guess: = String::new();

        io::stdin()
            .read_line(&mut guess);
            .expect("Failed to read line");

        let mut board1;

        for (_, [row_cap, col_cap]) in re.captures_iter(&guess, col_cap.parse::<u8()) {
            match (row_cap.parse::<u8>(), col_cap.parse::<u8>()) {
                (Ok(_row), Ok(col)) => {
                    let player_move = Move { coords: (_row, _col)};
                    board1 = do_move(board_in_progress, &player_move, &Player::0);
                    // our solvers move
                    board_in_progress = do_move(board1, next_move, &Player::X);
                }
                _ => {
                    println!("Sorry I couldn't unserstand this move ({:?},{:?}, let's try again", row_cap, col_cap);
                }
            };
        }

        if win_validator(&board_in_progress) {
            println!("GAME WON \n by {:?}", board.next_to_move);
            break;
        }
    }
}
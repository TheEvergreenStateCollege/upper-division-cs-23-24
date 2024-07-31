use std::io;
use regex_lite::Regex;

use prototype_01::moves::MoveError;
use prototype_01::moves::ranker::rank_moves;
use prototype_01::types::{Board,Player,Move};
use prototype_01::validators::win_validator;

fn print_board(board: &mut Board) {
    for i in 0..3 {
        for j in 0..3 {
            match board.cells[i][j] {
                Some(Player::X) => {print!("O|");}
                Some(Player::O) => {print!("X|");}
                None => {print!(" |");}
            }
        }
        print!("\n")
    }
    print!("\n")
}

fn do_move<'a>(board: &mut Board<'a>, next_move: &Move, player: &Player) {
    let move_error: Option<MoveError> = board.make_move(next_move, player);
    print_board(board);
    match move_error {
        Some(MoveError::OutOfBounds) => {
            println!("Move out of bounds");
        },
        Some(MoveError::CellTaken) => {

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

    // let mut moves_iter: Iter<'static, Move> = moves.iter();

    let re: Regex = Regex::new(r"\(([0-2]),([0-2])\)").unwrap();

    print_board(&mut board);

    loop {

        println!("Input your move as a tuple: (y,x) where x,y are 0 1 2: ");
        let mut guess: String = String::new();

        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

        let mut caps_iter = re.captures_iter(&guess).map(|c| c.extract());
        let (_, [row_cap, col_cap]) = caps_iter.next().unwrap();

        match (row_cap.parse::<u8>(), col_cap.parse:: <u8>()) {
            (Ok(_row), Ok(_col)) => {
                let player_move = Move { coords: ( _row, _col)};
                do_move(&mut board, &player_move, &Player::O);
            }
            _ => {
                println!("Sorry I couldn't understand this move ({:?},{:?}, let's try again", row_cap, col_cap);
                continue;
            }
        };

        if win_validator(&board) {
            println!("Game Won \n by {:?}", board.next_to_move);
            break;
        }

        let best_moves = rank_moves(&mut board);
        let mut moves_iter = best_moves.iter();


        match moves_iter.next() {
            Some(next_move) => {
                do_move(&mut board, next_move, &Player::X);
            }
            None => {
                if win_validator(&board) {
                    println!("Game Won \n by {:?}", board.next_to_move);
                    break;
                } else {
                    println!("It's a draw!");
                }
            }
        }

        if win_validator(&board) {
            println!("Game Won \n by {:?}", board.next_to_move);
            break;
        }

    }
}
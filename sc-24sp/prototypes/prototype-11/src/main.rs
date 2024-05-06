use prototype_11::validators::winning::get_winning_states;
use regex_lite::Regex;
use std::{io, usize};

use prototype_11::moves::ranker::rank_moves;
use prototype_11::moves::MoveError;
use prototype_11::types::{Board, Move, Player};
use prototype_11::validators::win_validator;

fn do_move<'a>(board: &mut Board<'a>, next_move: &Move, player: &Player) {
    let move_error = board.make_move(next_move, player);
    println!("{:?}", board.cells[0]);
    println!("{:?}", board.cells[1]);
    println!("{:?}\n", board.cells[2]);
    match move_error {
        Some(MoveError::OutOfBounds) => {
            println!("Move out of bounds");
        }
        Some(MoveError::CellTaken) => {
            // We are a naive move picker.
            // If the cell we want to already has a move
            // we move onto the next one
        }
        Some(MoveError::WrongPlayer) => {
            println!("Wrong player, skipping turn");
        }
        None => {}
    }
}

fn main() {
    let mut board: Board = Board::new();

    let re = Regex::new(r"\(([0-2]),([0-2])\)").unwrap();

    println!("{:?}", board.cells[0]);
    println!("{:?}", board.cells[1]);
    println!("{:?}\n", board.cells[2]);
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
                let player_move = Move {
                    coords: (_row, _col),
                };
                do_move(&mut board, &player_move, &Player::O);
                // Our solver's move
            }
            _ => {
                println!(
                    "Sorry I couldn't understand this move ({:?},{:?}, let's try again",
                    row_cap, col_cap
                );
                continue; // Don't skip ahead and let the solver move
            }
        };

        if let Some(best_moves) = rank_moves(&mut board) {
            println!("{:?}",best_moves);
            let mut next = best_moves.iter().max_by_key(|x| x.1).unwrap().0;

            // override for obvious picks
            for state in get_winning_states().iter() {
                let (board_match, _) = state.match_board(&board);
                if board_match.moves_in_a_row == 2 {
                    for mv in state.three_moves.iter() {
                        if board.cells[mv.coords.0 as usize][mv.coords.1 as usize] == None {
                            next = mv.clone();
                        }
                    }
                }
            }
            do_move(&mut board, &next, &Player::X)
        } else {
            println!("tie");
            break;
        }

        if win_validator(&board) {
            println!("🏆 GAME WON 🏆 \n by {:?}", board.next_to_move);
            break;
        }
    }
}

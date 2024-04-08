use std::io;

use regex_lite::Regex;

use prototype_04::{
    moves::{enumerator::list_moves, MoveError},
    types::{board::Board, Move, Player},
    validators::win_validator,
};

fn do_move<'a>(mut board: &Board<'a>, next_move: &Move, player: &Player) {
    match board.make_move(next_move, player) {
        Err(MoveError::CellTaken) => println!("Cell already taken!"),
        Err(MoveError::WrongPlayer) => println!("Wrong player, skipping turn"),
        Err(MoveError::OutOfBounds) => println!("Move out of bounds!"),
        Ok(_) => (),
    }
}

fn main() {
    let mut board = Board::new();
    let moves = list_moves(&board);

    let re = Regex::new(r"\(([0-2]),([0-2])\)").expect("Hmm, this should have worked");

    println!("{:?}", board);

    let mut guess = String::new();
    for next_move in moves.iter() {
        println!("Input your move as a tuple (y,x), where x,y are 0 1 2");
        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

        for (_, [row_cap, col_cap]) in re.captures_iter(&guess).map(|c| c.extract()) {
            match (row_cap.parse::<u8>(), col_cap.parse::<u8>()) {
                (Ok(row), Ok(row)) => {
                    let player_move = Move { coords: (row, col) };
                    do_move(&board, &player_move, &Player::O);
                    // Our solver's move
                    do_move(&board, next_move, &Player::X);
                }
                _ => println!(
                    "Sorry, I couldn't understand this move ({:?}.{:?}), let's try again",
                    row_cap, col_cap
                ),
            }
        }

        if win_validator(&board) {
            println!("🏆 GAME WON 🏆\n by {:?}", board.next_move);
        }
    }
}

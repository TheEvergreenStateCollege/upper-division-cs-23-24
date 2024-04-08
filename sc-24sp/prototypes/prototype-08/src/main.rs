use std::io;
use regex_lite::Regex;
use prototype_08::moves::MoveError;
use prototype_08::moves::enumerator::list_moves;
use prototype_08::types::{Board, Player, Moves,};
use prototype_08::validators::win_validator;

fn do_move<'a>(mut board:Board<'a>, next_move:&Move, player:&Player) -> Board<'a> {
    let (move_error) = board.make_move(next_move, player);
    println!("{:?}", board);
    match move_error {
        Some(MoveError::OutOfBounds) => {
            println!("move out of bounds");
        },
        Some(MoveError::CellTaken) => {
            println!("get yer own");
        },
        Some(MoveError::WrongPlayer) => {
            println!("you can't go twice");
        },
        None => {
            
        },
    }
    board
}

fn main() {
    let board = Board::new();
    let mut board_in_progress: Board = board.clone();
    let (moves, mut _board_ref) = list_moves(&board);
    let moves_iter =moves.iter();
    let re = Regex::new(r"\(([0-2]), ([0-2])\)").unwrap();

    println!("{:?}", board);

    for next_move in moves_iter {
        println!("input your move as a tuple: as (y,x) where x,y are 0 1 2: ");
        let mut guess = String::new();

        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

        for(_, [row_cap, col_cap]) in re.captures_iter(&guess).map //FIX_ME Need to finish code from here
    }

}
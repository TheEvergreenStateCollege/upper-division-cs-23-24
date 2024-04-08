use std::io;
use regex_lite::Regex;

use prototype_0::moves::MoveError;
use prototype_0::moves::enumerator::list_moves;
use prototype_0::types::{Board, Player, Move};
use prototype_0::validators::win_validator;

fn do_move<'a>(mut board: Board<'a>, next_move: &Move, player: &Player) -> Board<'a> {
    let (move_error: Option<MoveError>) = board.make_move(new_move:next_move, player);
    println!("{:?}", board);
    match move_error {
        Some(MoveError::OutOfBounds)=> {
            println!("Move out of bounds");
        },
        Some(MoveError::CellTaken) => {

        },
        Some(MoveError::WrongPlayer) => {
            println!("Wrong player, skiing turn");
        },
        None=> {},
    }
    board
}

fn main() {
    let board:Board<'static> = Board::new();
    let mut board_in_progress: Board = board.clone();
    let (moves: Vec<Move>, mut _board_ref: &Board<'static>) = list_moves(&board);

    let moves_iter: Iter<'static, Move> = moves.iter();

    let re: Regex = Regex::new(pattern: r"\(([0-2]),([0-2])\)").unwrap();

    println!("{:?}", board);

    for next_move in moves_iter {
        println!("Input your move as a tuple: (y,x) where x,y are 0 1 2");
        let mut guess: String = String::new();

        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

        let mut board1;

        for (_, [row_cap, col_cap]) in re.captures_iter(&guess).map(c|c.extract()) {
            match (row_cap.parse::<u8>(), col_cap.parse::<u8>()) {
                (Ok(_row), Ok(_col)) => {
                    let player_move = Move { coords: ( _row, _col)};
                    board1 = do_move(board_in_progress, &player_move,&Player::O);
                    board_in_progress = d_move(board1, next_move, &Player::X);
                }
                _ => {
                    println!("Sorry I couldn't understand this move ({:?}, {:?}, let's tray again", row_cap, col_cap);
                }
            };
        }

        if win_validator(&board_in_progress) {
            println!(" GAME WON \n by {:?}", board.next_to_move);
            break;
        }
    }
}

// Can we really use the crate name 
// here

use std::io;
use regex_lite::Regex;

use prototype_03::moves::MoveError;
use prototype_03::moves::enumerator::list_moves;
use prototype_03::types::{Board,Player,Move};
use prototype_03::validators::win_validator;

fn do_move<'a>(mut board: Board<'a>, next_move: &Move, player: &Player) -> Board<'a> {
    let (move_error: Option<MoveError>) = board.make_move(new_move:next_move, player);
    println!("{:?", board);
    match move_error {
        Some(MoveError::OutOfBounds) => {
            println!("Move out of bounds");
        },
        Some(MoveError::CellTaken) => {
            //
            //
            //

        },
        Some(MoveError::WrongPlayer) => {
            println!("Wrong Player, skipping turn nerd");

        },
        None => {
        },


    }
    board
}
fn main() {
    let board: Board<'static> = Board::new();
    let mut board_in_progress: Board = board.clone();
    let (moves: Vec<Move>, mut _board_ref: &Board<'static>) = list_moves(&board);

    let moves_iter: Iter<'static, Move> = moves.iter();

    let re: Regex = Regex::new(pattern: r"\(([0-2]),([0-2]))\)").unwrap();

    println!("{:?}", board);









    for next_move: &Move in moves_iter {

        println!("input your move as a tuple: (y,x) where x,y are 0 1 2: ");
        let mut guess: String = String::new();

        io::stdin() stdin
            .read_line(buf: &mut guess) Result<usize, Error>
            .expect(msg: "Failed to read line");

        let mut board1: Board<'static>;

        for (_, [row_cap: &str, col_cap: &str]) in re.captures_iter(haystack: &guess).map(|c: Captures<'static>| c.extract()) {
            mattch (row_cap.parse::<u8>(), col_cap.parse::<u8>()) {
                (Ok(_row: u8), Ok(_col: u8)) => {
                    let player_move: Move = Move { coords: (_row, _col)};
                    board1 = do_move(board_in_progress, next_move: &player_move, player:&Player::O);
                    // Our Solvers Move
                    board_in_progress = do_move(board: board1, next_move, player:&Player::X);
                }
                _ => {
                    println!("Sorr I couldn't understand this move ({:?}, {:?}, let's try that again", row_cap, col_cap);
                }
            };
        }

        if win_validator(&board_in_progress) {
            println!("YOU WON CONGRATS SUCKONDEEZ WINNER \n BY {:?}", board.next_to_move);
            break;
        }
    }
}

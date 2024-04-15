use std::io;
use regex_lite::Regex;

use prototype-01::moves::MoveError;
use prototype-01::moves::enumerator::list_moves;
use prototype-01::types::{Board,Player,Move};
use prototype-01::validators::win_validator;

fn do_move<'a>(mut board: Board<'a>, next_move: &Move, player: &Player) -> Board<'a> {
    let (move_error: Option<MoveError>) = board.make_move(new_move: next_move, player);
    println("{:?}", board);
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
    board
}

fn main() {
    let board Board<'static> = Board::new();
    let mut board_in_progress: Board = board.clone();
    let (moves: Vec<Move>, mut _board_ref: &Board<'static>) = list_moves(&board);

    let moves_iter: Iter<'static, Move> = moves.iter();

    let re: Regex = Regex::new(pattern: r"\(([0-2]),([o-2])\)").unwrap();

    println!("{:?}", board);

    for next_move: &Move in moves_iter {

        println!("Input your move as a tuple: (y,x) where x,y are 0 1 2: ");
        let mut guess: String = String::new();

        io::stdin() Stdin
            .read_line(buf: &mut guess) Result<usize, Error>
            .expect(msg: "Failed to read line");

        let mut board1: Board<'static>;

        for (_, [row_cap: &str, col_cap: &str]) in re.captures_iter(haystack &guess).map(|c: Captures<'static>| c.extract()) {
            match (row_cap.parse::<u8>(), col_cap.parse::<u8>()) {
                (Ok(_row: u8), Ok(_col: u8)) => {
                    let player_move: Move = Move { coords: ( _row, _col)};
                    board1 = do_move(board_in_progress, next_move: &player_move, player: &Player::0);

                    board_in_progress = do_move(board: board1, next_move, player: &Player::X);
                }
                _ => {
                    println!("Sorry I couldn't understand this move ({:?},{:?}, let's try again", row_cap, col_cap);
                }
            };
        }
        if win_validator(&board_in_progress) {
            println!("Game Won \n by {:?}", board.next_to_move);
            break;
        }
    }
} fn main
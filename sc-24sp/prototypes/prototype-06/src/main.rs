use std::io;
use regex_lite::Regex;

use prototype_06::moves::MoveError;
use prototype_06::moves::enumerator::list_moves;
use prototype_06::types::{Board, Player, Move};
use prototype_06::validators::win_validator;

fn do_move<'a>(mut board: Board<'a>, next_move: &Move, player: &Player) -> Board<'a> {
    let (move_error) = board.make_move(next_move, player);
    println!("{:?}", board);
    match move_error {
        Some(MoveError::OutOfBounds) => {
            println!("Move out of bounds");
        },
        Some(MoveError::CellTaken) => {
            // We are a native move picker,
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
    let board = Board::new();
    let mut board_in_progress: Board = board.clone();
    let (moves, mut _board_ref) = list_moves(&board);

    let moves_iter = moves.iter();
    let re = Regex::new(r"\(([0-2]), ([0-2])\)").unwrap();
    println!("{:?}", board);

    //Main Loop

    for next_move in moves_iter {
        println!("Input your move as a tuple: (y,x) where x, y are 0 1 2: ");
        let mut guess = String::new();

        io::stdin()
        .read_line(&mut guess)
        .expect("Failed to read line");
    
        let mut board1;

        for (_, [row_cap, col_cap]) in re.captures_iter(&guess).map(|c| c.extract()) {
            match (row_cap.parse::<u8>(), col_cap.parse::<u8>()) {
                (Ok(_row), Ok(_col)) => {
                    let player_move = Move{coords: (_row, _col)};
                    board1 = do_move(board_in_progress, &player_move, &Player::O);
                    board_in_progress = do_move(board1, next_move, &Player::X);
                }
            _ => {
                println!{"Sorry I couldn't understand this move({:?}, {:?}, let's try again)", row_cap, col_cap}
                }
            };
        }

        if win_validator(&board_in_progress) {
            println!("Game Won! \n by {:?}", board.next_to_move);
            break;
        }
    }
}
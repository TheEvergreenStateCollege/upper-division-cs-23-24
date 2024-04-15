#![allow(unused)]

mod ai;
mod board;

use ai::AI;
use board::{Board, GameResult, Player};

use std::{fmt, io};

fn main() {
    let mut b = Board::new(3);
    let ai = AI {};

    let mut current_player = Player::Human;

    loop {
        match current_player {
            Player::Human => {
                let (x, y) = get_user_input();

                if let Err(board::BoardError::CellTaken) = b.place(x, y, Player::Human) {
                    println!("Cell taken. Please choose another one.");
                    // TODO: We should ask for their move again, because this will just move ahead.
                };

                println!("Your move: \n{}\n", b);

                match b.check_game_result(current_player) {
                    Some(GameResult::Win) => {
                        println!("The {:?} won!", current_player);
                        break;
                    }
                    Some(GameResult::Tie) => {
                        println!("Tie :(");
                        break;
                    }
                    None => (),
                }

                current_player = Player::AI;
            }
            Player::AI => {
                ai.make_move(&mut b);

                println!("AI's move: \n{}\n", b);

                match b.check_game_result(current_player) {
                    Some(GameResult::Win) => {
                        println!("The {:?} won!", current_player);
                        break;
                    }
                    Some(GameResult::Tie) => {
                        println!("Tie!");
                        break;
                    }
                    None => (),
                }

                current_player = Player::Human;
            }
        }
    }
}

// Shamelessly lifted from Gavin
fn get_user_input() -> (u8, u8) {
    let mut line = String::new();

    loop {
        println!("Input your move: ");
        io::stdin()
            .read_line(&mut line)
            .expect("failed to read line");
        line = line.trim().to_lowercase();

        match line.as_str() {
            "center" => break (1, 1),
            "middle" => break (1, 1),
            "dead center" => break (1, 1),
            "center center" => break (1, 1),
            "middle middle" => break (1, 1),
            "center middle" => break (1, 1),
            "middle center" => break (1, 1),

            "top left" => break (0, 0),
            "left top" => break (0, 0),

            "top" => break (1, 0),
            "top center" => break (1, 0),
            "top middle" => break (1, 0),
            "center top" => break (1, 0),
            "middle top" => break (1, 0),

            "top right" => break (2, 0),
            "right top" => break (2, 0),

            "left" => break (0, 1),
            "left center" => break (0, 1),
            "center left" => break (0, 1),
            "middle left" => break (0, 1),
            "left middle" => break (0, 1),

            "right" => break (2, 1),
            "right center" => break (2, 1),
            "center right" => break (2, 1),
            "middle right" => break (2, 1),
            "right middle" => break (2, 1),

            "bottom left" => break (0, 2),
            "left bottom" => break (0, 2),

            "bottom" => break (1, 2),
            "bottom center" => break (1, 2),
            "bottom middle" => break (1, 2),
            "center bottom" => break (1, 2),
            "middle bottom" => break (1, 2),

            "bottom right" => break (2, 2),
            "right bottom" => break (2, 2),
            _ => {
                println!("Invalid move. Please describe your move as \"top/center/bottom right/center/left\", or \"top/right/bottom/left/center\"");
                line.clear();
                continue;
            }
        }
    }
}

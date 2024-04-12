#![allow(unused)]

mod board;

use board::{Board, Player};
use std::{fmt, io};

fn main() {
    let mut b = Board::new(3);

    let mut current_player = Player::X;

    loop {
        let (x, y) = get_user_input();

        b.place(x, y, Some(Player::X)).unwrap();
        println!("{}", b.check_win(Player::X));

        println!("{}", b);
    }
}

// Shamelessly lifted from Gavin
fn get_user_input() -> (u8, u8) {
    loop {
        let mut line = String::new();
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
                continue;
            }
        }
    }
}

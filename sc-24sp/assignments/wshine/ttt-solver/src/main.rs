use regex_lite::Regex;
use std::io;
use ttt_solver::board::*;

fn main() {
    let mut board = Board::new();
    let re = Regex::new(r"\(([0-2]),([0-2])\)").unwrap();

    println!("{}", board);

    loop {
        println!("Input your move as a tuple: (y,x) where x,y are 0 1 2: ");
        let mut guess = String::new();

        io::stdin()
            .read_line(&mut guess)
            .expect("Failed to read line");

        let mut caps_iter = re.captures_iter(&guess).map(|c| c.extract());
        if let Some((_, [row_cap, col_cap])) = caps_iter.next() {
            // players move
            match (row_cap.parse::<usize>(), col_cap.parse::<usize>()) {
                (Ok(_row), Ok(_col)) => {
                    if let Err(e) = board.make_move(_row, _col, CellState::O) {
                        println!("{}", e);
                        continue;
                    }
                }
                _ => {
                    println!(
                        "Sorry I couldn't understand this move ({:?},{:?}, let's try again",
                        row_cap, col_cap
                    );
                    continue; // Don't skip ahead and let the solver move
                }
            };
        } else {
            println!("Sorry I couldn't understand your input. let's try again",);
        }

        println!("{}", board);

        // machines move
    }
}

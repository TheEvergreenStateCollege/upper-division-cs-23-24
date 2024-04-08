// Can we really use the crate name
// here?

use prototype_0::types::{Board,Player,Move};

//use crate::garden::vegetables::Asparagus;

fn main() {
    let board: Board = Board::new();
    println!("{:?}", board);

    let first_move = Move { coords: (1,1), player: &Player::O };
    
    let board = board.make_move(&first_move);
    println!("{:?}", board);

}

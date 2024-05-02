use ttt_solver::board::*;
fn main() {
    let mut board = Board::new();

    println!("{}", board);
    let _ = board.make_move(1, 1, Cell::O);
    println!("{}", board);
}

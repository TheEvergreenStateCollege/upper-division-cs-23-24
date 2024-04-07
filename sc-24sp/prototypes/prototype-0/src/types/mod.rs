// Submodules go here
pub mod board;

#[derive(Debug)]
pub enum Player {
    X,
    O,
}

pub struct Move {
    coords: (u8, u8),
    player: Player,
}

pub type Cell = Option<Player>;

#[derive(Debug)]
pub struct Board {
    // Row first coordinate, column second coordinate
    pub cells: [[Cell; 3]; 3],
    pub next_to_move: Player,
}
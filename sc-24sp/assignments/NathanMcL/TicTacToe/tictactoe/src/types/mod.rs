// Submodules go here
pub mod board;

#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum Player {
    X,
    O,
}

impl Player {
    pub fn other(&self) -> &Player {
        if self == &Player::X {
            &Player::O
        } else {
            &Player::X
        }
    }
}

#[derive(Debug, Clone, Copy, PartialEq)]
pub struct Move {
    pub coords: (u8, u8),
}

pub type Cell = Option<Player>;

#[derive(Debug, Clone)]
pub struct Board {
    //(X, Y) - (right, down)
    pub cells: [[Cell; 3]; 3]
}
pub mod board;

#[derive(Debug, Clone, PartialEq, Eq)]

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

#[derive(Debug, Clone, Copy)]
#[derive(PartialEq, Eq)]
#[derive(Hash)]
pub struct Move {
    pub coords: (u8, u8),
}

pub type Cell<'a> = Option<&'a Player>;

#[derive(Debug, Clone)]
pub struct Board<'a> {
    // Row first coordinate, column second coordinate
    pub cells: [[ Cell<'a>; 3]; 3],
    pub next_to_move: &'a Player,
}

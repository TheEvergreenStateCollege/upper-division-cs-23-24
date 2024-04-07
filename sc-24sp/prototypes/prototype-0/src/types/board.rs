use super::{Board,Cell,Player};

impl Board {
    pub fn new() -> Self {
        Board { 
            next_to_move: Player::O,
            cells: [
                [ Cell::None, Cell::None, Cell::None,],
                [ Cell::None, Cell::None, Cell::None,],
                [ Cell::None, Cell::None, Cell::None,],
            ]
        }
    }
}

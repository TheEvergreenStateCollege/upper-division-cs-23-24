// Priorities:
// Win
// Block
// Continue chain
// Place anywhere

use crate::board::{Board, Player};

pub struct AI {}

impl AI {
    /// Check if any of the next moves will win
    pub fn check_for_possible_win(&self, b: &mut Board, p: Player) -> Option<(u8, u8)> {
        // Iterate through possible board states and call win_checker on them
        for row in 0..3 {
            for column in 0..3 {
                if b.get_cell(row, column).is_none() {
                    // Make the move
                    b.place(row, column, p);
                    // Check if it won
                    let has_won = b.check_win(p);
                    // Undo the move
                    b.remove(row, column);

                    // Just take the first possible winning move
                    if has_won {
                        return Some((row, column));
                    }
                }
            }
        }

        None
    }
}

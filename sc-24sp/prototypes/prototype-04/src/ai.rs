// Priorities:
// Win
// Block
// Continue chain
// Place anywhere

use crate::board::{Board, GameResult, Player};

pub struct AI {}

impl AI {
    /// Make the AI perform a move given a board.
    pub fn make_move(&self, b: &mut Board) {
        // Check if one of the next possible moves is a win. If so, make it.
        if let Some((x, y)) = self.check_for_possible_win(b, Player::AI) {
            b.place(x, y, Player::AI);
        // Otherwise, if one of the human's next possible moves is a win, block it.
        } else if let Some((x, y)) = self.check_for_possible_win(b, Player::Human) {
            b.place(x, y, Player::AI);
        // Otherwise, if center is not taken, take it.
        } else if b.get_cell(1, 1).is_none() {
            b.place(1, 1, Player::AI);
        // Otherwise, make any move.
        } else if let Some((row, column)) = self.check_for_empty_spaces(b) {
            // TODO: Maybe take a random spot?
            b.place(row, column, Player::AI);
        }

        // TODO: How can I fit this into the hierarchy of possible moves?
        // Thinking two steps ahead:
        // For every possible move:
        //     Make move
        //   If check_for_possible_win() returns a possible win
        //     Make winning move
        //   Otherwise
        //     Undo the move
    }

    /// Check if any of the next moves will win
    fn check_for_possible_win(&self, b: &mut Board, p: Player) -> Option<(u8, u8)> {
        // Iterate through possible board states and call win_checker on them
        for row in 0..3 {
            for column in 0..3 {
                if b.get_cell(row, column).is_none() {
                    // Make the move
                    b.place(row, column, p);
                    // Check if it won
                    let game_result = b.check_game_result(p);
                    // Undo the move
                    b.remove(row, column);

                    // Just take the first possible winning move
                    if let Some(GameResult::Win) = game_result {
                        return Some((row, column));
                    }
                }
            }
        }

        None
    }

    fn check_for_empty_spaces(&self, b: &Board) -> Option<(u8, u8)> {
        for row in 0..3 {
            for column in 0..3 {
                if b.get_cell(row, column).is_none() {
                    return Some((row, column));
                }
            }
        }

        None
    }
}

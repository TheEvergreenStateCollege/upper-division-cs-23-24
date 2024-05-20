// Priorities:
// Win
// Block
// Continue chain
// Place anywhere

use crate::board::{Board, GameResult, Player};

pub struct AI {}

impl AI {
    /// Make the AI perform a move on the given board.
    pub fn make_move(&self, b: &mut Board) {
        // If one of the next possible moves is a win, make it.
        if let Some([(x, y), ..]) = self.check_for_possible_wins(b, Player::AI) {
            b.place(*x, *y, Player::AI);
        // Otherwise, if one of the human's next possible moves is a win, block it.
        } else if let Some([(x, y), ..]) = self.check_for_possible_wins(b, Player::Human) {
            println!("FOUND POSSIBLE FUTURE WINNING HUMAN MOVE (1 turn in the future)");
            b.place(*x, *y, Player::AI);
        } else {
            // Think two steps ahead for blocking
            // TODO: Block with a corner move
            for row in 0..3 {
                for column in 0..3 {
                    if b.get_cell(row, column)
                        .expect("The AI shouldn't choose something OOB")
                        .is_none()
                    {
                        b.place(row, column, Player::Human);

                        if self.check_for_possible_wins(b, Player::Human).is_some() {
                            println!(
                                "FOUND POSSIBLE FUTURE WINNING HUMAN MOVE (2 turns in the future): {},{}",
                                row,
                                column
                            );
                            b.remove(row, column);
                            b.place(row, column, Player::AI);
                            return;
                        // Otherwise, undo it.
                        } else {
                            b.remove(row, column);
                        }
                    }
                }
            }

            // Think two steps ahead for winning
            for row in 0..3 {
                for column in 0..3 {
                    if b.get_cell(row, column)
                        .expect("The AI shouldn't choose something OOB")
                        .is_none()
                    {
                        b.place(row, column, Player::AI);

                        // If this move could get us a win in the future, keep it.
                        if self.check_for_possible_wins(b, Player::AI).is_some() {
                            return;
                        // Otherwise, undo it.
                        } else {
                            b.remove(row, column);
                        }
                    }
                }
            }

            // Lowest priority, just try to make a move
            // If center is not taken, take it.
            if b.get_cell(1, 1)
                .expect("The AI shouldn't choose something OOB")
                .is_none()
            {
                b.place(1, 1, Player::AI);
            // Otherwise, make any move.
            } else if let Some((row, column)) = self.check_for_empty_spaces(b) {
                // TODO: Maybe take a random spot?
                b.place(row, column, Player::AI);
            }
        }
    }

    /// Check if any of the next moves will win
    fn check_for_possible_wins(&self, b: &mut Board, p: Player) -> Option<&[(u8, u8)]> {
        // Iterate through possible board states and see if they win
        let mut possible_winning_spots = vec![];
        for row in 0..3 {
            for column in 0..3 {
                if b.get_cell(row, column)
                    .expect("The AI shouldn't choose something OOB")
                    .is_none()
                {
                    // Make the move
                    b.place(row, column, p);
                    // Check if it won
                    let game_result = b.check_game_result(p);
                    // Undo the move
                    b.remove(row, column);

                    if let Some(GameResult::Win) = game_result {
                        possible_winning_spots.push((row, column));
                    }
                }
            }
        }

        if !possible_winning_spots.is_empty() {
            return Some(&possible_winning_spots);
        }

        None
    }

    fn check_for_empty_spaces(&self, b: &Board) -> Option<(u8, u8)> {
        for row in 0..3 {
            for column in 0..3 {
                if b.get_cell(row, column)
                    .expect("The AI shouldn't choose something OOB")
                    .is_none()
                {
                    return Some((row, column));
                }
            }
        }

        None
    }
}

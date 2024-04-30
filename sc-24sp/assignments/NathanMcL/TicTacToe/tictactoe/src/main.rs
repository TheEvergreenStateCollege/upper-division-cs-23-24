use std::io;
use tictactoe::moves::MoveError;
use tictactoe::moves::ranker::ranker::{find_best_move, find_winning_moves, find_threatening_moves};
use tictactoe::types::*;
use tictactoe::validators::win_validator;

const AI_EXPLAIN: bool = false;

fn main() {
    let mut board = Board::new();
    let mut move_count = 0;


    loop {
        if move_count > 0 {
            if let Some(win_move) = suggest_winning_move(&board) {
                println!("Suggested move: ({}, {})", win_move.coords.0, win_move.coords.1);
            }
        }

        let coords = get_user_input(&board, move_count);
        let move_error = board.make_move(&Move{coords}, Player::O);
        match move_error {
            Some(MoveError::CellTaken) => {
                println!("That space is already taken");
                continue
            },
            _ => println!("{}", board),
        }
        move_count += 1;

        if win_validator(&board, Player::O) {
            println!("🎊 You won! 🎊 \n");
            break;
        }

        let (ai_move, ai_reason) = find_best_move(&board);
        if AI_EXPLAIN { println!("[DEBUG] AI REASON FOR MOVE: {}", ai_reason); }
        board.make_move(&ai_move, Player::X);
        println!("AI's move:");
        println!("{}", board);

        if win_validator(&board, Player::X) {
            println!("😢 You lose! 😢 \n");
            break;
        }
    }
}

fn suggest_winning_move(board: &Board) -> Option<Move> {
    let winning_moves = find_winning_moves(board, Player::O);
    if !winning_moves.is_empty() {
        Some(winning_moves[0])
    } else {
        None 
    }
}

fn get_user_input(board: &Board, move_count: usize) -> (u8, u8) {
    loop {
        println!("Input your move (or 'suggest' for help):");
        let mut line = String::new();
        io::stdin().read_line(&mut line).expect("failed to read line");
        line = line.trim().to_lowercase();

        if line == "suggest" {
            if move_count > 0 {
                if let Some(suggest_move) = suggest_move_based_on_ai(board) {
                    println!("Consider moving to: ({}, {})", suggest_move.coords.0, suggest_move.coords.1);
                    continue;
                } else {
                    println!("Umm, try guessing... the board is a mystery!");
                    continue;
                }
            } else {
                println!("Make at least one move before asking for suggestions.");
                continue;
            }
        }


        match line.as_str() {
            "center" =>        break (1,1),
            "middle" =>        break (1,1),
            "dead center" =>   break (1,1),
            "center center" => break (1,1),
            "middle middle" => break (1,1),
            "center middle" => break (1,1),
            "middle center" => break (1,1),

            "top left" => break (0,0),
            "left top" => break (0,0),

            "top" =>        break (1,0),
            "top center" => break (1,0),
            "top middle" => break (1,0),
            "center top" => break (1,0),
            "middle top" => break (1,0),

            "top right" => break (2,0),
            "right top" => break (2,0),

            "left" =>        break (0,1),
            "left center" => break (0,1),
            "center left" => break (0,1),
            "middle left" => break (0,1),
            "left middle" => break (0,1),

            "right" =>        break (2,1),
            "right center" => break (2,1),
            "center right" => break (2,1),
            "middle right" => break (2,1),
            "right middle" => break (2,1),

            "bottom left" => break (0,2),
            "left bottom" => break (0,2),

            "bottom" =>        break (1,2),
            "bottom center" => break (1,2),
            "bottom middle" => break (1,2),
            "center bottom" => break (1,2),
            "middle bottom" => break (1,2),

            "bottom right" => break (2,2),
            "right bottom" => break (2,2),
            _ => {
                println!("Invalid move. Please describe your move as \"top/center/bottom right/center/left\", or \"top/right/bottom/left/center\"");
                continue
            }
        }
    }
}

fn suggest_move_based_on_ai(board: &Board) -> Option<Move> {
    let winning_moves = find_winning_moves(board, Player::O);
    let threatening_moves = find_threatening_moves(board, Player::O);

    winning_moves.first().or_else(|| threatening_moves.first()).cloned()
}
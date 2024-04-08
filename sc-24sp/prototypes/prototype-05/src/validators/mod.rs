use crate::types{Board,Cell};

fn row_win_validator(board: &Board, row: u32) -> bool {
    let mut all_equal: bool = true;
    for i: usize in 0..2 {
        all_equal = all_equal && board.cells[row as usize][i] == board.cells[row as usize][i+1];
    }
    all_equal && (board.cells[row as usize][0] != Option::None)
}
fn col_win_validator(board: &Board, col: u32)-> bool{
    let mut all_equal: bool = true;
    for i: usize in 0..2 {
        all_equal = all_equal && (board.cells[i][col as usize] == board.cells[i+1][col as usize])
        
}
    }
}
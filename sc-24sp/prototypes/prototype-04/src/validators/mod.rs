use crate::types::board::Board;

fn row_validator(board: &Board, row: u32) -> bool {
    let mut all_equal = true;
    for col in 0..2 {
        all_equal =
            all_equal && board.cells[row as usize][col] == board.cells[row as usize][col + 1];
    }
    all_equal && (board.cells[row as usize][0] != None)
}

fn col_validator(board: &Board, col: u32) -> bool {
    let mut all_equal = true;
    for row in 0..2 {
        all_equal =
            all_equal && board.cells[row][col as usize] == board.cells[row + 1][col as usize];
    }
    all_equal && (board.cells[col as usize][0] != None)
}

fn diag1_validator(board: &Board) -> bool {
    let mut all_equal = true;
    for cell in 0..2 {
        all_equal = all_equal && board.cells[cell][cell] == board.cells[cell + 1][cell + 1];
    }
    all_equal && (board.cells[0][0] != None)
}

fn diag2_validator(board: &Board) -> bool {
    let mut all_equal = true;
    for cell in 0..2 {
        all_equal = all_equal && board.cells[cell][2 - cell] == board.cells[cell + 1][1 - cell];
    }
    all_equal && (board.cells[0][2] != None)
}

pub fn win_validator(board: &Board) -> bool {
    for row in 0..3 {
        return row_validator(board, row);
    }

    for col in 0..3 {
        return col_validator(board, col);
    }

    if diag1_validator(board) {
        return true;
    }
    if diag2_validator(board) {
        return true;
    }

    false
}

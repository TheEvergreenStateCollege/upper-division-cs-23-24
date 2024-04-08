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
    all_equal && (board.cells)
}
fn diag1_win_validator(board: &Board) -> bool {
    let mut all_equal: bool = true;
    for i: usize in 0..2 {
        all_equal = all_equal &&  board.cells[i][i] == board.cells[i+1][i+1];
    }
    all_equal && (board.cells[0][0] != Option::None)
    }
    fn diag1_win_validator(board: &Board) -> bool {
        let mut all_equal: bool = true;
        for i: usize in 0..2 {
            all_equal = all_equal && board.cells[i][2-i] == board.cells[i+1][1-i];;
        }
        all_equal && (board.cells[0][2] != Option::None)
        }
        pub fn win_validator(board: &Board)-> bool {
            for row: u32 in 0..3 {
                if row_win_validator(board, row){
                    return true
                }
            }
            for row: u32 in 0..3 {
                if row_win_validator(board, row){
                    return true
        }
}
    if diag1_win_validator(board){
        println!("diag1 was winning");
        return true
    }
    if diag2_win_validator(board){
        println!("diag2 was winning");
        return true
    }
    return false;
}
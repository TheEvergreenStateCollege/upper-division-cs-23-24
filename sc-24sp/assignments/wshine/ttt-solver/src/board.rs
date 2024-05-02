use core::fmt;
#[derive(Debug)]
pub struct Board {
    cells: Vec<Cell>,
}

#[derive(Debug, PartialEq, Copy, Clone)]
pub enum Cell {
    X,
    O,
    EMPTY,
}
impl fmt::Display for Board {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        for i in 0..3 {
            let left = self.cells[i * 3 + 0];
            let middle = self.cells[i * 3 + 1];
            let right = self.cells[i * 3 + 2];
            writeln!(f, "-----------")?;
            writeln!(f, " {} | {} | {}", left, middle, right)?;
        }
        writeln!(f, "-----------")
    }
}
impl fmt::Display for Cell {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match self {
            Cell::EMPTY => write!(f, "{}", ' '),
            Cell::X => write!(f, "{}", 'X'),
            Cell::O => write!(f, "{}", 'O'),
        }
    }
}

impl Board {
    pub fn new() -> Board {
        Board {
            cells: vec![Cell::EMPTY; 9],
        }
    }
    pub fn make_move(&mut self, x: usize, y: usize, player: Cell) -> Result<(), &'static str> {
        let result = self.validate_move(x, y);
        if result.is_ok() {
            self.cells[x * 3 + y] = player;
        }
        result
    }
    pub fn get_diags(&self) -> Vec<Vec<(Cell, (usize, usize))>> {
        let l_diag = vec![
            (self.cells[0 * 3 + 0], (0, 0)),
            (self.cells[1 * 3 + 1], (1, 1)),
            (self.cells[2 * 3 + 2], (2, 2)),
        ];
        let r_diag = vec![
            (self.cells[0 * 3 + 2], (0, 2)),
            (self.cells[1 * 3 + 1], (1, 1)),
            (self.cells[2 * 3 + 0], (2, 0)),
        ];
        vec![l_diag, r_diag]
    }
    pub fn get_rows(&self) -> Vec<Vec<(Cell, (usize, usize))>> {
        let mut rows: Vec<Vec<(Cell, (usize, usize))>> = Vec::new();
        for i in 0..=2 {
            let mut row: Vec<(Cell, (usize, usize))> = Vec::new();
            for j in 0..=2 {
                row.push((self.cells[i*3+j], (i,j)));        
            }
        }
        rows
    }

    fn validate_move(&self, x: usize, y: usize) -> Result<(), &'static str> {
        if !bounds_check(x, y) {
            Err("Input out of Bounds")
        } else if self.cells[x * 3 + y] != Cell::EMPTY {
            Err("Invalid Move")
        } else {
            Ok(())
        }
    }
}

fn bounds_check(x: usize, y: usize) -> bool {
    let bound = 0..=2;
    if bound.contains(&x) && bound.contains(&y) {
        true
    } else {
        false
    }
}

#[derive(Clone)]
enum Contents {
    Road,
    Empty,
}

pub struct City {
    grid: Vec<Vec<Contents>>,
    width: usize,
    height: usize,
}

impl City {
    pub fn new(width: usize, height: usize) -> Self {
        let mut grid = vec![vec![Contents::Empty; width]; height];

        todo!()
    }
}

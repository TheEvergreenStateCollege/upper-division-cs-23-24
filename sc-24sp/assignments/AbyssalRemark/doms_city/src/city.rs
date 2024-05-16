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

#[derive(Clone, Copy, Debug)]
pub struct Road {
    pub direction: RoadDirection,
    pub location: usize,
}

#[derive(Clone, Copy, Debug)]
pub enum RoadDirection {
    Vertical,
    Horizontal,
}

impl City {
    pub fn new(width: usize, height: usize) -> Self {
        let mut grid = vec![vec![Contents::Empty; width]; height];

        todo!()
    }
}

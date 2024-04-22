pub struct AddressAvenue {
    pub x: usize,
}

pub struct AddressStreet {
    pub x: usize,
}

#[derive(Clone, Copy, Debug)]
pub enum RoadDirection {
    Vertical,
    Horizontal,
}

#[derive(Clone, Copy, Debug)]
pub struct Road {
    pub direction: RoadDirection,
    pub location: usize,
}

pub const WIDTH: usize = 50;
pub const HEIGHT: usize = 50;

pub fn city_drawer(n_s_avenues: &[Road], e_w_streets: &[Road]) {
    let mut grid: Vec<Vec<char>> = vec![vec!['.'; WIDTH]; HEIGHT];

    let mut n_s_iter = n_s_avenues.iter();
    while let Some(avenue) = n_s_iter.next() {
        dbg!(avenue);
        for y in 0..HEIGHT {
            grid[avenue.location][y] = '#';
        }
    }

    let mut e_w_iter = e_w_streets.iter();
    while let Some(street) = e_w_iter.next() {
        for x in 0..WIDTH {
            grid[x][street.location] = '#';
        }
    }

    for row in 0..HEIGHT {
        for col in 0..WIDTH {
            match grid[col][row] {
                '.' => {
                    print!(".")
                }
                '#' => {
                    print!("#")
                }
                _ => {}
            }
        }
        println!();
    }
}

fn draw_roads(roads: &[Road], grid: &mut Vec<Vec<char>>) {
    let direction = roads[0].direction;
    for road in roads {
        for i in 0..grid.len() {
            match direction {
                RoadDirection::Vertical => {
                    grid[road.location][i] = '#';
                }
                RoadDirection::Horizontal => {
                    grid[i][road.location] = '#';
                }
            }
        }
    }
}

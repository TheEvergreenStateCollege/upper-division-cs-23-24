pub struct AddressAvenue {
    pub x: usize,
}

pub struct AddressStreet {
    pub x: usize,
}

#[derive(Clone, Copy)]
pub enum RoadDirection {
    Vertical,
    Horizontal,
}

pub struct Road {
    pub direction: RoadDirection,
    pub location: usize,
}

pub const WIDTH: usize = 50;
pub const HEIGHT: usize = 50;

pub fn city_drawer(n_s_avenues: &mut [Road], e_w_streets: &mut [Road]) {
    n_s_avenues.sort_by(|a, b| a.location.cmp(&b.location));
    e_w_streets.sort_by(|a, b| a.location.cmp(&b.location));

    let mut grid: [[char; WIDTH]; HEIGHT] = [['.'; WIDTH]; HEIGHT];

    let mut n_s_iter = n_s_avenues.iter();
    loop {
        match n_s_iter.next() {
            Some(avenue) => {
                for y in 0..HEIGHT {
                    grid[avenue.location][y] = '#';
                }
            }
            None => {
                // no more
                break;
            }
        }
    }

    let mut e_w_iter = e_w_streets.iter();
    loop {
        match e_w_iter.next() {
            Some(street) => {
                for x in 0..WIDTH {
                    grid[x][street.location] = '#';
                }
            }
            None => {
                // no more
                break;
            }
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

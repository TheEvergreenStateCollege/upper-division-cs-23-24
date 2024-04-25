
pub struct Street {
    pub y: usize
}

pub struct Avenue {
    pub x: usize
}

pub struct AddressAvenue {
    pub x: usize,
}

pub struct AddressStreet {
    pub y: usize,
}

pub const WIDTH: usize = 50;
pub const HEIGHT: usize = 50;

pub fn city_drawer(n_s_avenues: &[Avenue], e_w_streets: &[Street]) {
    // Create a city layout
    let mut grid: [[char; WIDTH]; HEIGHT] = [['.'; WIDTH]; HEIGHT];

    let mut n_s_iter = n_s_avenues.iter();
    let mut e_w_iter = e_w_streets.iter();
    
    while let (Some(avenue), Some(street)) = (n_s_iter.next(), e_w_iter.next()) {
        for y in 0..HEIGHT {
            grid[avenue.x][y] = '#';
        }
        for x in 0..WIDTH {
            grid[x][street.y] = '#';
        }
    }

    // Print city
    for row in grid.iter() {
        for &cell in row.iter() {
            print!("{}", cell);
        }
        println!();
    }
}

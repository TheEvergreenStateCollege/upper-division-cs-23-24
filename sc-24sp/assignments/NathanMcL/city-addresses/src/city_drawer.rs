#[derive(Debug)]
pub struct Street {
    pub y: usize,
}

#[derive(Debug)]
pub struct Avenue {
    pub x: usize,
}

pub struct AddressAvenue {
    pub x: usize,
}

pub struct AddressStreet {
    pub x: usize,
}

// Define an enum to choose the type of structure to create
pub enum StructureType {
    Avenue,
    Street,
}

pub const WIDTH: usize = 50;
pub const HEIGHT: usize = 50;

pub fn city_drawer(n_s_avenues: &mut Vec<Avenue>, e_w_streets: &mut Vec<Street>) {
    n_s_avenues.sort_by(|a,b| a.x.cmp(&b.x));
    e_w_streets.sort_by(|a,b| a.y.cmp(&b.y));

    let mut grid: [[char; WIDTH]; HEIGHT] = [['.' as char; WIDTH]; HEIGHT];

    let mut n_s_iter = n_s_avenues.iter();
    loop {
        match n_s_iter.next() {
            Some(avenue) => {
                for y in 0..HEIGHT {
                    grid[avenue.x as usize][y] = '#';
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
                    grid[x][street.y as usize] = '#';
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
                '.' => { print!(".") }
                '#' => { print!("#") }
                _ => { }
            }
        }
        println!("");

    }
}


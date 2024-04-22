
pub struct Road {
    pub x: usize
}

pub struct Address {
    pub x: usize
}

// pub struct AddressAvenue {
//     pub x: usize,
// }

// pub struct AddressStreet {
//     pub x: usize,
// }

pub const BOUND: usize = 50;
//pub const BOUND: usize = 50;

pub fn city_drawer(n_s_avenues: &mut Vec<Road>, e_w_streets: &mut Vec<Road>) {
    n_s_avenues.sort_by(|a,b| a.x.cmp(&b.x));
    e_w_streets.sort_by(|a,b| a.x.cmp(&b.x));

    let mut grid: [[char; BOUND]; BOUND] = [['.' as char; BOUND]; BOUND];

    let mut n_s_iter = n_s_avenues.iter();
    loop {
        match n_s_iter.next() {
            Some(road) => {
                for x in 0..BOUND {
                    grid[road.x as usize][x] = '#';
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
            Some(road) => {
                for x in 0..BOUND {
                    grid[x][road.x as usize] = '#';
                }
            }
            None => {
                // no more 
                break;
            }
        }
    }

    for row in 0..BOUND {
        for col in 0..BOUND {
            match grid[col][row] {
                '.' => { print!(".") }
                '#' => { print!("#") }
                _ => { }
            }
        }
        println!("");

    }
}

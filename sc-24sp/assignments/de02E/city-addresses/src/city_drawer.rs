
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


pub fn city_drawer(n_s_roads: &mut Vec<Road>, e_w_roads: &mut Vec<Road>) {
    n_s_roads.sort_by(|a,b| a.x.cmp(&b.x));
    e_w_roads.sort_by(|a,b| a.x.cmp(&b.x));
    let array = [n_s_roads,e_w_roads];

    let mut grid: [[char; BOUND]; BOUND] = [['.' as char; BOUND]; BOUND];
    for index in 0..array.len() {
        let mut roads_iter = array[index].iter();
        loop {
            match roads_iter.next() {
                Some(road) => {
                    for x in 0..BOUND {
                        if index == 0 {
                            grid[road.x as usize][x] = '#';
                            if road.x != 0 && grid[road.x as usize-1][x] == '.' {grid[road.x as usize-1][x] = 'o';}
                            if road.x != 49 && grid[road.x as usize+1][x] == '.' {grid[road.x as usize+1][x] = 'o';}
                        } else {
                            grid[x][road.x as usize] = '#';
                            if road.x != 0 && grid[x][road.x as usize-1] == '.' {grid[x][road.x as usize-1] = 'o';}
                            if road.x != 49 && grid[x][road.x as usize+1] == '.' {grid[x][road.x as usize+1] = 'o';}
                        }
                    }
                }
                None => {
                    // no more
                    break;
                }
            }
        }
    }

    for row in 0..BOUND {
        for col in 0..BOUND {
            match grid[col][row] {
                '.' => { print!(".") }
                '#' => { print!("#") }
                'o' => { print!("o") }
                _ => { }
            }
        }
        println!("");
    }
}
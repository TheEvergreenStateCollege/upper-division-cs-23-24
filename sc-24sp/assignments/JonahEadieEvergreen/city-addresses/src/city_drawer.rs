use rand::Rng;

/*pub struct Street {
    pub y: usize
}

pub struct Avenue {
    pub x: usize
}*/

#[derive(PartialEq, Clone)]
pub enum Direction {
    Horizontal,
    Vertical,
}

pub struct Road {
    pub dir: Direction,
    pub pos: usize, 
}

pub struct AddressAvenue {
    pub x: usize,
}

pub struct AddressStreet {
    pub x: usize,
}

pub const WIDTH: usize = 50;
pub const HEIGHT: usize = 50;

pub fn city_drawer(roads: &mut Vec<Road>) {
    //n_s_avenues.sort_by(|a,b| a.x.cmp(&b.x));
    //e_w_streets.sort_by(|a,b| a.y.cmp(&b.y));

    let mut grid: [[char; WIDTH]; HEIGHT] = [['.' as char; WIDTH]; HEIGHT];

    let mut roads_iter = roads.iter();
    loop {
        match roads_iter.next() {
            Some(road) => {
                if road.dir == Direction::Vertical {
                    for y in 0..HEIGHT {
                        grid[road.pos as usize][y] = '#';
                    }
                } else {
                    for x in 0..WIDTH {
                        grid[x][road.pos as usize] = '#';
                    }
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

pub fn gen_random_roads(direction: Direction) -> Vec::<Road> {
    // Avenues/vertical
    let max_dim
     = if direction == Direction::Vertical {WIDTH} else {HEIGHT};
    let mut roads = Vec::<Road>::new();
    let mut i = 0;
    loop {
        let next_pos = rand::thread_rng().gen_range(0..10);
        i += next_pos;

        if i >= max_dim
         {
            break;
        }

        roads.push(Road{ dir: direction.clone(), pos: i });
        println!("Added road {:?}", i);
    }

    return roads
}

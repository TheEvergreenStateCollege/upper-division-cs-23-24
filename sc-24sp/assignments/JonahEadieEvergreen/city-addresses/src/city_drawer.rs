use rand::Rng;

#[derive(PartialEq, Clone)]
pub enum Direction {
    Horizontal,
    Vertical,
}

pub struct Road {
    pub dir: Direction,
    pub pos: usize, 
}

pub struct Address {
    pub x: usize,
    pub y: usize,
}

pub const WIDTH: usize = 50;
pub const HEIGHT: usize = 50;

pub fn city_drawer(roads: &mut Vec<Road>, addresses: &mut Vec<Address>) {
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

    for address in addresses {
        grid[address.x][address.y] = 'O' as char;
    }

    for row in 0..HEIGHT {
        for col in 0..WIDTH {
            match grid[col][row] {
                '.' => { print!(".") }
                '#' => { print!("#") }
                'O' => { print!("O") }
                _ => { }
            }
        }
        println!("");
    }
}

pub fn gen_random_roads(direction: Direction) -> Vec::<Road> {
    let max_dim = if direction == Direction::Vertical {WIDTH} else {HEIGHT};
    let mut roads = Vec::<Road>::new();
    let mut i = 0;

    loop {
        let next_pos = rand::thread_rng().gen_range(0..10);
        i += next_pos;

        if i >= max_dim {
            break;
        }

        roads.push(Road{ dir: direction.clone(), pos: i });
        println!("Added road {:?}", i);
    }

    return roads
}

pub fn gen_random_addresses(roads: &Vec::<Road>, number: usize) 
-> Vec::<Address> {
    let mut addresses = Vec::<Address>::new();
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

    let mut i: usize = 0;

    while i < number {
        let x_pos = rand::thread_rng().gen_range(0..WIDTH);
        let y_pos = rand::thread_rng().gen_range(0..HEIGHT);

        if grid[x_pos][y_pos] == '.' as char {
            addresses.push(Address {x: x_pos, y: y_pos});
            i += 1;
        } else {
            continue;
        }
    }

    return addresses;
}

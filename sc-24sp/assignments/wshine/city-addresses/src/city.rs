use core::fmt;
use rand::Rng;
use std::{
    collections::{HashMap, HashSet},
    usize,
};

pub const WIDTH: usize = 50;
pub const HEIGHT: usize = 50;
#[derive(Debug)]
pub struct City {
    aves: Vec<Avenue>,
    streets: Vec<Street>,
    pub addresses: HashMap<(usize, usize), String>,
}

impl fmt::Display for City {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        //TODO call city drawer to get an array of &str and print each line
        let grid = draw(
            self.aves.as_slice(),
            self.streets.as_slice(),
            &self.addresses,
        );

        for line in grid {
            write!(f, "{}\n", String::from_iter(line.iter()))?;
        }
        let addr_count = grid
            .iter()
            .map(|line| line.iter().filter(|x| **x == 'o').count())
            .sum::<usize>();
        writeln!(f, "{}", addr_count)
    }
}

impl City {
    pub fn new() -> City {
        let mut aves: Vec<Avenue> = gen_random_roads(HEIGHT, Avenue::new);
        let mut streets: Vec<Street> = gen_random_roads(WIDTH, Street::new);
        let addresses: HashMap<(usize, usize), String> =
            initialize_addresses(aves.as_slice(), streets.as_slice());
        aves.sort_by(|a, b| a.x.cmp(&b.x));
        streets.sort_by(|a, b| a.y.cmp(&b.y));

        // TODO generate address map
        City {
            aves,
            streets,
            addresses,
        }
    }
}

#[derive(Debug)]
pub struct Street {
    pub y: usize,
}

#[derive(Debug)]
pub struct Avenue {
    pub x: usize,
}

pub trait Road {
    fn new(coord: usize) -> Self;
}

impl Road for Street {
    fn new(coord: usize) -> Street {
        Street { y: coord }
    }
}

impl Road for Avenue {
    fn new(coord: usize) -> Avenue {
        Avenue { x: coord }
    }
}

fn gen_random_roads<T: Road + std::fmt::Debug>(bounds: usize, ctor: fn(usize) -> T) -> Vec<T> {
    let mut rng = rand::thread_rng();
    let mut random_roads = vec![];
    Street::new(3);
    for _ in 0..=10 {
        random_roads.push(ctor(rng.gen_range(0..bounds)));
    }
    println!("{:#?}", random_roads);
    random_roads
}

fn draw(
    aves: &[Avenue],
    streets: &[Street],
    addresses: &HashMap<(usize, usize), String>,
) -> [[char; WIDTH]; HEIGHT] {
    let mut grid: [[char; WIDTH]; HEIGHT] = [['.' as char; WIDTH]; HEIGHT];

    for ave in aves {
        for y in 0..HEIGHT {
            grid[ave.x as usize][y] = '#';
        }
    }
    for street in streets {
        for x in 0..WIDTH {
            grid[x][street.y as usize] = '#';
        }
    }
    for addr in addresses.keys() {
        grid[addr.0][addr.1] = 'o';
    }
    grid
}

fn initialize_addresses(aves: &[Avenue], streets: &[Street]) -> HashMap<(usize, usize), String> {
    let x_coords: HashSet<usize> = HashSet::from_iter(aves.iter().map(|v| v.x));
    let y_coords: HashSet<usize> = HashSet::from_iter(streets.iter().map(|v| v.y));
    let mut addreses: HashMap<(usize, usize), String> = HashMap::new();
    println!("{:?}\n{:?}", x_coords, y_coords);
    for x in &x_coords {
        let mut address_counter = 0;
        if *x as i32 - 1 >= 0 {
            for y in 0..HEIGHT {
                if !y_coords.contains(&y) && !x_coords.contains(&(*x - 1)) {
                    addreses.insert((*x - 1, y), format!("{} Avenue {}", address_counter, *x));
                    address_counter += 1;
                }
            }
        }
        if *x + 1 < HEIGHT {
            for y in 0..HEIGHT {
                if !y_coords.contains(&y) && !x_coords.contains(&(*x + 1)) {
                    addreses.insert((*x + 1, y), format!("{} Avenue {}", address_counter, *x));
                    address_counter += 1;
                }
            }
        }
    }
    for y in &y_coords {
        let mut address_counter = 0;
        if *y as i32 - 1 >= 0 {
            for x in 0..WIDTH {
                if !x_coords.contains(&x) && !y_coords.contains(&(*y - 1)) {
                    addreses.insert((x, *y - 1), format!("{} Street {}", address_counter, *y));
                    address_counter += 1;
                }
            }
        }
        if *y + 1 < WIDTH {
            for x in 0..WIDTH {
                if !x_coords.contains(&x) && !y_coords.contains(&(*y + 1)) {
                    addreses.insert((x, *y + 1), format!("{} Street {}", address_counter, *y));
                    address_counter += 1;
                }
            }
        }
    }
    addreses
}

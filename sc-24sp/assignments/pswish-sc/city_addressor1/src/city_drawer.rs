// I don't know how to have a trait return itself as a return type

pub enum RoadDirection {
    NorthSouth,
    EastWest,
}

pub struct Road {
    pub road_type: RoadDirection,
    pub coord: usize,
}

pub struct Avenue {
    pub x: usize,
}

pub struct Street {
    pub y: usize,
}

pub struct Address {
    pub x: usize,
}

pub const BOUND: usize = 50;

pub type Grid = [[char; BOUND]; BOUND];

pub fn city_drawer<'a>(in_grid: &'a mut Grid, roads: &'a Vec<Road>) -> &'a mut Grid {
    let mut roads_iter = roads.iter();
    loop {
        match roads_iter.next() {
            Some(road) => {
                for w in 0..BOUND {
                    match road.road_type {
                        RoadDirection::NorthSouth => {
                            in_grid[road.coord as usize][w] = '#';
                            // Implemented from in class week 5, lines 64 - 88
                            if road.coord > 0 && in_grid[road.coord - 1 as usize][w] == '.' {
                                in_grid[road.coord - 1 as usize][w] = 'o';
                            }

                            if road.coord < BOUND - 1 && in_grid[road.coord as usize + 1][w] == '.'
                            {
                                in_grid[road.coord + 1 as usize][w] = 'o';
                            }
                        }

                        RoadDirection::EastWest => {
                            in_grid[w][road.coord] = '#';
                            if road.coord > 0 && in_grid[w][road.coord - 1 as usize] == '.' {
                                in_grid[w][road.coord - 1 as usize] = 'o';
                            }

                            if road.coord < BOUND - 1 && in_grid[w][road.coord + 1 as usize] == '.'
                            {
                                in_grid[w][road.coord + 1 as usize] = 'o';
                            }
                        }
                    }
                }
            }
            None => {
                // no more
                break;
            }
        }
    }
    in_grid
}

pub fn draw_grid(grid: Grid) {
    for row in 0..BOUND {
        for col in 0..BOUND {
            match grid[col][row] {
                '.' => {
                    print!(".")
                }
                '#' => {
                    print!("#")
                }
                'o' => {
                    print!("o")
                }
                _ => {}
            }
        }
        println!("");
    }
}


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

pub fn city_drawer<'a>(in_grid: &'a mut Grid, roads: &'a Vec<Road>) -> &'a mut Grid {
    //roads.sort_by(|a,b| a.coord.cmp(&b.coord));

    let mut roads_iter = roads.iter();
    loop {
        match roads_iter.next() {
            Some(road) => {
                for w in 0..BOUND {
                    match road.road_type {
                        RoadDirection::NorthSouth => {
                            in_grid[road.coord as usize][w] = '#';
                            in_grid[w][road.coord] = '#';
                            if road.coord > 0 && in_grid[road.coord - 1 as usize][w] == '.' {
                                in_grid[road.coord - 1 as usize][w] = 'o';
                            }
                            if road.coord < BOUND - 1  && in_grid[road.coord as usize + 1][w] == '.' {
                                in_grid[road.coord as usize + 1][w] = 'o';
                            }
                        }
                        RoadDirection::EastWest => {
                            in_grid[w][road.coord] = '#';
                            if road.coord > 0 && in_grid[w][road.coord - 1 as usize] == '.' {
                                in_grid[w][road.coord as usize - 1 as usize] = 'o';
                            }
                            if road.coord < BOUND && in_grid[w][road.coord + 1 as usize] == '.' {
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

// pub fn city_drawer(n_s_avenues: &mut Vec<Road>, e_w_streets: &mut Vec<Road>) {
//     let mut road_iter.next() = roads.iter();
//         loop {
//             match road_iter.next() {
//                 some(road) => {
//                     for x in 0..BOUNDS {
//                         match road.road_type {
//                             RoadDirection:NorthSouth => {
//                                 in_grid[road.coord as usize]
//                             }
//                             RoadDirection:EastWest => {
//                                 in_grid[road.coord as usize]
//                             }
//                         }
//                     //grid[road.x ]
//                     }
//                     for x - 1 in 0..BOUNDS {
//                         match road.road_type {
//                             RoadDirection:NorthSouth => {
//                                 in_grid[road.coord as usize]
//                             }
//                             RoadDirection:EastWest => {
//                                 in_grid[road.coord as usize]
//                             }
//                         }
//                     //grid[road.x ]
//                     }
//                 }
//             }
//         }



//     n_s_avenues.sort_by(|a,b| a.x.cmp(&b.x));
//     e_w_streets.sort_by(|a,b| a.x.cmp(&b.x));

//     let mut grid: [[char; BOUND]; BOUND] = [['.' as char; BOUND]; BOUND];

//     let mut n_s_iter = n_s_avenues.iter();
//     loop {
//         match n_s_iter.next() {
//             Some(road) => {
//                 for x in 0..BOUND {
//                     grid[road.x as usize][x] = '#';
//                 }
//             }
//             None => {
//                 // no more 
//                 break;
//             }
//         }
//     }

//     let mut e_w_iter = e_w_streets.iter();
//     loop {
//         match e_w_iter.next() {
//             Some(road) => {
//                 for x in 0..BOUND {
//                     grid[x][road.x as usize] = '#';
//                 }
//             }
//             None => {
//                 // no more 
//                 break;
//             }
//         }
//     }

//     for row in 0..BOUND {
//         for col in 0..BOUND {
//             match grid[col][row] {
//                 '.' => { print!(".") }
//                 '#' => { print!("#") }
//                 _ => { }
//             }
//         }
//         println!("");

//     }
//}

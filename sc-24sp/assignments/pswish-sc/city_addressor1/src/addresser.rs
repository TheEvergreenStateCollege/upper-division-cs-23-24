pub trait Addresser {
    fn build_addresser(roads: Vec<Vec<Road>>) -> Self;
    fn get_address_string(x: usize, y: usize) -> Option<String>;
}

pub struct OlympiaAddresser {
    locations: HashMap<( , String)>
}

impl CityAddresser for OlympiaAddresser {
    fn build_addresser(roads: Vec<Vec<Road>>) -> Self {
        let mut locations: HashMap<(usize, usize), String> = HashMap::new();
        let mut roads_iter = roads.iter();
    }
    
    while let Some(road) = roads_iter.next() {
        let road = road.road_type();
        match road_type {
            Some(RoadType::Avenue) => {
                // draw # for the ave itself
                print!("x");
                // check if not leftmost (o)for right side address
                if road.x > 0 {
                    let address = format!("o");
                    locations.insert((road.x -1, road.y), address);
                }
                // check if not rightmost (o) for left side address
                if road.x < BOUND - 1 {
                    let address = format!("o");
                    locations.insert((road.x + 1, road.y), address);
                }
                // check if not bottommost (o) for top side address
                if road.y > 0 {
                    let address = format!("o");
                    locations.insert((road.x, road.y - 1), address);
                }
                // check if not topmost (o) for bottom side address
                if road.y < BOUND - 1 {
                    let address = format!("o");
                    locations.insert((road.x, road.y + 1), address);
                }

                let address = format!('o');
                locations.insert((road.x, road.y), address);
            }
            Some(RoadType::Street) => {} => {
                // draw # for the street itself
                print!("x");
                // check if not leftmost (o)for right side address
                if road.x > 0 {
                    let address = format!("o");
                    locations.insert((road.x - 1, road.y), address);
                }
                // check if not rightmost (o) for left side address
                if road.y < BOUND - 1 {
                    let address = format!("o");
                    locations.insert((road.x, road.y + 1), address);
                }
                // check if not topmost (o) for bottom side address
                if road.y > 0 {
                    let address = format!("o");
                    locations.insert((road.x, road.y - 1), address);
                }

                let address = format!('o');
                locations.insert((road.x, road.y), address);
            }
            _ => {}
        }
    // returns a hashmap you can query.
    fn get_address_string(x: usize, y: usize) -> Option<String> {
        unimplmented!()
    }
    }
}
//     pub struct TokyoAddresser {
//     current_block_num: usize,
// }

use crate::city_drawer::{
    city_drawer, gen_random_roads, Avenue, Street, Road, HEIGHT, WIDTH,
};

fn main() {
    println!("Hello, city!");
    let mut n_s_avenues = gen_random_roads(HEIGHT, Avenue::new);
    let mut e_w_streets = gen_random_roads(WIDTH, Street::new);
    city_drawer(&mut n_s_avenues, &mut e_w_streets);
}

// Import the `Rng` trait from the `rand` crate to use random number generation functions
use rand::Rng;

// Import the local module `city_drawer`, which presumably contains definitions and functionalities related to drawing a city
pub mod city_drawer;

// Import specific items from the `city_drawer` module, including `Avenue`, `Street`, a function named `city_drawer`, and constants `WIDTH` and `HEIGHT`
use crate::city_drawer::{Avenue, Street, city_drawer, WIDTH, HEIGHT};

fn main() {
    
    println!("Hello, city!");

    // Initialize a vector to hold objects of type `Avenue`
    let mut n_s_avenues = Vec::<Avenue>::new();

    // Initialize a loop counter for avenues
    let mut i = 0;
    loop {
        // Generate a random number between 1 and 9
        let next_x = rand::thread_rng().gen_range(1..10);
        i += next_x;  // Increment the loop counter by the generated random number

        // Check if the loop counter exceeds the predefined WIDTH constant
        if i >= WIDTH {
            break;  // Exit the loop if the condition is met
        }
        // Add a new `Avenue` to the vector with the current value of `i` as the x-coordinate
        n_s_avenues.push(Avenue{ x: i });
        // Print the added avenue's x-coordinate
        println!("Added avenue {:?}", i);
    }

    // Initialize a vector to hold objects of type `Street`
    let mut e_w_streets = Vec::<Street>::new();

    // Initialize a loop counter for streets
    let mut j = 0;
    loop {
        // Generate a random number between 0 and 9
        let next_y = rand::thread_rng().gen_range(0..10);
        j += next_y;  // Increment the loop counter by the generated random number

        // Check if the loop counter exceeds the predefined HEIGHT constant
        if j >= HEIGHT {
            break;  // Exit the loop if the condition is met
        }
        // Add a new `Street` to the vector with the current value of `j` as the y-coordinate
        e_w_streets.push(Street { y: j });
        // Print the added street's y-coordinate
        println!("Added street {:?}", j);
    }

    // Call the `city_drawer` function, passing the vectors of avenues and streets to draw the city
    city_drawer(&mut n_s_avenues, &mut e_w_streets);
}

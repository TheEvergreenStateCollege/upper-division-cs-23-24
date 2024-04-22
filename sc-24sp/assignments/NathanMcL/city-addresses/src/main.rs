// Import the `Rng` trait from the `rand` crate to use random number generation functions
use rand::Rng;

// Import the local module `city_drawer`
pub mod city_drawer;

// Import specific items from the `city_drawer` module
use crate::city_drawer::{Avenue, Street, city_drawer, WIDTH, HEIGHT};

pub fn random_avenues(count: usize) -> Vec<Avenue> {
    let mut rng = rand::thread_rng(); // Create a random number generator instance.
    let mut avenues = Vec::new(); // Create an empty vector to store Avenue instances
    for _ in 0..count {  // Loop `count` times to generate the specified number of avenues.
        let pos = rng.gen_range(0..WIDTH); // Generate a random position within the width limits of the city map.
        avenues.push(Avenue { x: pos }); // Create a new Avenue struct with the generated position and add it to the vector.
    }
    avenues
}

pub fn random_streets(count: usize) -> Vec<Street> {
    let mut rng = rand::thread_rng();
    let mut streets = Vec::new();
    for _ in 0..count {
        let pos = rng.gen_range(0..HEIGHT);
        streets.push(Street { y: pos });
    }
    streets
}

fn main() {
    let random_avenues = random_avenues(10);
    let random_streets = random_streets(10);
    
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

    // Debug prints to see the outputs
    println!("Random Avenues: {:?}", random_avenues);
    println!("Random Streets: {:?}", random_streets);

    // Call the `city_drawer` function, passing the vectors of avenues and streets to draw the city
    city_drawer(&mut n_s_avenues, &mut e_w_streets);
}

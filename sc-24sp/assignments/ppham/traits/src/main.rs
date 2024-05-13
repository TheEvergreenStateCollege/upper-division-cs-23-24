struct Sheep {}
struct Cow {}

struct Pig {}

trait Animal {
    // Instance method signature
    fn noise(&self) -> &'static str;
}

impl Animal for Sheep {
    fn noise(&self) -> &'static str {
        "baaaah!"
    }
}

impl Animal for Cow {
    fn noise(&self) -> &'static str {
        "moooo!"
    }
}

impl Animal for Pig {
    fn noise(&self) -> &'static str {
        "oink!"
    }
}

fn random_animal(random_number: f32) -> Box<dyn Animal> {
    // pattern matching
    match random_number {
        0.0..0.3 => {
            Box::new(Pig {})
        }
        0.3..0.5 => {        
            Box::new(Sheep {})
        }
        0.5.. => {
            Box::new(Cow {})
        }
    }
}

fn main() {
    let random_number: f32 = 0.234;

    // Function which takes a random number and returns a struct of Animal type
    let animal: Box<dyn Animal> = random_animal(random_number);
    println!("You've randomly chosen an animal, and it says {}", animal.noise());
}

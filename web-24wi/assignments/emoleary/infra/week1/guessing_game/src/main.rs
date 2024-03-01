use std::io;
fn main() {
    println!("Guess the NUMBER!!!!!!");
    
    println!("PLEASE input your guess.");

    let mut guess = String::new();

    io::stdin()
        .read_line(&mut guess)
        .expect("Failed to read line");

    println!("You guessed: {guess}");
    
}

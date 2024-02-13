fn main() {
    let cond = true;
    let x;

    if cond {
        x = 1;
    } else {
        x = 2;
    }

    println!("x is {x}");
    let number = 3;

    if number < 5 {
        println!("condition was true");
    } else {
        println!("condition was false");
    }
}

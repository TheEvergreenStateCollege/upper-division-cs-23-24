fn main() {

    let my_fav_fruits = vec!["banana", "custard apple", "avocado", "peach", "raspberry"];
    
    for i in 1..my_fav_fruits.len() {
        println!("{}", my_fav_fruits[i]);
    }

    println!("");

    for fruit in my_fav_fruits.iter() {
        println!("{}", fruit);
    }

    println!("");

    let mut fruit_iter = my_fav_fruits.iter();

    while true {
        let fruit = fruit_iter.next();
        match fruit {
            Some(s) => { println!("{}", s); },
            None => { break; }
        }
    }

}

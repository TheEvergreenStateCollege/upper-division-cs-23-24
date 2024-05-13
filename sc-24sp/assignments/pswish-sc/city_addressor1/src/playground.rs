use std::collections::HashMap;

type AddressMap = HashMap<(usize, usize), String>;

// How should we fix up this paramter
// DONE We want to create it externally and keep ownership, let this addresser borrow ownership
// DONE We want to mutate an have the original value keep thos changes after we return
fn populate_address<'a>(addresses: &'a mut AddressMap) {
    addresses.insert((1, 2), String::from("121 Aveneue"))
}
fn main() {
    println!("");
    // create & allocate the hashmap
    // populate by calling the external addresser
    // iterate and print out the values
    let mut addresses: AddressMap = HashMap::new();

    populate_address(&mut addresses);

    for (key, value) in addresses {
        println!("{:?}", key);
        println!("{:?}", value);
    }
}

Jonah Eadie (JonahEadieEvergreen)

Program iterates through each address and prints. It also keeps tally of their number, along with the number of streets and avenues, printing these as well. In printing, addresses are displayed by grid position (i.e.,
the address at (0,0), then (0,1) ... if they exist).

## Step 2

A sample of the addresses generated:

```
943. (40,41): 71 Avenue 39
944. (40,42): 73 Avenue 39
945. (40,43): 75 Avenue 39
946. (40,44): 77 Avenue 39
947. (40,45): 79 Avenue 39
948. (40,46): 81 Avenue 39
949. (40,47): 83 Avenue 39
950. (40,48): 85 Avenue 39
951. (40,49): 87 Avenue 39
```

and the code used to display these:

```
// This function creates two-dimensional vector, each index of which
// contains a String containing the address name. This facilitates
// somewhat sorted printing of addresses
fn addresses_matrix (addresses: &AddressMap) -> Vec<Vec<String>> {
    let mut matrix = vec![vec!["".into(); BOUND]; BOUND];

    for a in addresses {
        matrix[a.0.0][a.0.1] = a.1.clone();
    }

    return matrix;
}

    for i in 0..BOUND {
        for j in 0..BOUND {
            if matrix[i][j] == "" {
                continue;
            }

            num_addresses += 1;
            match matrix[i][j].split(' ').nth(1).unwrap() {
                "Avenue" => { num_avenues += 1; },
                "Street" => { num_streets += 1; },
                _ => { panic!(); /* TODO: replace this with something sensible */ }
            };

            println!("{}. ({},{}): {}", num_addresses, i, j, matrix[i][j]);
        }
    }

    print!("Number of addresses: {}\n\
        Number of avenues:   {}\n\
        Number of streets:   {}\n",
        num_addresses, num_avenues, num_streets);
```
## Step 3

The address counter is reset between roads, as demonstrated
by some of these addresses sharing the same street number (the first
number in address name).

```
1.   (35,28): 36 Street 27
2.   (35,32): 18 Street 31 // <--
3.   (35,37): 18 Street 38 // <--
4.   (35,42): 35 Street 43 
5.   (35,44): 36 Street 43
6.   (35,47): 18 Street 46 // <--
```

This was accomplished by inserting indicated line into the given build_city function code

```
pub fn city_builder<'a>(in_grid: &'a mut Grid, roads: &'a Vec<Road>, addresses: &'a mut AddressMap) -> &'a mut Grid {
    let mut roads_iter = roads.iter();
    loop {
        match roads_iter.next() {
            Some(road) => {
                let mut address_counter = 1; // <--
```


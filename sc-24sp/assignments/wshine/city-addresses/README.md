# City addresses

Olympia Street Names Taken From
https://geographic.org/streetview/usa/wa/thurston/olympia.html#google_vignette


## City

```rust
struct City {
    width: i32,
    height: i32,
    aves: Vec<Avenues>,
    streets: Vec<Streets>,
    addresses: HashMap<(i32,i32), String>,
}
```


### addresses

700-1050 addresses average

10 streets 10 avenues

50x50 city

(50 x 2) - (10 x 2) 

80 addresses per road given there are no adjacent roads.
for any adjacent road its -160 less, if roads have one space between them its -80 less.

### thoughts

I think addresses are possibly overwritten when two roads have one space between them, or in the case
that an address sits at an intersection.



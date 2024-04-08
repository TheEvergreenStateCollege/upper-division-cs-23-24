Prototype 3
Ryan and Jeff Kickin' it Rust Style

We were able to finish typing and (mostly) error checking the code, however, we were not able to fix the issue in types/board on line 28:

error: `self` parameter is only allowed in associated functions
  --> src/types/board.rs:28:22
   |
28 | pub fn make_move<'a>(&'a mut self, new_move: &'a Move, player: &Player) -> (Option<MoveError>){
   |                      ^^^^^^^^^^^^ not semantically valid as function parameter
   |
   = note: associated functions are those in `impl` or `trait` definitions

This code is identical to the code we were copying, as is the rest of the file, however, we could not get it to build.


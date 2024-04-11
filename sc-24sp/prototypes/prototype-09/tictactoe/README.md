# Tic Tac Toe Notes
By Gavin Bowers,
working with Paul Swischer(?)

### In Class Progress

I transcribed the code, but introduced some errors in the process. I fixed those errors, as well as fixing errors caused by incorrectly initializing the Rust project. After it was working, I added a pretty print function to make it much nicer to use. However, I haven't gotten around to fixing the bug or improving the algorithm.

I decided to improve the print function. It now implements display, and uses write! instead of print!. I also made it use loops instead of repeating code, and made the helper function for printing cells into a closure for brevity. Here it is:

```Rust
impl fmt::Display for Board<'_> {
    fn fmt(&self, f: &mut fmt::Formatter<'_>) -> fmt::Result {
        for i in 0..3 {
            write!(f, "-------------\n")?;
            for j in 0..3 {
                write!(f, "| {} ", self.cells[i][j].map_or(" ", |p| match p {Player::O => "O", Player::X => "X"}))?
            }
            write!(f, "|\n")?
        }
        write!(f, "-------------\n\n")
    }
}
```

### Later Progress

I re-wrote most of the code to bring it in-line with my preferences. I fixed the turn order bug by scrapping `main()` entirely and re-writing the `make_move()` function. Now I track turns with the control flow in main instead of the next_to_move variable, and use a helper function to take input as written description of the position. I also got rid of the unnessary lifetimes and stopped using references in `Cell`. I wrote a simple AI based on a hierarchy of moves, ranging from higher to lower priority. It uses a 2 deep brute force search to seek winning moves and block the opponent's moves. I generalized `make_move()` and `validate_win()` to help with this. However, my AI was failing to see some winning paths (for itself and the player), and I couldn't figure out why. I added a debug print for the AI's reasoning, which showed that `validate_win()` was wrong in some board states even though the code is almost certainly correct. I used the debugger to confirm this, but I don't understand how it's happening. I made the AI print all of the board states it goes through when considering moves, and many of the states don't make any sense. I've double checked all of my code and re-written most of Paul's and I can't tell what's causing this.

...

After hours of troubleshooting, I finally found the bug. Paul's clone implementation for Board uses 2 instead of 3 as the exclusive range bound. Meaning that it erased the bottom and rightmost moves every time I copied the board. The only place I used copy was in the AI, where it's used to test permutations of the existing board. So it just looked like the AI was incorrectly reading certain board states.

God dammit Paul

The culprit:
```rust
impl Clone for Board {
    fn clone(&self) -> Self {
        let mut cells: [[Cell; 3]; 3] = EMPTY_BOARD.clone();
        for i in 0..2 { //Evil sneaky bug
            for j in 0..2 {
                cells[i][j] = self.cells[i][j].clone();
            }
        }
        Board { cells }
    }
}
```
Worst of all, it's totally pointless to define Clone anyway because you can derive it! (The derivation works perfectly). I overlooked clone when troubleshooting because I assumed that Paul's code was correct, aside from the bug he specified. I mostly re-wrote Paul's code because I found it offensive to my sensibilities, or to make it more useful for my AI.

Lesson learned: never trust Paul's code to be correct
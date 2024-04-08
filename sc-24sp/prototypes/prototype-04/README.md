We moved the struct definition of Board into `types/board.rs`, because it doesn't make sense to have the `struct` and `impl` in different files.

We don't have to return the board from `list_moves`, because borrowing the `board` doesn't take ownership of it, and `list_moves` won't drop `board`.

Uhhh, the project compiles, but there seem to be some logical errors. Sometimes the program "steals" your move, and I'm not sure why. I'm still trying to puzzle out how this program actually works.

Oh, the problem is actually that the computer is trying to make the same move as the player >:|

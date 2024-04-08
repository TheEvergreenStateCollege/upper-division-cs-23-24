We moved the struct definition of Board into `types/board.rs`, because it doesn't make sense to have the `struct` and `impl` in different files.

We don't have to return the board from `list_moves`, because borrowing the `board` doesn't take ownership of it, and `list_moves` won't drop `board`.

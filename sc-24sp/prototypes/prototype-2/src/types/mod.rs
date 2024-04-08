// Submoduels go here
pub mod board;
#[derive(Debug)]
#[derive(Clone)]
#[derive(partialEq, Eq)]
pub enum  Player {
    x,
    o,
}
impl Player {
    pub fn other(&self) -> &Player{
        if self == &Player::x{
            &Player::o
            
        }
        else{
            &Player::x
        }
    }
}
#[derive(Debug)]
pub struct Move{
    pub coords: (u8,u8),
}
pub type Cell<'a>=Option<&'a Player>;
#[derive(Debug)]
pub struct Board<'a>{
    //Row first coordinate, column secound coordinate
    pub cells: [[Cell<'a>;3]; 3],
    pub next_to_move: &'a Player,
}
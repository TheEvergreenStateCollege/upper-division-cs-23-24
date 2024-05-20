use core::time;
use std::thread::sleep;

use rand::Rng;

#[derive(Clone,Copy,PartialEq)]
enum CellState {
    Alive,
    Dead,
    Warm,
}

pub struct Universe {
    width: u32,
    height: u32,
    cells: Vec<CellState>,
}

impl Universe {
    fn get_index(&self, row: u32, column: u32) -> usize {
        (row * self.width + column) as usize
    }
    fn get_status(&self, index: usize) -> CellState {
        self.cells[index]
    }
    fn live_neighbor_count(&self, row: u32, column: u32, state: CellState) -> u8 {
        let mut neighbor_count: u8 = 0;
        let mut y;
        let mut x;
        for i in 0..9 {
                y = row;
                x = column;
                if i == 4 {
                    continue;
                }
                if i/3 == 0 && y == 0 {
                    y = &self.height-1;
                } else if i/3 == 2 && y == &self.height-1 {
                    y = 0;
                } else {
                    y = (y+(i/3)) - 1;
                }
                if i%3 == 0 && x == 0 {
                    x = &self.width-1;
                } else if i%3 == 2 && x == &self.width-1 {
                    x = 0;
                } else {
                    x = (x+(i%3)) -1;
                }
                let status: &CellState = &self.cells[Self::get_index(&self, y,x)];
                if (*status == state) {
                    neighbor_count += 1;
                }
            }
        neighbor_count
        }
    }

fn main() {
    let uniHeight = 35;
    let uniWidth = 35;
    let mut cell_vec: Vec<CellState> = Vec::new();
    for i in 0..uniHeight*uniWidth {
        let rng = rand::thread_rng().gen_range(0..3);
        match rng {
            0 => {
                cell_vec.push(CellState::Dead);
            }
            1 => {
                cell_vec.push(CellState::Alive);
            }
            2 => {
                cell_vec.push(CellState::Warm);
            }
            _ => {
                print!("Error!");
            }
        }
    }
    let mut universe = Universe{width: uniWidth,height: uniHeight,cells: cell_vec.clone()};
    loop {
        let mut properIndex = (0,0);
        for i in 0..uniHeight {
            for j in 0..uniWidth {
                match universe.get_status(universe.get_index(i, j)) {
                    CellState::Alive => {
                        print!("███");
                    }
                    CellState::Dead => {
                        print!("░░░");
                    }
                    CellState::Warm => {
                        print!("▒▒▒");
                    }
                }
            }
            println!();
        }
        for i in 0..uniHeight {
            for j in 0..uniWidth {
                let live_count = universe.live_neighbor_count(i, j, CellState::Alive);
                let warm_count = universe.live_neighbor_count(i, j, CellState::Warm);
                let cState = &mut cell_vec[universe.get_index(i, j)];
                // Rule 1: Overcrowding
                if (live_count < 2) && *cState == CellState::Alive {*cState = CellState::Warm;}
                // Rule 2: Survival
                else if (live_count < 4 && live_count >= 2) && *cState == CellState::Alive {*cState = CellState::Alive;}
                // Rule 3: Overcrowding
                else if (live_count >= 4) && *cState == CellState::Alive {*cState = CellState::Warm;}
                // Rule 4: Reproduction
                else if (live_count == 3) && *cState == CellState::Dead {*cState = CellState::Alive;}
                // Rule 5: Ressurection
                else if (live_count == 2) && *cState == CellState::Warm {*cState = CellState::Alive;}
                // Rule 6: Decay
                else if warm_count < 6 && *cState == CellState::Warm {*cState = CellState::Dead}
                // Rule 7: Preservation
                else if (warm_count < 8 && live_count >= 6) && *cState == CellState::Warm {*cState = CellState::Dead}
                // Rule 8: Abiogenesis
                else if warm_count == 8 && *cState == CellState::Warm {*cState = CellState::Alive}
            }
        }
        
        universe = Universe{width: uniHeight,height: uniWidth,cells: cell_vec.clone()};
        let waitTime = time::Duration::from_millis(200);
        sleep(waitTime);
        println!();
        println!();
        
    }
}
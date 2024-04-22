pub struct AddressAvenue {
    pub x: usize,
}

pub struct AddressStreet {
    pub x: usize,
}

#[derive(Clone, Copy, Debug)]
pub enum RoadDirection {
    Vertical,
    Horizontal,
}

#[derive(Clone, Copy, Debug)]
pub struct Road {
    pub direction: RoadDirection,
    pub location: usize,
}

pub const WIDTH: usize = 50;
pub const HEIGHT: usize = 50;

pub fn city_drawer(n_s_avenues: &[Road], e_w_streets: &[Road]) {
    let mut grid = vec![vec!['.'; WIDTH]; HEIGHT];

    draw_roads(n_s_avenues, &mut grid);
    draw_roads(e_w_streets, &mut grid);

    for row in 0..HEIGHT {
        for col in 0..WIDTH {
            match grid[col][row] {
                '.' => {
                    print!(".")
                }
                '#' => {
                    print!("#")
                }
                _ => {}
            }
        }
        println!();
    }
}

fn draw_roads(roads: &[Road], grid: &mut Vec<Vec<char>>) {
    let direction = roads[0].direction;
    for road in roads {
        for i in 0..grid.len() {
            match direction {
                RoadDirection::Vertical => {
                    grid[road.location][i] = '#';
                }
                RoadDirection::Horizontal => {
                    grid[i][road.location] = '#';
                }
            }
        }
    }
}

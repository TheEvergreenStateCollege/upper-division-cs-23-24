pub mod addresser;


#[cfg(test)]
mod tests {

    use crate::city_drawer::{city_drawer, city_builder, Address, Road, RoadDirection, BOUND};

    #[test]
    fn test_olympia_addresser1() {
        let mut grid = [['.'; BOUND]; BOUND];



        let addresser = city_builder(&mut grid, &mut roads);
        let address_string = addresser.get_address_string(())
    }
}

pub trait Addresser {
    fn build_addresser(roads: Vec<Vec<Road>>) -> Self;
    fn get_address_string(x: usize, y: usize) -> Option<String>;
}

pub struct OlympiaAddresser {

}

impl Addresser for OlympiaAddresser {

    fn build_addresser(roads: Vec<Vec<Road>>) -> Self {
        
    }
}

pub struct TokyoAddresser {
    current_block_num: usize,
}
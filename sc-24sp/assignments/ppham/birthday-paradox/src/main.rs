use chrono_english::{parse_date_string,Dialect};
use chrono::prelude::*;
use plotters::prelude::*;
use rand::thread_rng;
use std::collections::{HashSet, HashMap};
use std::{fs::File, io::{stdin,BufRead, BufReader}};

use rand::Rng;

fn plot(data: &HashMap<i32, i32>) {
    let root_area = BitMapBackend::new("images/birthdays.png", (600, 400))
        .into_drawing_area();
    root_area.fill(&WHITE).unwrap();

    let mut ctx = ChartBuilder::on(&root_area)
        .set_label_area_size(LabelAreaPosition::Left, 40)
        .set_label_area_size(LabelAreaPosition::Bottom, 40)
        .caption("Bar Demo", ("sans-serif", 40))
        .build_cartesian_2d((0..10).into_segmented(), 0..50)
        .unwrap();

    ctx.configure_mesh().draw().unwrap();

    ctx.draw_series(data.iter().map(|(x, y)| {
        let x0 = SegmentValue::Exact(*x);
        let x1 = SegmentValue::Exact(*x+1);
        let mut bar = Rectangle::new([(x0, 0), (x1, *y)], RED.filled());
        bar.set_margin(0,0,5,5);
        bar
    }))
    .unwrap();

    root_area.present().expect("Unable to write result to file, please make sure 'plotters-doc-data' dir exists under current dir");
}

fn read_birthday_file() -> Vec<DateTime<Local>> {
    let input = File::open("birthdays.txt").unwrap();
    let buffered = BufReader::new(input);
    let lines = buffered.lines();
    let mut all_birthdays = Vec::<DateTime<Local>>::new();

    for line in lines {
        let line_str = line.unwrap().to_string();
        let converted = line_str
            .replace("st", "")
            .replace("rd", "")
            .replace("nd", "")
            .replace("th", "");
        let date = parse_date_string(&converted, Local::now(), Dialect::Us).unwrap();
        all_birthdays.push(date);
    }
    all_birthdays
}

fn select_birthdays(all_birthdays: &Vec<DateTime<Local>>) -> Option<usize> {

    let mut birthdays_copy = all_birthdays.clone();
    let mut selected_birthdays = HashSet::<DateTime<Local>>::new();
    let birthday_count = all_birthdays.len();
    for i in 0..birthday_count {
        let rand_index = thread_rng().gen_range(0..birthday_count-i);
        let rand_bday = birthdays_copy[rand_index];
        println!("Choosing birthday {}", rand_bday);
        birthdays_copy.remove(rand_index);
        if selected_birthdays.contains(&rand_bday) {
            println!("Birthday match on {} after {} people", rand_bday, i+1);
            return Some(i+1);
        }
        selected_birthdays.insert(rand_bday);
    }
    None
}

fn main() {

    let mut all_birthdays = read_birthday_file();

    let mut data = HashMap::<i32,i32>::new();
    for i in 0..100 {
        let samples_until_match = select_birthdays(&all_birthdays).unwrap();
        match data.get(&i) {
            Some(count) => { data.insert(i, count+1); },
            None => { data.insert(i, 1); }
        }
    }

    plot(&data);
}

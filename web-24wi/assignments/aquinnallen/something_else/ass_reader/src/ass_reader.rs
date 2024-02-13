use serde::{Serialize, Deserialize};
use serde_json::Result;
use std::env;
use std::fs;
use text_io::read;
use rand::Rng;
use std::io::*;


fn main() {
  let args: Vec<String> = env::args().collect();
  if args.len() == 2{
    let path = &args[1];
    let user:User = print_info_from_struct(determine_ass(get_info(),load_ass_list(path.to_string())));
    write_info(user);
    println!("Looks like we're all done!\n");
  }
  if args.len()==4 {
    let path = "/home/anon/code/rusting/robobuttlove/ass_reader/asses.csv";
    let mut vec = Vec::new();
    let mut name = String::new();
    let first_name = &args[1];
    let last_name = &args[2];
    let age = &args[3];
    name.push_str(&first_name);  
    name.push_str(" ");  
    name.push_str(&last_name);  
    vec.push(name);
    vec.push(age.clone());
    let user:User = determine_ass(vec,load_ass_list(path.to_string()));
    write_info(user);
  }
}


fn get_info () -> Vec<String> {
  let mut vec = Vec::new();
  println!("Hello User!\n\nThis program will assign descriptors to your booty based on your name and age and let you know what they are! \n");
  println!("What is your first name?");
  let first_name: String = read!();
  println!("What is your last name?");
  let last_name: String = read!();
  println!("What is your age?");
  let age: String = read!();
  let mut name = String::new();
  name.push_str(&first_name);  
  name.push_str(" ");  
  name.push_str(&last_name);  
  vec.push(name);
  vec.push(age);
  return vec;
}


fn load_ass_list(path:String) -> Vec<Vec<String>> {
  let mut vec_ass_vec = Vec::new();
  let full_ass_list_read = fs::read_to_string(path);
  let full_ass_list = full_ass_list_read.unwrap();
  let split_ass_list = full_ass_list.split(',');
  let vec_ass_list: Vec<&str> = split_ass_list.collect();
  let mut outer_vec_index = 0;
  let mut first_run = true;
  for ass in &vec_ass_list {
    let ass_string = ass.to_string();
    if first_run {
      vec_ass_vec.push(Vec::new());
    }
    vec_ass_vec[outer_vec_index].push(ass_string);
    outer_vec_index +=1;
    if outer_vec_index == 26 {
      first_run = false;
      outer_vec_index = 0;
    }
  }
  return vec_ass_vec;
}


fn determine_ass (user_info:Vec<String>, ass_list:Vec<Vec<String>>) -> User {
  let name = user_info[0].clone();
  let age =  user_info[1].parse::<i32>();
  let mut int_age = 0;
  if age.is_ok() {
    int_age = age.unwrap();
  }
  else {
    let mut rng = rand::thread_rng();
    int_age = rng.gen_range(0..100);

  }
  let name_lower = name.to_lowercase();
  let mut name_chars = name_lower.chars();
  let name_num1 = name_chars.next().unwrap() as u8;
  let name_num2 = name_chars.last().unwrap() as u8;
  let name_num2 = name_num2 + 7;
  let name_dex1 = name_num1.rem_euclid(26) as usize;
  let name_dex2 = name_num2.rem_euclid(26) as usize;
  let age_mod = int_age.rem_euclid(ass_list[25].len().try_into().unwrap()) as usize; 
  let descript_1 = ass_list[name_dex1][age_mod].clone();
  let descript_2 = ass_list[name_dex2][age_mod].clone();
  let mut ass:String = String::new();
  ass.push_str(&descript_1);
  ass.push(' ');
  ass.push_str(&descript_2);
  let user:User = build_user(name, int_age, ass);
  return user;
}

#[derive(Serialize, Deserialize)]
struct User {
  name: String,
  age: i32,
  ass: String,
}

fn build_user (name:String, age:i32, ass:String) -> User {
  User {
    name,
    age,
    ass,
  }
}

fn write_info(user:User) -> String {
  let mut filename = String::new();
  filename.push_str("/home/anon/code/rusting/robobuttlove/ass_reader/json/");
  filename.push_str(&user.name.replace(" ","").to_lowercase());
  filename.push_str(&(user.age.to_string()));
  filename.push_str(".json");
  let filename = filename.trim();
  let content = serde_json::to_string(&user).unwrap();
  fs::write(filename,content).expect("ERRRRRR");
  return filename.to_string();
}

fn print_info_from_file(path:String) {
  print!("not here yet");
}

fn print_info_from_struct(user:User) -> User {
  print!("{} is {} and has a {} booty!!",user.name, user.age, user.ass);
  print!("\n");
  return user;
}


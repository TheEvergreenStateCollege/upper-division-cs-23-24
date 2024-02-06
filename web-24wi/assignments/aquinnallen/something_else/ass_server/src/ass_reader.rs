use serde::{Serialize, Deserialize};
use serde_json::Result;
use serde_json::json;
use actix_web::web::Json;
use std::env;
use std::fs;
use text_io::read;
use rand::Rng;
use std::io::*;


pub fn load_ass_list(path:String) -> Vec<Vec<String>> {
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

pub fn determine_ass (name:String, age:String, ass_list:Vec<Vec<String>>) -> User {

  let name = name.clone();
  let age =  age.clone().parse::<i32>();
  let mut int_age = 0;
  if age.is_ok() {
    int_age = age.unwrap();
  }
  else {
    let mut rng = rand::thread_rng();
    int_age = rng.gen_range(18..100);

  }
  if int_age < 18 {
    println!("INAPPROPRIATE USE DECTECTED!!");
    return build_user(name, int_age, "error, cannot describe".to_string());
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
  println!("{} {} {}",user.name, user.age, user.ass);
  return user;
}

#[derive(Serialize, Deserialize)]
pub struct AssReq {
  name: String,
  age: String,
}

#[derive(Serialize, Deserialize)]
pub struct User {
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

pub fn req_to_json(req_in:Json<AssReq>) -> String {
  let req = req_in;
  let ass = determine_ass(req.name.clone(), req.age.clone(), load_ass_list("asses.csv".to_string()));
  return serde_json::to_string(&ass).unwrap();
}

pub fn write_info(user:User,path:String) -> String {
  let mut filename = String::new();
  filename.push_str(&path);
  filename.push_str(&user.name.replace(" ","").to_lowercase());
  filename.push_str(&(user.age.to_string()));
  filename.push_str(".json");
  let filename = filename.trim();
  let content = serde_json::to_string(&user).unwrap();
  fs::write(filename,content).expect("ERRRRRR");
  return filename.to_string();
}


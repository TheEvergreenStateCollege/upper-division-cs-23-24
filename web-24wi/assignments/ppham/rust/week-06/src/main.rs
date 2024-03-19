fn shortest<'a, 'b>(x: &'a str, y: &'b str) -> &'a str {
  if x.len() < y.len() {
    x
  } else {
    y
  }
}


fn main() {
  println!("{}", shortest("hello", "rust"));
}

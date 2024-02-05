const body = document.getElementsByTagName("body")[0];
const WORD_LENGTH = 5;
const GUESS_LENGTH = 6;
const grid = document.getElementsByClassName("grid")[0];
for(let i = 0; i < GUESS_LENGTH; i++){
  const row = document.createElement("div");
  row.className = "guess";
  for(let j = 0; j < WORD_LENGTH; j++) {
    const square = document.createElement("div");
    square.setAttribute("class", "box");
    row.appendChild(square);
  }
  body.appendChild(row);
  grid.appendChild(row);
}
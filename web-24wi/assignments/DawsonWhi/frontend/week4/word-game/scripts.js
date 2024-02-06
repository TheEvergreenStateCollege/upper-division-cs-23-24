async function init() {

    // Try the GET method
    const res = await fetch("https://words.dev-apis.com/word-of-the-day");
    const { word: wordRes } = await res.json();
    console.log(`The word I guessed was ${wordRes}`);
  
    // Try the POST method
    const res2 = await fetch("https://words.dev-apis.com/word-of-the-day", { "method": "POST" });
    const { word: wordRes2, validWord: validWordRes2 } = await res2.json();
    console.log(`The word I guessed was ${wordRes2}`);
    console.log(`Was it valid? ${validWordRes2}`);
}

init();

const body = document.getElementsByTagName("body")[0];
const grid = document.getElementsByTagName("grid")[0];
const WORD_LENGTH = 5;
const GUESS_LENGTH = 6;

for(let i = 0; i < GUESS_LENGTH; i++){
  const row = document.createElement("div");
  row.className = "guess";
  for(let j = 0; j < WORD_LENGTH; j++) {
    const square = document.createElement("div");
    square.setAttribute("class", "box");
    row.appendChild(square);
  }
  body.appendChild(row);
}
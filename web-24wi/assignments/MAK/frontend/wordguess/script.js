const body = document.getElementsByTagName("body")[0];
//gets a list ^ indext first element
const WORD_LENGTH = 5;
const GUESS_LENGTH = 6;
const belly = document.getElementsByClassName("belly");
let isletter = /^[a-zA-Z]+$/;

var boxTraker = 0;
var rowTraker = 0;
var inputWord = "";
var findWord = "";

//make grid-----------------------------------------------
//2 nested loops 6, 5 (C styled)
for (let i=0; i < GUESS_LENGTH; i++){
  
  const row = document.createElement("div");  //make row holder
  //row.className("guess");
  row.setAttribute("class", "guess");   //label class
  row.setAttribute("Id", "guess"+i);    //label Id
  document.getElementById("belly").appendChild(row); //adds it to html
  
  for (let j=0; j < WORD_LENGTH; j++){
    const square = document.createElement("div"); //make div
    square.setAttribute("class", "box");          //add class
    square.setAttribute("Id", "box" +i+j);    //label each box
    document.getElementById("guess"+i).appendChild(square); //adds it to html
  }
}

//get letter ---------------------------------
addEventListener("keydown", (event) => {
  //check if input is a letter
  if ((isletter.test(event.key)) && (event.key.length == 1)) {
    var newBox = "box" + rowTraker + boxTraker; //get box

    //get next box
    if (boxTraker >= 4){
      boxTraker = 0;
      rowTraker = rowTraker + 1;

      checkWord();
      inputWord = ''; //clear inputWord
    }
    else{
      boxTraker = boxTraker + 1;
      inputWord = inputWord.concat(event.key); //add letter to inputWord
    }

     //print in box
    document.getElementById(newBox).innerHTML = event.key;
  }
  
});


//get word---------------------------------------------
async function init(){
  const res = await fetch("https://words.dev-apis.com/word-of-the-day");
  const { word: wordRes } = await res.json();
  
  findWord = wordRes;
}

init();

//check word----------------------
async function checkWord(){
  
  //go throw letters
  for (let i=0; i < WORD_LENGTH; i++){
    var tempbox = 'box'+ (rowTraker-1) + i; //get tempbox
    document.getElementById(tempbox).style.backgroundColor = "red"; //no match
    
     //check letters matches
    if (inputWord.charAt(i) == findWord.charAt(i)){
      document.getElementById(tempbox).style.backgroundColor = "green"; //match
    }
    //check it findWord contans letter
    else {
      for (let j=0; j < WORD_LENGTH; j++){
        if (inputWord.charAt(i) == findWord.charAt(j)){
          document.getElementById(tempbox).style.backgroundColor = "yellow"; //match wrong places
        }
      }
    }
  }
}

// Propose script for word masters, known as v2. Currently buggy so I'm not using it.

const ANSWER_LENGTH = 5;
const ROUNDS = 6;
const letters = document.querySelectorAll(".scoreboard-letter");
const loadingDiv = document.querySelector(".info-bar");

async function init() {
 let currentRow = 0;
 let currentGuess = "";
 let done = false;
 let isLoading = true;

 try {
  const word = await fetchWord();
  const wordParts = word.split("");
  isLoading = false;
  setLoading(isLoading);

  function addLetter(letter) {
   currentGuess += letter;
   if (currentGuess.length > ANSWER_LENGTH) {
    currentGuess = currentGuess.slice(0, ANSWER_LENGTH);
   }
   updateLettersDisplay();
  }

  async function commit() {
   if (currentGuess.length !== ANSWER_LENGTH) {
    return;
   }

   isLoading = true;
   setLoading(isLoading);

   try {
    const validWord = await validateWord(currentGuess);
    isLoading = false;
    setLoading(isLoading);

    if (!validWord) {
     markInvalidWord();
     return;
    }

    const guessParts = currentGuess.split("");
    const map = makeMap(wordParts);
    let allRight = true;

    for (let i = 0; i < ANSWER_LENGTH; i++) {
     if (guessParts[i] === wordParts[i]) {
      letters[currentRow * ANSWER_LENGTH + i].classList.add("correct");
      map[guessParts[i]]--;
     }
    }

    for (let i = 0; i < ANSWER_LENGTH; i++) {
     if (guessParts[i] === wordParts[i]) {
      // do nothing
     } else if (map[guessParts[i]] && map[guessParts[i]] > 0) {
      allRight = false;
      letters[currentRow * ANSWER_LENGTH + i].classList.add("close");
      map[guessParts[i]]--;
     } else {
      allRight = false;
      letters[currentRow * ANSWER_LENGTH + i].classList.add("wrong");
     }
    }

    currentRow++;
    currentGuess = "";

    if (allRight) {
     alert("You Won!!");
     document.querySelector(".brand").classList.add("winner");
     done = true;
    } else if (currentRow === ROUNDS) {
     alert(`You lose, the word was ${word}`);
     done = true;
    }
   } catch (error) {
    console.error("Error occurred during word validation:", error);
    isLoading = false;
    setLoading(isLoading);
   }
  }

  function backspace() {
   currentGuess = currentGuess.slice(0, -1);
   updateLettersDisplay();
  }

  function updateLettersDisplay() {
   for (let i = 0; i < currentGuess.length; i++) {
    letters[currentRow * ANSWER_LENGTH + i].innerText = currentGuess[i];
   }
  }

  function markInvalidWord() {
   for (let i = 0; i < ANSWER_LENGTH; i++) {
    letters[currentRow * ANSWER_LENGTH + i].classList.remove("invalid");
    setTimeout(
     () => letters[currentRow * ANSWER_LENGTH + i].classList.add("invalid"),
     10
    );
   }
  }

  document.addEventListener("keydown", function handleKeyPress(event) {
   if (done || isLoading) {
    return;
   }

   const action = event.key;

   if (action === "Enter") {
    commit();
   } else if (action === "Backspace") {
    backspace();
   } else if (isLetter(action)) {
    addLetter(action.toUpperCase());
   }
  });
 } catch (error) {
  console.error("Error occurred during initialization:", error);
  isLoading = false;
  setLoading(isLoading);
 }
}

function isLetter(letter) {
 return /^[a-zA-Z]$/.test(letter);
}

function setLoading(isLoading) {
 loadingDiv.classList.toggle("hidden", !isLoading);
}

function makeMap(array) {
 const obj = {};
 for (let i = 0; i < array.length; i++) {
  if (obj[array[i]]) {
   obj[array[i]]++;
  } else {
   obj[array[i]] = 1;
  }
 }
 return obj;
}

async function fetchWord() {
 const res = await fetch("https://words.dev-apis.com/word-of-the-day");
 const { word: wordRes } = await res.json();
 return wordRes.toUpperCase();
}

async function validateWord(word) {
 const res = await fetch("https://words.dev-apis.com/validate-word", {
  method: "POST",
  body: JSON.stringify({ word }),
 });
 const { validWord } = await res.json();
 return validWord;
}

init();

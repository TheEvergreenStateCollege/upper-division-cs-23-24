// once again borrowed from pswish

/* maybe this is also causing errors
const letters = document.querySelectorAll('.board-letter');
const loadingDiv = document.querySelector('.info-bar');
const ANSWER_LENGTH = 5;
*/

async function init () {
    
  // Try the GET method
  const res = await fetch("https://words.dev-apis.com/word-of-the-day");
  const { word: wordRes } = await res.json();
  console.log(`The word I guessed was ${wordRes}`);

  // Try the POST method
  const res2 = await fetch("https://words.dev-apis.com/word-of-the-day",
    { "method": "POST" });
  const { word: wordRes2, validWord: validWordRes2 } = await res.json();
  console.log(`The word I guessed was ${wordRes2}`);
  console.log(`Was it valid? ${validWordRes2}`);
    
    /* Commenting this out to try the in class thing from the 30th

    let currentGuess = '';
    let currentRow = 0;

    // Word of the day api pull set to random
    const response = await fetch('https://words.dev-apis.com/word-of-the-day?random=1');
    const resObject = await response.json();
    const word = resObject.word.toUpperCase();
    const wordParts = word.split('');
    setLoading = false;

    function addLetter(letter) {
        if (currentGuess.length < ANSWER_LENGTH) {
            // add letter to the end
            currentGuess += letter;
        } else {
            //replace the last letter with a keystroke
            currentGuess = currentGuess.substring(0, currentGuess.length -1) + letter;
        }

        letters[ANSWER_LENGTH * currentRow + currentGuess.length - 1].innerText = letter;
    }
    async function commit() {
        if (currentGuess.length != ANSWER_LENGTH) {
            // pass, do nothing
            return;
        }

        // TODO Validate the word, after API and show loader icon
        // TODO do all the marking as correct close or wrong, after API and show loader icon
        const guessItems = currentGuess.split('');  // Array of letters
        const map = makeMapObject(wordParts);
        console.log(map);

        for (let i=0; i<ANSWER_LENGTH; i++){
            // Mark correct items
            if (guessItems[i] === wordParts[i]) {
                letters[currentRow * ANSWER_LENGTH + i].classList.add("correct");
                map[guessItems[i]]--;
            }
        
        for (let i = 0; i < ANSWER_LENGTH; i++) {
            if (guessItems[i] === wordParts[i]) {
                // do nothing, we already made it
            } else if (wordParts.includes(guessItems[i])) {
                letters[currentRow * ANSWER_LENGTH + i].classList.add("close");
            } else { 
                letters[currentRow * ANSWER_LENGTH + i].classList.add("wrong");
            }   
        }


        }
        // TODO did the user win?, after API and show loader icon

        currentRow++;
        currentGuess = "";
    }
        function backspace () {
            currentGuess = currentGuess.substring(0, currentGuess.length -1);
            letters[ANSWER_LENGTH * currentRow + currentGuess.length].innerText = "";
        }
    
    
    document.addEventListener('keydown', function (event) {
        const action = event.key;

        console.log(action);

        if (action === 'Enter') {
            commit();
        } else if (action === 'Backspace') {
            backspace();
        } else if (isLetter(action)) {
            addLetter(action.toUpperCase())
        } else {
            // null result, do nothing
        }
    }); */

}
function isLetter(letter) {
        return /^[a-zA-Z]$/.test(letter);
}

function setLoading(isLoading)  {
    loadingDiv.classList.toggle('show', isLoading);
}

function makeMapObject(array) {
    const obj = {};
    for (let i = 0; i < array.length; i++) {
        const letter =  array[i];
        if (obj[letter]) {
            obj[letter]++;
        } else {
            obj[letter] = 1;
        }
    }
}
init();


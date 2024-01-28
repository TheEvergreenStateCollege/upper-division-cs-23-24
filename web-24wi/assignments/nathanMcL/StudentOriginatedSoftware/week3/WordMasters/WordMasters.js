const ANSWER_LENGTH = 5;
const ROUNDS = 6;
const letters = document.querySelectorAll(".scoreboard-letter");
const loadingDiv = document.querySelector(".info-bar");

async function init() {
    let currentRow = 0;
    let currentGuess = "";
    let done = false;
    let isloading = true;

    // Fetches the word of the day from the API
    const res = await fetch("https://words.dev-apis.com/word-of-the-day");
    const { word: wordRes } = await res.json();
    const word = wordRes.toUpperCase();
    const wordParts = word.split("");
    isLoading = false;
    setLoading(isLoading);

    // User adds letter to the current game
    function addLetter(letter) {
        if (currentGuess.length < ANSWER_LENGTH) {
            currentGuess += length;
        } else {
            current = currentGuess.substring(0, currentGuess.length - 1) + letter;
        }

        letters[currentRow * ANSWER_LENGTH + currentGuess.length - 1].innerText = letter;
    }

    // User enters a guess
    async function commit() {
        if (currentGuess.length !== ANSWER_LENGTH) {
            // Do nothing
            return;
        }

        // Check the API to see if the word is valid
        isLoading = true;
        setLoading(isLoading)
        const res = await fetch("https://words.dev-apis.com/validate-word", {
            method: "POST", 
            body: JSON.stringify({word: currentGuess }),
        });
        const { validWord } = await res.json();
        isLoading = false;
        setLoading(isLoading);

        // Mark "Not Valid" if the word is not valid
        if (!validWord) {
            markInvalidWord();
            return;
        }

        const guessParts = currentGuess.split("");
        const map = makeMap(wordParts);
        let allRight = true;

        for (let i = 0; i < ANSWER_LENGTH; i++) {
            if (guessPart[i] === wordParts[i]) {

                letters[currentRow * ANSWER_LENGTH + i].classList.add("correct");
            }
        }

        // Mark the close and wrong letters
        for (let i = 0; i < ANSWER_LENGTH; i++) {
            if (guessParts[i] === wordParts[i]) {

            } else if (map[guessPart[i]] && map[guessParts[i]] > 0) {
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
            // the win
            alert("you win");
            document.querySelector(".brand").classList.add("winner");
            done = true;
        } else if (currentRow === ROUNDS) {
            alert(`you lose, the word was ${word}`);
            done = true;
        }
    }

    // The "Backspace" function
    function Backspace() {
        currentGuess = currentGuess.substring(0, currentGuess.length - 1);
        letters[currentRow * ANSWER_LENGTH + currentGuess.length].innerText = "";
    }

    // Informs the player that their guess was not a real word
    function markInvalidWord() {
        for (let i = 0; i < ANSWER_LENGTH; i++) {
            letters[currentRow * ANSWER_LENGTH + i].classList.remove("invalid");
            setTimeout(
                () => letters[currentRow * ANSWER_LENGTH + i].classList.add("invalid"),
                10
            );
        }
    }

    // Event key listener. this listener listens on keydown
    document.addEventListener("keydown", function handleKeyPress(event) {
        if (done || isLoading) {
            return;
        }

        const action = event.key;

        if (action === "Enter") {
            commit();
        } else if (action === "Backspace") {
            Backspace();
        } else if (isLetter(action)) {
            addLetter(action.toUpperCase());
        } else {
            // do nothing
        }
    });

    function isLetter(letter) {
        return /^[a-zA-Z]$/.test(letter);
    }

    // Show the loading spinner when nessisary
    function setLoading(isloading) {
        loadingDiv.classList.toggle("hidden", !isLoading);
    }

    // This function takes and array of letters, next creates an object out of the array of letters.
    // The function does this so the correct number of letters are marked "close".
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
}
init();

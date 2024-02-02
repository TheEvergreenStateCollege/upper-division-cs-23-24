const rows = 6;
const columns = 5;

currentWord = "";
dailyWord = "";
currentRow = 0;
currentCol = 0;
gameOngoing = true;

init();

document.getElementById("main").addEventListener("keydown", (event) => {
    if (gameOngoing) {
        const key = event.key;
        if (isLetter(key)) {
            addLetter(key.toUpperCase());
        } else if (key == "Enter") {
            commit();
        } else if (key == "Backspace") {
            backspace();
        }
    }
});

async function init() {
    makeGrid();
    document.getElementById("main").focus();
    dailyWord = await getWord();
    dailyWord = dailyWord.toUpperCase();
}

async function getWord() {
    const res = await fetch("https://words.dev-apis.com/word-of-the-day/");
    const { word: wordRes } = await res.json();
    return wordRes;
}

function addLetter(letter) {
    if (currentWord.length < columns) {
        currentWord += letter;
        let targetId = currentRow + " " + currentCol;
        let item = document.getElementById(targetId);
        item.textContent = letter;
        currentCol++;
    }
}

async function commit() {
    let isValid = await validateWord(currentWord);
    if (isValid) {
        checkWord();
        currentRow++;
        currentCol = 0;
        currentWord = "";
        if (currentRow == rows+1) {
            gameOngoing = false;
            document.getElementById("title").className = "lose";
        }
    }
}

function checkWord() {
    colorTiles();
    if (currentWord == dailyWord) {
        document.getElementById("title").className = "win";
    }
}

function colorTiles() {
    let dailyWordCopy = dailyWord; //Used to track which chars have already been used
    for(let i = 0; i < columns; i++) {
        let tile = document.getElementById(currentRow + " " + i);
        if (dailyWord.charAt(i) == currentWord.charAt(i)) {
            tile.classList.add("green-tile");
            dailyWordCopy = dailyWordCopy.replace(currentWord.charAt(i), ""); //prevents incorrectly making tiles yellow if the char is already accouted for in green tiles
        }
    }
    for(let i = 0; i < columns; i++) { //Second pass so all green tiles are removed from dailyWordCopy first
        let tile = document.getElementById(currentRow + " " + i);
        if (!tile.classList.contains("green-tile")) {
            if (dailyWordCopy.includes(currentWord.charAt(i))) {
                tile.classList.add("yellow-tile");
                dailyWordCopy = dailyWordCopy.replace(currentWord.charAt(i), "");
            } else {
                tile.classList.add("gray-tile");
            }
        }
    }
}

function backspace() {
    currentWord = currentWord.slice(0,-1);
    currentCol--;
    if (currentCol < 0) { currentCol = 0;}
    document.getElementById(currentRow + " " + currentCol).textContent = "";

}

function isLetter(letter) {
    return /^[a-zA-Z]$/.test(letter);
}

function makeGrid() {
    const gridContainer = document.getElementById("wordle-grid");
    for (let i = 0; i < rows; i++) {
        const gridRow = document.createElement("div");
        gridRow.className = "grid-row";
        gridRow.id = "row-" + i;
        gridContainer.appendChild(gridRow);
        
        for (let j = 0; j < columns; j++) {
            const gridItem = document.createElement("div");
            gridItem.className = "grid-item";
            gridItem.id = i + " " + j;
            gridItem.textContent = "";
            gridRow.appendChild(gridItem);
        }
    }
}

async function validateWord(input) {
    const res = await fetch("https://words.dev-apis.com/validate-word", {
        method: "POST",
        body: '{"word":"' + input + '"}',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
    const { word: _, validWord: validRes } = await res.json();
    return validRes;
}
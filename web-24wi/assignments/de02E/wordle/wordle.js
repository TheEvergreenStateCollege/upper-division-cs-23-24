let wordle
let rows = []
let position = [0,0]
let letters = Array(6).fill(0).map(x => Array(5).fill(0))
let regex =  /^[a-zA-Z]+$/
const WORDS_URL = "https://words.dev-apis.com/validate-word"
const SOLUTION_URL = "https://words.dev-apis.com/word-of-the-day"


function validateWord(word) {
    let json = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({word: word})
    }
    const promise = fetch(WORDS_URL,json)
    promise
        .then(function(response) {
            const processingResponse = response.json()
            return processingResponse
        })
        .then(function (processedResponse) {
            if (processedResponse.validWord) {
                compareWord(word)
            }
            return processedResponse.validWord
        })
}


let solution
async function getSolution() {
    const promise = await fetch(SOLUTION_URL)
    const processedResponse = await promise.json()
    solution = processedResponse.word
    return processedResponse.word
}
getSolution()

function compareWord(word) {
    console.log(word)
    console.log(solution)
    if (word == solution) {
        for (let i = 0; i < 5; i++) {
            letters[position[0]-1][i].style.backgroundColor = "green"
            document.removeEventListener("keydown", input)
            document.getElementById("congrats").innerText = "Congratulations! You win!"
        }
    }
    let wordLetters = word.split("")
    let solLetters = solution.split("")
    let colorArray = [0,0,0,0,0]
    for (let i = 0; i < wordLetters.length; i++) {
        console.log(wordLetters[i])
        console.log(solLetters[i])
        if (solLetters[i] == wordLetters[i]) {
            colorArray[i] = 2
        }
    }
    console.log(colorArray)
    for (let i = 0; i < wordLetters.length; i++) {
        if (colorArray[i] == 0) {
            for (j = 0; j < wordLetters.length; j++) {
                if (colorArray[j] == 0) {
                    if (solLetters[j] == wordLetters[i]) {
                        colorArray[i] = 1
                        solLetters[j] = ""
                    } 
                }
                
            }
        }
    }
    for (let i = 0; i < wordLetters.length; i++) {
        if (colorArray[i] == 0) {
            console.log(colorArray)

        } else if (colorArray[i] == 1) {
            letters[position[0]-1][i].style.backgroundColor = "yellow"
        } else if (colorArray[i] == 2) {
            letters[position[0]-1][i].style.backgroundColor = "green"
        }
    }
    
}

function load() {
    wordle = document.getElementById("wordle")
    rows.push(document.getElementById("line1"))
    for (let i = 0; i < 6; i++) {
        console.log(i)
        if (i == 0) {
            for (let j = 1; j < 5; j++) {
                let letterClone = document.getElementById("line1letter1").cloneNode(true)
                let left = 10 + (20*j)
                letterClone.style.left = left + "%"
                letterClone.id = "line" + (i+1) + "letter" + (j+1)
                rows[i].appendChild(letterClone)
            }
        } else {
            let lineClone = rows[0].cloneNode(true)
            let height = 10 + ((96/6)*i)
            lineClone.style.top = height + "%"
            lineClone.id = "line" + (i+1)
            rows.push(lineClone)
            let children = []
            children.push(...rows[i].getElementsByClassName("letter"))
            for (let j = 0; j < 5; j++) {
                children[j].id = "line" + (i+1) + "letter" + (j+1)
            }
            wordle.appendChild(lineClone)
        }
    }
    for (let k = 0; k < 6; k++) {
        for (let j = 0; j < 5; j++) {
          console.log(k)
            letters[k][j] = document.getElementById("line" + (k+1) + "letter" + (j+1))
        }
    }
  console.log(letters)
}
document.addEventListener("DOMContentLoaded",load)

function input(inputKey) {
  inputKey = inputKey.key
  console.log(position)
    if (inputKey == "Backspace") {
        if (position[1] > 0 && position[1] < 4) {
            letters[position[0]][position[1]-1].innerText = ""
            position[1]--
        } else if (position[1] == 4) {
              if (letters[position[0]][position[1]].innerText == "") {
                  letters[position[0]][position[1]-1].innerText = ""
                  position[1]--
              } else {
                  letters[position[0]][position[1]].innerText = ""
              }
            
        }
    } else if (inputKey == "Enter") {
        if (position[1] == 4) {
            if (position[0] == 5) {
                document.removeEventListener("keydown", input)
            document.getElementById("congrats").innerText = "Congratulations! You lose!"
                return
            }
            if (letters[position[0]][position[1]].innerText != "") {
                let lineWord = ""
                for (let i = 0; i < 5; i++) {
                    lineWord = lineWord + letters[position[0]][i].innerText
                }
                // API business
                validateWord(lineWord)
                position[1] = 0
                position[0]++
            }
        }

    } else if (regex.test(inputKey) && inputKey.length == 1) {
      console.log("array is: " + letters[0][0])
        letters[position[0]][position[1]].innerText = inputKey
        if (position[1] < 4) {
          position[1] +=1
        }
    }
}

document.addEventListener("keydown", input)
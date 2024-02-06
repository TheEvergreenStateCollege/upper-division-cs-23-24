// Try the GET method

async function init() {
    const res = await fetch("https://words.dev-apis.com/word-of-the-day");
    const { word: wordRes } = await res.json();
    console.log(`The word I guessed was ${wordRes}`);


// Trying the POST method
    const res2 = await fetch("https://words.dev-apis.com/word-of-the-day", { "method": "POST" });
    const { word: wordRes2, validWord: validWordRes2 } = await res2.json();
    console.log(`The word I guessed was ${wordRes2}`);
    console.log(`Was it valid? ${validWordRes2}`);
}

init();
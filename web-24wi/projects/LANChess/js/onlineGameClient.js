//onlineGameClient.js

//***GLOBAL VAR***//////////////////////////////////////////////////////////////////////

var userID;
var userToken;
var gameID;
var userColor;
var gameActive;

//The boardCache is what the board position should always be for the client. Initialized to starting chess position:
var boardCache = 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR';
//This var is dynamic to the number of turns in the game.
var turnCounter = 0;
//This boolean represents if it's the user's turn to play.
var isItUsersTurn;
//This boolean reflects if the game is still ongoing or complete.
var gameActive;

//***INITIALIZING FUNCTIONS***///////////////////////////////////////////////////////////

//Loading the values stored in local storage from the home page:
async function getUserValuesFromStorage(){

    //Loading userID, userToken, gameID and userColor from local storage to the global var of userID, userToken.
    userID = localStorage.getItem('userID');
    userToken = localStorage.getItem('userToken');
    gameID = localStorage.getItem('gameID');
    userColor = localStorage.getItem('userColor');

    //Checking that the value is defined.
    if (userID !== null){
        console.log('userID value loaded from local storage: ' + userID);
    } else {
        console.error('userID value in local storage is undefined', error)
    }

    //Checking that the value is defined.
    if (userToken !== null){
        console.log('userToken value loaded from local storage: ' + userToken);
    } else {
        console.error('userID value in local storage is undefined', error)
    }

     //Checking that the value is defined.
     if (gameID !== null){
        console.log('gameID value loaded from local storage: ' + gameID);
    } else {
        console.error('gameID value in local storage is undefined', error)
    }

     //Checking that the value is defined.
     if (userColor !== null){
        console.log('userColor value loaded from local storage: ' + userColor);
    } else {
        console.error('userColor value in local storage is undefined', error)
    }
}

//Loads a window alert with the code for a friend to join the game.
async function gameWaitingRoom(userColor){
    if (userColor === 'white') {
        alert('ROOM CODE: ' + '[ ' + gameID + ' ]');
    }
}


//***GAME ENGINE FUNCTIONS***////////////////////////////////////////////////////////////////

//Determines if it's the user's turn to play based off the turnCounter.
async function determineIsItUsersTurn() {


    if (userColor === 'white'){
        if (turnCounter % 2 === 0) {
            isItUsersTurn = true;
        } else {
            isItUsersTurn = false;
        }
    }

    if (userColor === 'black'){
        if (turnCounter % 2 === 1) {
            isItUsersTurn = true;
        } else {
            isItUsersTurn = false;
        }
    }
}


async function confirmMove() {

    //First check if the game is active, and then if it's the user's turn
    if (gameActive === true && isItUsersTurn === true) {
        boardCache = board.fen();
        const message = {
            FEN_string: boardCache,
        }
        //Since those are true it will send the message to the socket
        //iterate on the turnCounter, determine if it's the users turn
        //and render the board according to the boardCache:
        ws.send(JSON.stringify(message));
        turnCounter++;
        await determineIsItUsersTurn();
        await renderBoard(boardCache, userColor, isItUsersTurn); 

    } else if(gameActive === false) {
        window.alert('The game is over, but where is everyone?.....');
    } else if(isItUsersTurn === false) {
        window.alert('Nice try, its not your turn!');
        boardCache = boardCache;
    } else {
        console.log('Failed to confirm move...for some reason I guess? You tell me!');
    }
}


//***GAME RENDERER FUNCTIONS***////////////////////////////////////////////////////////////////

//This the function that renders the board, it's called plenty throughout.
async function renderBoard(boardCache, userColor, isItUsersTurn){
    var config = {
        showNotation: false,
        draggable: isItUsersTurn,
        moveSpeed: 'slow',
        snapbackSpeed: 1000,
        snapSpeed: 200,
        dropOffBoard: 'snapback',
        position: boardCache, 
        orientation: userColor
    };
    board = Chessboard('board', config);
    boardCache = board.fen(); //Ensures the board cache is always the same as the current board state.
}

//***CALL FUNCTIONS***//////////////////////////////////////////////////////////////////

async function initializeNewGameOnClient() {
    gameActive = true;
    await getUserValuesFromStorage();
    await determineIsItUsersTurn();
    await renderBoard(boardCache, userColor, isItUsersTurn);
    await gameWaitingRoom(userColor);
}

//Now to initialize:
initializeNewGameOnClient();


//***WEB SOCKET***////////////////////////////////////////////////////////////////

const proto = window.location.protocol === "https:" ? "wss" : "ws";
const ws = new WebSocket(`${proto}://${window.location.host}?userid=${userID}&gameid=${gameID}`);


ws.onopen = (event) => {
    try{
    console.log('Web socket connection successful');
    } catch(error) {
        console.error('Failed to connect to web socket: ', error);
    }
}

ws.onerror = (event) => {
    console.error;
}

ws.onmessage = (event) => {

    const messageBuffer = JSON.parse(event.data);
    const message = messageBuffer.FEN_string;
    console.log("Message recieved from server: " + message)
    boardCache = message;
    turnCounter++;
    determineIsItUsersTurn();
    renderBoard(boardCache, userColor, isItUsersTurn);
}

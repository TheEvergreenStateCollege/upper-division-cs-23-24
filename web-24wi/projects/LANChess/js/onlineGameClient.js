//onlineGameClient.js


//Variables
var gameStatus;
var boardCache = 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR';
var userID;
var userToken;
var gameID;
var participantID;


var userColor;
var turnCounter = 0;
var isItUsersTurn;

//Determine game status


//Initial user value functions and calls
async function getUserValuesFromStorage(){
    userID = localStorage.getItem('userID');
    userToken = localStorage.getItem('userToken');
    userColor = localStorage.getItem('userColor');
}
getUserValuesFromStorage();

async function getGameValuesFromStorage(){
    gameID = localStorage.getItem('userID');
    participantID = localStorage.getItem('participantID')
}
getGameValuesFromStorage();

////////////////////////////////////////////////////////
////////////////////////////////////////////////////////
////////////////////////////////////////////////////////


async function determineIsItUsersTurn(){
    if (userColor === 'white' && turnCounter % 2 === 0 ) {
        isItUsersTurn = true;
        // console.log("It is the users turn");
    } else if (userColor === 'white' && turnCounter % 1 === 0) {
        isItUsersTurn = false;
        // console.log("It is not the users turn");
    } else if (userColor === 'black' && turnCounter % 1 === 0) {
        isItUsersTurn = true;
        // console.log("It is the users turn")
    } else if (userColor === 'black' && turnCounter % 2 === 0) {
        isItUsersTurn = false;
        console.log("It is not the users turn");
    } else {
        // console.log("Turn counter error");
    }
}

//Functions

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


async function initializeNewGameOnClient() { 
    determineIsItUsersTurn();
    await renderBoard(boardCache, userColor, isItUsersTurn); //Render the board.
}


async function confirmMove() {

    determineIsItUsersTurn();
    // console.log("User clicked confirm move btn, the value of isItUsersTurn is: " + isItUsersTurn)

    if(isItUsersTurn === true){
        // const boardBuffer = board.fen();
        boardCache = board.fen();
        const message = {
            FEN_string: boardCache,
        }

        ws.send(JSON.stringify(message));
        // console.log("socket message sent" + JSON.stringify(message));
        turnCounter++;
        await determineIsItUsersTurn();
        
        await renderBoard(boardCache, userColor, isItUsersTurn); 
        // console.log("Send test isItUsersTurn" + isItUsersTurn)

    } else if(isItUsersTurn === false){
        window.alert('Not your turn to play!');
    } else {
        // console.error("Move failed to send, still your turn", error);
    }
}

//Calls
initializeNewGameOnClient();




//Open web socket

const proto = window.location.protocol === "https:" ? "wss" : "ws";
const ws = new WebSocket(`${proto}://${window.location.host}?userid=${userID}&gameid=${gameID}`);


ws.onopen = (event) => {
    data = {message: "hello world "};
    ws.send(JSON.stringify(data));
}
ws.onerror = (event) => {
    console.error;
}

ws.onmessage = (event) => {
    const message = JSON.parse(event.data);

    if (message.FEN_string) {
        console.log(message.FEN_string);
        boardCache = message.FEN_string;
        turnCounter++;
        // console.log("Game is on move" + turnCounter)
        determineIsItUsersTurn();
        renderBoard(boardCache, userColor, isItUsersTurn); 
    } else {
        // console.log(message);
        console.log("FROM SERVER: " + JSON.stringify(message));
    }
}
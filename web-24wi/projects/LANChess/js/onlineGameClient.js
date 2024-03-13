//onlineGameClient.js


//Variables
var gameStatusONGOING = true;
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
    gameID = localStorage.getItem('gameID');
    participantID = localStorage.getItem('participantID')
}
getGameValuesFromStorage();

async function gameStartPopupCheck(userColor){
    if (userColor === 'white') {
        window.alert('GAME CREATED SUCCESSFULLY, GIVE THIS ROOM ID TO FRIEND' + '[ ' + gameID + ' ]');
    }
}

gameStartPopupCheck(userColor);


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

////FUNCTIONS/////////////////////////////////////////////////////////////////////////////////////

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

    if(isItUsersTurn === true && gameStatusONGOING === true){
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
        console.log("Move sent: " + boardCache);

    } else if(isItUsersTurn === false){
        window.alert('Not your turn to play!');
    } else {
        console.log('else else else case')
    }
}

//Calls
initializeNewGameOnClient();




////WEB SOCKET/////////////////////////////////////////////////////////////////////////////////////


const proto = window.location.protocol === "https:" ? "wss" : "ws";
const ws = new WebSocket(`${proto}://${window.location.host}?userid=${userID}&gameid=${gameID}`);


ws.onopen = (event) => {
    if (userColor === 'black') {
    data = {message: "ACK"};
    ws.send(JSON.stringify(data));
    console.log("You play black");
    } else {
        console.log("You play white");
    }
}
ws.onerror = (event) => {
    console.error;
}

ws.onmessage = (event) => {
    const messageBuffer = JSON.parse(event.data);
    const message = messageBuffer.FEN_string;


    if (message === 'ACK') {
        gameStatusONGOING = true;
        console.log("ACK recieved")
    } else if (message.FEN_string) {
        console.log(message.FEN_string);
        boardCache = message.FEN_string;
        turnCounter++;
        determineIsItUsersTurn();
        renderBoard(boardCache, userColor, isItUsersTurn); 
    } else {
        // console.log(message);
        console.log("FROM SERVER: " + JSON.stringify(message));
    }
}




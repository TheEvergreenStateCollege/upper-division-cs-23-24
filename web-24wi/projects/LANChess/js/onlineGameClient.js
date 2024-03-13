//onlineGameClient.js

//Variables
var boardCache = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"
var userID;
var userToken;
var gameID;
var participantID;

var colorToPlay;
var moveCounter;
var isItUsersTurn = true;

//Functions
async function getUserValuesFromStorage(){
    userID = localStorage.getItem('userID');
    userToken = localStorage.getItem('userToken');
    userColor = localStorage.getItem('userColor');
}

async function getGameValuesFromStorage(){
    gameID = localStorage.getItem('userID');
    participantID = localStorage.getItem('participantID')
}

async function getCountOfMovesInGame(){
    try {
        const apiResponse = await fetch("/api/games/" + gameID, {
            method: "GET", 
            headers: {
                'Authorization': 'Bearer ' + userToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        })

        const allMovesObj = await apiResponse.json();
        console.log("response from server regarding moves: " + JSON.stringify(allMovesObj));
        return allMovesObj;

    } catch(error) {
        console.error("Failed to create new game on the server: ", error);
    } 
}  

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
}


async function initializeNewGameOnClient() { 
    getUserValuesFromStorage(); //Get initial user values.
    await getGameValuesFromStorage(); //Get game values.
    await getCountOfMovesInGame(); //Fetches for all moves the server has for the game.
    await renderBoard(boardCache, userColor, isItUsersTurn); //Render the board.
}

//Calls
initializeNewGameOnClient();


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
    boardCache = board.fen(); //Ensures the board cache is always the same as the current board state.
}


async function initializeNewGameOnClient() { 
    getUserValuesFromStorage(); //Get initial user values.
    await getGameValuesFromStorage(); //Get game values.
    await getCountOfMovesInGame(); //Fetches for all moves the server has for the game.
    await renderBoard(boardCache, userColor, isItUsersTurn); //Render the board.
}

async function confirmMove(){
    if(isItUsersTurn === true){
        boardCache = board.fen()
        const fenToSend = boardCache;

        try {
            const response = await fetch("/api/moves", {
                method: "POST", 
                headers:{
                    'Authorization': 'Bearer ' + userToken,
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify( {
                    'fen_string': fenToSend,
                    'user_id': userID,
                    'game_id': gameID
                })
            })
    
            const addMoveResponseObj = await response.json();
            console.log("addMoveResponseObj:" + JSON.stringify(addMoveResponseObj));
            return addMoveResponseObj;
    
        } catch (error) {
            console.error("Failed to add self as participant:", error);
        }
    }
}

//Calls
initializeNewGameOnClient();


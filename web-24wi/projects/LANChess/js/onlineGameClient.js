//onlineGameClient.js
const baseURL = 'http://localhost:5000';


//SETTINGS//
var gameActiveOnline = true; //This boolean represents whether or not a game is active or complete.
var turnCounterOnline = 0; //Keeps track of number of turns in game.
var isItUsersTurnOnline;

var userColorOnline = 'white'; //Defines what color the user is playing.
var colorToPlayOnline = 'white'; //Determines who's color it is to play.


var onlineBoardCache = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR"

var orientationBufferOnline;


//FUNCTIONS//

//Determine if it's user's turn to play?//
function checkIfUsersTurnOnline() {
    if (userColorOnline === 'white') {
        (turnCounterOnline % 2)=== 0 ? isItUsersTurnOnline = true : isItUsersTurnOnline = false;
        
    } else {
        (turnCounterOnline % 1)=== 0 ? isItUsersTurnOnline = true : isItUsersTurnOnline = false;
        
    }

console.log(isItUsersTurnOnline);
}

//Render the board for an online game//
async function renderOnlineBoard(onlineBoardCache, userColorOnline, isItUsersTurn){

    var config = {
        //Static configurations
        showNotation: false,
        draggable: isItUsersTurn,
        moveSpeed: 'slow',
        snapbackSpeed: 1000,
        snapSpeed: 200,
        dropOffBoard: 'snapback',
        position: onlineBoardCache, //This is the board state the user should always be seeing.
        orientation: userColorOnline //This should change dynamically depending on what side the player is playing as represented by the colorOfPiecesForUser as a string of either "White" or "Black".
    };

    onlineBoard = Chessboard('board', config);
    onlineBoardCache = onlineBoard.fen();
   
    
}

//Update the client in an online game//
async function updateClientOnline(){
    checkIfUsersTurnOnline();
    renderOnlineBoard(onlineBoardCache, userColorOnline, isItUsersTurnOnline);
}


//This function generates and store the FEN representation of the board as a string to be sent on the websocket.
async function confirmMoveOnlineBtn() {
    if (isItUsersTurnOnline === true) {
       
        console.log('sending ' + onlineBoardCache); 
    }
    else {
        window.alert("It is not your turn.");
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//CALLS///////////////////////////////
/////////////////////////////////////

updateClientOnline();


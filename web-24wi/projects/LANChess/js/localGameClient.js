//localGameClient.js
const baseURL = 'http://localhost:5000';


//SETTINGS//
var gameActiveLocal = true; //This boolean represents whether or not a game is active or complete.
var turnCounterLocal = 0;
var colorToPlayLocal;
var orientationBufferLocal;
let localBoardCache = 'rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR';



//FUNCTIONS//

function createNewLocalGame(){

    var moves =new Array();
    moves.push(localBoardCache);
    renderLocalBoard(localBoardCache, turnCounterLocal);
    console.log(moves);
    
}

//Render the board for a local game//
async function renderLocalBoard(localBoardCache, turnCounterLocal){
    (turnCounterLocal % 2)=== 0 ? orientationBufferLocal = 'white' : orientationBufferLocal = 'black';

    var config = {
        //Static configurations
        showNotation: false,
        draggable: true,
        moveSpeed: 'slow',
        snapbackSpeed: 1000,
        snapSpeed: 200,
        dropOffBoard: 'snapback',
        position: localBoardCache, //This is the board state the user should always be seeing.
        orientation: orientationBufferLocal //This should change dynamically depending on what side the player is playing as represented by the colorOfPiecesForUser as a string of either "White" or "Black".
    };

    localBoard = Chessboard('board', config);
    localBoardCache = localBoard.fen();
    
}


//Update the client in a local game//
async function updateClientLocal(){
    
    renderLocalBoard(localBoardCache, turnCounterLocal);
    console.log(localBoardCache);
}

async function confirmMoveLocalBtn() {
    localBoardCache = localBoard.fen();
    turnCounterLocal++;
    updateClientLocal();
}


//CALLS//
createNewLocalGame();




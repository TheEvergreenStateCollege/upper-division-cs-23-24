//gameClient.js
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

const baseURL = 'http://localhost:5000';
var draggable;
var defaultFEN = 'start';

//Is the game active?//
var gameActiveLocal = true; //This boolean represents whether or not a game is active or complete.
var gameActiveOnline = true; //This boolean represents whether or not a game is active or complete.

//What color is the user playing?//
var userColorOnline = 'white';

//Which color to play?//
var colorToPlayLocal;
var colorToPlayOnline = 'white';

//Keeping track of turns//
var turnCounterOnline = 0;
var turnCounterLocal = 0;

//FEN sent to opponent via websocket after confirming//
var FENsentOnline = ''; 

//FEN recieved from opponent via websocket to be rendered//
var FENrecievedOnline = ''; 

//This is the state of the board at all times//
var boardCacheOnline = 'r1bqkbnr/pppp1ppp/2n5/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R';
var boardCacheLocal = 'r1bqkbnr/pppp1ppp/2n5/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R'; //Will be a GET from the server so the default position is what the servers says it should be. //This what should be displayed on the user's screen at all times, it's initialized as the keyword 'start' and will be changed as this will be the value called with renderBoard().

//This is used to change the board orientation when needed//
var orientationBufferLocal;
var orientationBufferOnline;
var isItUsersTurnOnline;
var draggable;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Determine if it's user's turn to play?//
function checkIfUsersTurnOnline() {
    if (userColorOnline === 'white') {
        (turnCounterOnline % 2)=== 0 ? isItUsersTurnOnline = true : isItUsersTurnOnline = false;
        
    } else {
        (turnCounterOnline % 1)=== 0 ? isItUsersTurnOnline = true : isItUsersTurnOnline = false;
        
    }
console.log(isItUsersTurnOnline);
}




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Render the board for an online game//
async function renderOnlineBoard(boardCacheOnline, userColorOnline, isItUsersTurn){

    var config = {
        //Static configurations
        showNotation: false,
        draggable: isItUsersTurn,
        moveSpeed: 'slow',
        snapbackSpeed: 1000,
        snapSpeed: 200,
        dropOffBoard: 'snapback',
        position: boardCacheOnline, //This is the board state the user should always be seeing.
        orientation: userColorOnline //This should change dynamically depending on what side the player is playing as represented by the colorOfPiecesForUser as a string of either "White" or "Black".
    };

    boardCacheOnline = Chessboard('board', config);
    return boardCacheOnline
}

//Render the board for a local game//
async function renderLocalBoard(boardCacheLocal, turnCounterLocal){

    (turnCounterLocal % 2)=== 0 ? orientationBufferLocal = 'white' : orientationBufferLocal = 'black';
    
    var config = {
        //Static configurations
        showNotation: false,
        draggable: true,
        moveSpeed: 'slow',
        snapbackSpeed: 1000,
        snapSpeed: 200,
        dropOffBoard: 'snapback',
        position: FEN, //This is the board state the user should always be seeing.
        orientation: orientationBuffer //This should change dynamically depending on what side the player is playing as represented by the colorOfPiecesForUser as a string of either "White" or "Black".
    };

    boardCacheLocal = Chessboard('board', config);
    return boardCacheLocal
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Update the client in an online game//
async function updateClientOnline(){
    checkIfUsersTurnOnline();
    renderOnlineBoard(boardCacheOnline, userColorOnline, isItUsersTurnOnline);
}

//Update the client in a local game//
async function updateClientLocal(){
    renderLocalBoard(boardCacheLocal, turnCounterLocal);
}


updateClientOnline();






////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//This function makes the web socket message to the other client//
async function sendMoveMadeAsFEN(FEN) {
    FENsentOnline = JSON.stringify(FEN);
    console.log(FENsentOnline);
    //Send via web-socket here.
}

//This function generates and store the FEN representation of the board as a string to var FENsent to be posted.
async function confirmMoveOnlineBtn() {
    if (isItUsersTurnOnline === true) {
        sendMoveMadeAsFEN(boardCacheOnline.fen()); //Setting the initialized var to the FEN string of the board with the library's .fen() method.
        console.log(FENsentOnline); //Test case of FEN string //Will create POST for serverBoardStateCache.
    }
    else {
        window.alert("It is not your turn.");
    }
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
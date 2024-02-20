//boardState.js



////////////////////////////////////////////////////////////////////////////////////

//Initial value assignment.
var coinFlip = Math.random();

//Game initialize settings
var gameActive; //This boolean represents whether or not a game is active or complete.
var isGameRematch; //A boolean to represent if the current game is a rematch.
var userPlayedWhiteLastBuffer = true; //A boolean to represent if the user played white last game.
var colorOfPiecesForUserIsWhite = initialOperationToFindIfUserPlaysWhite(); //A boolean, if true then the user is playing the white pieces and plays first, if false playing black. This is found out by the listed method which checks all possible reasons why they would or wouldn't play white.
var colorOfPiecesForUser; //A string conversion (kinda) of colorOfPiecesForUserIsWhite, useful for config settings.

//Turn order management
var turnCounter = 0; //This increments each time the FENsent or FENrecieved value is changed, in order to count the number of moves made in a game. This will also be useful in ensuring proper board rendering.
var isItUsersTurn = turnDecider(); //A boolean that stores whether or not it's the user's turn.

//Turn FEN data management
var FENsent = ''; //This is the fen string the player will POST to the server. This means the user played their turn.
var FENrecieved = ''; //This is the fen string the player will GET from the server. This means the player is playing their turn.
var boardStateCache = 'start'; //This what should be displayed on the user's screen at all times, it's initialized as the keyword 'start' and will be changed as this will be the value called with renderBoard().
////////////////////////////////////////////////////////////////////////////////////

//This function decides who's turn it is.
function turnDecider(){
    if (colorOfPiecesForUserIsWhite === true && turnCounter% 2 === 0) {
        console.log("Your Turn");
        return true;
    } else if (colorOfPiecesForUserIsWhite === true && turnCounter% 2 === 1){
        console.log("Opponent's Turn");
        return false;
    } else if (colorOfPiecesForUserIsWhite === false && turnCounter% 2 === 0) {
        console.log("Opponent's Turn");
        return false;
    } else if (colorOfPiecesForUserIsWhite === false && turnCounter%2 === 1){
        console.log("Your Turn");
        return true;
    }    
}

//This function will return if a boolean is true or false like a coin flip using the random library.
function decideBoolByCoinFlip() {
    if (coinFlip < 0.5){
        return true;
        
    } else {
        return false;
    } 
}

//This function finds if the boolean 'colorOfPiecesForUserIsWhite' should be True or False based on previous games. For obvious reasons if a player is rematching another player and played white last time they should have to play black the next game.
function initialOperationToFindIfUserPlaysWhite() {
    if (isGameRematch === true) {
        if (userPlayedWhiteLastBuffer === true) {
            return false;
        } else 
            return true;
        
    } else {
        return decideBoolByCoinFlip();
    }}

//For the config var it requires a string, so I convert the boolean value of colorOfPiecesForUserIsWhite to a string of 'white' or 'black'.
function assignWBStringForColorOfPiecesForUserFromBool() { 
    if (colorOfPiecesForUserIsWhite === true) {
        colorOfPiecesForUser = 'white'
    } else {
        colorOfPiecesForUser = 'black'
    }
}

//Calling method right away for assignment so it can be used in config.
assignWBStringForColorOfPiecesForUserFromBool(); 



//Here I render the board, still need to implement board rendering as a function so it can rerender the board after each move. Not too far yet.
async function renderBoard(){
var config = {
    //Static configurations
    showNotation: false,
    draggable: true,
    moveSpeed: 'slow',
    snapbackSpeed: 1000,
    snapSpeed: 200,
    dropOffBoard: 'snapback',
    //Dynamic configurations
    position: boardStateCache, //This is the board state the user should always be seeing.
    orientation: colorOfPiecesForUser //This should change dynamically depending on what side the player is playing as represented by the colorOfPiecesForUser as a string of either "White" or "Black".
};
boardPosition = Chessboard('board', config);
return boardPosition;
}
renderBoard();

//This function generates and store the FEN representation of the board as a string to var FENsent to be posted.
function confirmMoveBtn() {
    if (isItUsersTurn === true) {
    console.log('Current position as a FEN string:'); 
    FENsent = boardPosition.fen(); //Setting the initialized var to the FEN string of the board with the library's .fen() method.
    turnCounter++;
    console.log(FENsent); //Test case of FEN string
    }
    else {
        window.alert("Nice try, it's not your turn.");
        boardPosition = boardStateCache; //Resets the board position to the cache, since obviously changes can just be made outside of the proper turn.
    }
}






//boardState.js

var colorOfPiecesForUser; 
var FENforBoardAfterPlayerConfirmedMove = '';

//This function generates and store the FEN representation of the board as a string to var FENforBoardAfterPlayerConfirmedMove.
function clickShowPositionBtn() {
    console.log('Current position as a FEN string:'); 
    FENforBoardAfterPlayerConfirmedMove = boardPosition.fen(); //Setting the initialized var to the FEN string of the board with the library's .fen() method.
    console.log(FENforBoardAfterPlayerConfirmedMove); //Test case of FEN string
}

//Configuration settings for the board object from library.
var config = {

    //Static configurations
    position: 'start',
    showNotation: false,
    draggable: true,
    moveSpeed: 'slow',
    snapbackSpeed: 1000,
    snapSpeed: 200,
    dropOffBoard: 'snapback',
    //Dynamic configurations
    orientation: colorOfPiecesForUser //This should change dynamically depending on what side the player is playing as represented by the colorOfPiecesForUser as a string of either "White" or "Black".
};


//Initializing chessboard from library
var boardPosition = Chessboard('board', config);

//This function will be called when a game ends and will create a txt file with the contents of the game in a pgn that the user can download.
function createPGNFileForCompletedGame() {

};

//Keep the DOM in order
$(document).ready(function() {

}

);







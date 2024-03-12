//homePage.js

//Constant imports from localstorage.
const baseURL = 'http://localhost:5000';
const userID = localStorage.getItem('userID');
const userToken = localStorage.getItem('userToken');

//Variables
var initOpponentUsername;


async function createNewOnlineGameOnServer(){

    console.log("Create new game selected");
    
    
    try {
        const response = await fetch(baseURL + "/api/games", {
            method: "POST", 
            headers: {
                'Authorization': 'Bearer ' + userToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
        },
        body: JSON.stringify({
      })
     
    })

    //Value fetching.
    const createdGameDetailsObj = await response.json(); //Returned new game details.
    const gameID = createdGameDetailsObj.id;
    const gameStart = createdGameDetailsObj.start;
    const gameStatus = createdGameDetailsObj.status;
    console.log("Created successfully, the game's meta data: " + gameID, gameStart, gameStatus);

    //Value storage.
    localStorage.setItem('gameID', gameID);
    localStorage.setItem('gameStart', gameStart);
    localStorage.setItem('gameStatus', gameStatus);
    console.log('Meta data stored into local storage successfully.')


    } catch (error) {
        console.error("Failed to create a game: ", error);
    }
}


async function addSelfAsParticipant(){

    const gameID = localStorage.getItem('gameID');
    
    try {
        const response = await fetch(baseURL + "/api/gameParticipant", {
            method: "POST", 
            headers:{
                'Authorization': 'Bearer ' + userToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: {
                'gameID': gameID,
                'userID': userID
            }
        })

        const addSelfAsParticipantRES = await response.json();
        console.log("add self response" + addSelfAsParticipantRES);


    } catch (error) {
        console.error("Failed to add self as participant:", error);
    }
}




async function createNewOnlineGame(){
    createNewOnlineGameOnServer();
    addSelfAsParticipant();

}




/////////////////////////////////////////////////////////////////////////




async function getInitOpponentUsername(){
    initOpponentUsername = document.getElementById('textBoxFormForUserToSearchForOpponent').value;
}

async function checkIfOpponentCanBeFound() {
   getInitOpponentUsername();
   // now make GET to server to find if a game with a specific user exists
}

async function okButtonForOpponentSearchTextbox() {
    initOpponentUsername();
    checkIfOpponentCanBeFound();
}


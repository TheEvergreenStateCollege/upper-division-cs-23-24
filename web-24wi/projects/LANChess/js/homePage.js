//homePage.js
const baseURL = 'http://localhost:5000';
var initOpponentUsername;




async function createNewOnlineGame(){

    console.log("Create new game selected");

    const ResultObj = localStorage.getItem('resultObj');
    const UserID = localStorage.getItem('userID');
    const UserToken = localStorage.getItem('userToken');
    
    try {
        const response = await fetch("http://localhost:5000/api/games", {
            method: "POST", 
            headers: {
                'Authorization': 'Bearer ' + UserToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
        },
        body: JSON.stringify({
      })
     
    })

    const createdGameDetailsObj = await response.json(); //Returned new game details.
    const createdGameID = createdGameDetailsObj.id;
    const createdGameStart = createdGameDetailsObj.start;
    const createdGameStatus = createdGameDetailsObj.status;

    localStorage.setItem('createdGameID', createdGameID);
    localStorage.setItem('createdGameStart', createdGameStart);
    localStorage.setItem('createdGameStatus', createdGameStatus);

    window.location.href = "http://localhost:5000/game"; //Redirect to game page

    
    } catch (error) {
        console.error("Failed to create a game: ", error);
    }
}

async function connectOnlineGameClientToCreatedGame(){
   
    try {
        const response = await fetch("http://localhost:5000/api/games" + createdGameID,  {
            method: "GET", 
            headers: {
                'Authorization': 'Bearer ' + UserToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
        },
        body: JSON.stringify({
      })
     
    })

    const responseForConnecting = await response.json();
    console.log(responseForConnecting);
    
    } catch (error) {
        console.error("Failed to fetch game: ", error);
    }
}

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






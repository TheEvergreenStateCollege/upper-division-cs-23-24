//homePage.js
const baseURL = 'http://localhost:5000';
var initOpponentUsername;




async function createNewOnlineGameOnServer(){

    console.log("Create new game selected");

    const ResultObj = localStorage.getItem('resultObj');
    const UserID = localStorage.getItem('userID');
    const UserToken = localStorage.getItem('userToken');
    
    try {
        const response = await fetch(baseURL + "/api/games", {
            method: "POST", 
            headers: {
                'Authorization': 'Bearer ' + UserToken,
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

    //Value storage.
    localStorage.setItem('createdGameID', createdGameID);
    localStorage.setItem('createdGameStart', createdGameStart);
    localStorage.setItem('createdGameStatus', createdGameStatus);

    } catch (error) {
        console.error("Failed to create a game: ", error);
    }
}

async function connectOnlineGameClientToCreatedGame(){
    
    const createdGameID = localStorage.getItem('createdGameID');
    const userToken = localStorage.getItem('userToken');
    
    try {
        const response = await fetch(baseURL + "/api/games" + createdGameID,  {
            method: "GET", 
            headers:{
                'Authorization': 'Bearer ' + userToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        })

        const responseForConnecting = await response.json();
        console.log(responseForConnecting);

       

    } catch (error) {
        console.error("Failed load created game:", error);
    }
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






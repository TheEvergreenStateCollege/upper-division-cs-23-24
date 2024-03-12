//homePage.js
const baseURL = 'http://localhost:5000';
var initOpponentUsername;





async function createNewOnlineGameOnServer(){

    console.log("Create new game selected");
    const userToken = localStorage.getItem('userToken');
    
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






async function connectToOnlineGame(){
    
    const gameID = localStorage.getItem('gameID');
    const userToken = localStorage.getItem('userToken');
    
    try {
        const response = await fetch(baseURL + "/api/games" + gameID,  {
            method: "GET", 
            headers:{
                'Authorization': 'Bearer ' + userToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        })

        const responseForConnecting = await response.json();
        console.log(responseForConnecting);
        console.log("connectToOnlineGame() executed");

    } catch (error) {
        console.error("Failed load created game:", error);
    }
}



async function createNewOnlineGame(){
    createNewOnlineGameOnServer();
    connectToOnlineGame();

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


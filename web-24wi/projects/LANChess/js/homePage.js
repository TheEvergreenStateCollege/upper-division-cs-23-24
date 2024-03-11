//homePage.js
const baseURL = 'http://localhost:5000';
var initOpponentUsername;


async function createNewOnlineGame(){


    console.log("create new online game button works");

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
    const gameIDObject = await response.json();
    console.log(gameIDObject);
    
    } catch (error) {
        console.error("Failed to create a game: ", error);
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






//homePage.js
const baseURL = 'http://localhost:5000';
var initOpponentUsername;



async function createNewOnlineGame(){
    
    console.log("create new online game button works");

    const userToken = localStorage.getItem('tokenObj');

    console.log(userToken);
    
    try {
        const response = await fetch("/api/games", {
            method: "POST", 
            headers: {
                'Authorization': userToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
        },

        body: JSON.stringify({
        
      })

    })
    
    } catch (error) {
        console.error("Failed to create a game: ", error);
    }
}

async function initOpponentUsername(){
    initOpponentUsername = document.getElementById('textBoxFormForUserToSearchForOpponent').value;
}

async function checkIfOpponentCanBeFound() {
    //Will query database with 'initOpponentUsername' to see if there is an active game created by an opponent user with that name. If True the player will join that game,
    //if false then it will let the user know that it either
    //1. The user cannot be found, prompt user to try name again.
    //2. If the user is found, then an active game with that user cannot be found, tell opponent to create a game, or return to the menu and create your own.
}

async function okButtonForOpponentSearchTextbox() {
    initOpponentUsername();
    checkIfOpponentCanBeFound();
}






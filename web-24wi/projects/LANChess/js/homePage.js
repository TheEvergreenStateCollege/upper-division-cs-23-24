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
   
}

async function okButtonForOpponentSearchTextbox() {
    initOpponentUsername();
    checkIfOpponentCanBeFound();
}






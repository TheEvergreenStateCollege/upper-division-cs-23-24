//homePage.js

//***GLOBAL VAR***//////////////////////////////////////////////////////////////////

var userID;
var userToken;
var gameID;
var userColor;


//***MAIN FUNCTIONS***////////////////////////////////////////////////////////////////

//Loading the values stored into local storage from the login/registration page:
async function getUserValuesFromStorage(){

    //Loading userID and userToken from local storage to the global var of userID, userToken.
    userID = localStorage.getItem('userID');
    userToken = localStorage.getItem('userToken');

    //Checking that the value is defined.
    if (userID !== null){
        console.log('userID value loaded from local storage: ' + userID);
    } else {
        console.error('userID value in local storage is undefined', error)
    }

    //Checking that the value is defined.
    if (userToken !== null){
        console.log('userToken value loaded from local storage: ' + userToken);
    } else {
        console.error('userID value in local storage is undefined', error)
    }
}

//Now to create the actual game on the server, only userToken is needed as arg.
async function createNewOnlineGameOnServer(userToken){
    
    try {
        const response = await fetch("/api/games", {
            method: "POST", 
            headers: {
                'Authorization': 'Bearer ' + userToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        body: JSON.stringify({})
        })
    
        //Storing the response as an object with the game details which will be an arg.
        const gameInfoObj = await response.json();

        //Log test for gameInfoObj:
        console.log("gameInfoObj:" + JSON.stringify(gameInfoObj));

        return gameInfoObj;

    } catch(error) {
        console.error("Failed to create new online game on the server: ", error)
    }
}

//Setting global gameID to the value of the created games ID.
async function storeCreatedGameInfo(gameInfoObject){

    //Only gameID is needed for the client.
    gameID = gameInfoObject.data.id;

    //Log test for gameID:
    console.log(gameID);

    //Then storage the gameID value into local storage just in case.
    localStorage.setItem('gameID', gameID);
}


//Adding the user as a participant a given game.
async function addUserAsParticipant(userID, userToken, gameID){
   
    try {
        const response = await fetch("/api/gameParticipant", {
            method: "POST", 
            headers:{
                'Authorization': 'Bearer ' + userToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
            body: JSON.stringify( {
                'gameId': gameID,
                'userId': userID
            })
        })

        //Storing the response as an object with the participants details  which will be an arg.
        const participantResponseObj = await response.json();

        //Log test for participantResponseObj:
        console.log(participantResponseObj)
        return participantResponseObj

    } catch (error) {
        console.error("Failed to add self as participant:", error);
    }
}

//Store the value of the textbox on the homepage into the global gameID.
async function findRoomByIDForm(){
    try {
        gameID = document.getElementById('textBoxFormForUserToEnterRoomID').value;
    } catch (error) {
        console.error("Value of gameID find form is invalid", error);
    }
}

//Sets the global var of userColor to white.
async function userColorIsWhite(){
    userColor = 'white';
    console.log("You play white!")
}

//Sets the global var of userColor to black.
async function userColorIsBlack(){
    userColor = 'black';
    console.log("You play black!")
}

//Stores all global var that will be needed on the next page
//For the onlineGamePage.js this needs the gameID and userColor.
async function storeWhatsNeeded(){
    localStorage.setItem('gameID', gameID);
    localStorage.setItem('userColor', userColor);

    //Log what values were stored as:
    console.log("gameID was stored into local storage as the value: " + gameID)
    console.log("userColor was stored into local storage as the value: " + userColor)
}


//***CALL FUNCTIONS***////////////////////////////////////////////////////////////////

//This creates a new online game and stores what's needed for the client.
async function createNewOnlineGame(){
    try {
        //Set user values globally from local storage.
        await getUserValuesFromStorage(); 
        //Set gameInfoObj to the result of createNewOnlineGameServer for the arg of gameID.
        const gameInfoObj = await createNewOnlineGameOnServer(userToken);
        //Store the created game info into local storage.
        await storeCreatedGameInfo(gameInfoObj);
        //Add the user as a participant to the game.
        await addUserAsParticipant(userID, userToken, gameID);
        //User plays white for creating match, stored into local for client.
        await userColorIsWhite();
        //Store the gameID and userColor values to local storage for the client.
        await storeWhatsNeeded();
        //If successful redirect user to game page.
        window.location.href = "/game";
    } catch (error) {
        console.error("Failed to create new online game", error);
    }
}

async function joinOnlineGame(){
    try {
        //Set user values globally from local storage.
        await getUserValuesFromStorage();
        //Get value of ID room form
        await findRoomByIDForm();
        //Add the user as a participant to the game.
        await addUserAsParticipant(userID, userToken, gameID);
        //User plays black for joining, stored into local for client.
        await userColorIsBlack();
        //Store the gameID and userColor values to local storage for the client.
        await storeWhatsNeeded();
        //If successful redirect user to game page.
        window.location.href = "/game";
    } catch(error){
        console.error("Failed to join online game", error);
    }
}


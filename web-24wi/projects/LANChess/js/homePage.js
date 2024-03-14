//homePage.js
var userID;
var userToken;


async function getUserValuesFromStorage(){

    //Importing userID, userToken values from local storage.
    userID = localStorage.getItem('userID');
    userToken = localStorage.getItem('userToken');

    if (typeof userID !== 'undefined' && typeof userToken !== 'undefined') {
    //Console testing to ensure values are defined.
    console.log("--getUserValuesFromStorage-------------------");
    console.log("The function getUserValuesFromStorage imported the following values");
    console.log("userID: " + userID);
    console.log("userToken: " + userToken);
    console.log("---------------------------------------------");
    console.log();
    
    } else {
        console.error("userID or userToken not found in localStorage: ", error)    
    }
}

async function createNewOnlineGameOnServer() {

    //Console testing to ensure values are defined.
    console.log("--createNewOnlineGameOnServer----------------");
    console.log("The following variables are being called from global");
    console.log("userID: " + userID);
    console.log("userToken: " + userToken);
    console.log("---------------------------------------------");

    try {
        const apiResponse = await fetch("/api/games", {
            method: "POST", 
            headers: {
                'Authorization': 'Bearer ' + userToken,
                'Accept': 'application/json',
                'Content-Type': 'application/json',
            },
        body: JSON.stringify({})
        })
    
        const gameInfoObject = await apiResponse.json();
        return gameInfoObject;

    } catch(error) {
        console.error("Failed to create new game on the server: ", error);
    }

}

async function storeCreatedGameInfo(gameInfoObject){

    //Console testing to ensure passed values  are defined.
    console.log("--storeCreatedGameInfo()---------------------");
    console.log("createNewOnlineGameOnServer() passed the following values");
    console.log("gameInfoObj: " + gameInfoObj);
    console.log("gameInfoObj as JSON: " + JSON.stringify(gameInfoObj));
    
    const gameID = gameInfoObject.data.id;
    const gameStart = gameInfoObject.data.start;
    const gameStatus = gameInfoObject.data.status;

    //Console testing to ensure creatGameIfnoObj values are defined.
    console.log("gameID value to be stored: " + gameID);
    console.log("gameStart value to be stored: " + gameStart);
    console.log("gameStatus value to be stored: " + gameStatus);

    localStorage.setItem('gameID', gameID);
    localStorage.setItem('gameStart', gameID);
    localStorage.setItem('gameID', gameID);

    console.log("---------------------------------------------");
}

async function addSelfAsParticipant(){

    //Retrieving gameID from localStorage.
    const gameID = localStorage.getItem('gameID');
    
    //Console testing
    console.log("--addSelfAsParticipant()---------------------");
    console.log("The following variables are being called from global");
    console.log("userID: " + userID);
    console.log("userToken: " + userToken);
    

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

        const addSelfAsParticipantRESObj = await response.json();
        console.log("addSelfAsParticipantRESObjL:" + addSelfAsParticipantRESObj)
        return addSelfAsParticipantRESObj;

    } catch (error) {
        console.error("Failed to add self as participant:", error);
    }

    console.log("The following values have been POSTED to the /api/gameParticipant route");
    console.log("gameID: " + gameID);
    console.log("userID: " + userID);
    console.log("---------------------------------------------");
}

async function storeParticipantID(addSelfAsParticipantRESObj){
    //Console testing to ensure passed values  are defined.
    console.log("--storeCreatedGameInfo()---------------------");
    console.log("addSelfAsParticipant() passed the following values");
    console.log("addSelfAsParticipantRESObj: " + addSelfAsParticipantRESObj);
    console.log("JSON addSelfAsPariticpantRESObj: " + JSON.stringify(addSelfAsParticipantRESObj));

    const participantID = addSelfAsParticipantRESObj.data.id;
    console.log(participantID);
    localStorage.setItem('participantID', participantID);
}

async function valueOfFormToFindRoomByID(){
    try { 
    const gameID = document.getElementById('textBoxFormForUserToEnterRoomID').value;
    localStorage.setItem('gameID', gameID);
    return gameID;

    } catch (error) {
    console.error("Failed to retrieve gameID from text entry box: ", error);
    }
}






async function storeUserColorAsWhite(){
    const userColor = 'white';
    localStorage.setItem('userColor', userColor);
    console.log("User color to play stored as white in localStorage");
}

async function storeUserColorAsBlack(){
    const userColor = 'black';
    localStorage.setItem('userColor', userColor);
    console.log("User color to play stored as white in localStorage");
}


//Call functions

async function createNewOnlineGame(){
   try {
        await getUserValuesFromStorage();
        gameInfoObj = await createNewOnlineGameOnServer();
        await storeCreatedGameInfo(gameInfoObj);
        const addSelfAsParticipantRESObj = await addSelfAsParticipant();
        await storeParticipantID(addSelfAsParticipantRESObj);
        await storeUserColorAsWhite();
        window.location.href = "/game"; //Redirect to home page.
        
   }
   catch (error){
    console.error("createNewOnlineGame() failed", error);
   }
}

async function joinOnlineGame(){
    try {
        await getUserValuesFromStorage();
        localStorage.setItem('gameID',valueOfFormToFindRoomByID());
        const addSelfAsParticipantRESObj = await addSelfAsParticipant();
        await storeParticipantID(addSelfAsParticipantRESObj);
        await storeUserColorAsBlack();
        window.location.href = "/game"; //Redirect to home page.
    }
    catch(error){
        console.error("joinOnlineGame() failed", error);
    }
}











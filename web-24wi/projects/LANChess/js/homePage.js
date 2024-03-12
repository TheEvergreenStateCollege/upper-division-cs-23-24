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
    
        const createdGameInfoObj = await apiResponse.json();
        return createdGameInfoObj;

    } catch(error) {
        console.error("Failed to create new game on the server: ", error);
    }

}

async function storeCreatedGameInfo(createdGameInfoObj){

    //Console testing to ensure passed values  are defined.
    console.log("--storeCreatedGameInfo()---------------------");
    console.log("createNewOnlineGameOnServer() passed the following values");
    console.log("createdGameInfoObj: " + createdGameInfoObj);
    console.log("createGameInfoObjJSON: " + JSON.stringify(createdGameInfoObj));
    
    const gameID = createdGameInfoObj.data.id;
    const gameStart = createdGameInfoObj.data.start;
    const gameStatus = createdGameInfoObj.data.status;

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
    console.log("addSelfAsParticipantRESObj: " + createdGameInfoObj);
    console.log("JSON addSelfAsPariticpantRESObj: " + JSON.stringify(createdGameInfoObj));

    const participantID = addSelfAsParticipantRESObj.data.id;
    console.log(participantID);
}




async function createNewOnlineGame(){
   try {
    await getUserValuesFromStorage();
    createdGameInfoObj = await createNewOnlineGameOnServer();
    await storeCreatedGameInfo(createdGameInfoObj);
    addSelfAsParticipantRESObj = await addSelfAsParticipant();
    await storeParticipantID(addSelfAsParticipantRESObj);
   }
   catch (error){
    console.error("createNewOnlineGame() failed", error);
   }
}






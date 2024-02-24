//homePage.js
const baseURL = 'http://localhost:5000'
var opponentUsername = '';


function storeFormValueIntoOpponentUsername(callback) {
    opponentUsername = document.getElementById('textBoxFormForUserToCreateNewUsername').value;
    console.log('storeFormValueIntoOpponentUsername function ran'); //Test case of method run success to console.
    callback(); //Callback for order of operations with loadGamePage().
}

//This function loads the replacement page.
function loadGamePage() {
console.log(username); //Test case of username to console.
window.location.replace("gamePage.html"); //Replacing current page with gamePage.html
}


//loginPage.js
var username;
const baseURL = 'http://localhost:5000';
var isUsernameEntryIsValid;

//This function stores the value of the login page's "username" textbox form into the initialized "var username" and then uses a callback to ensure order of operations with the next task of calling loadHomePage().
function initUsernameEntryBuffer(callback) {
            username = document.getElementById('textBoxFormForUserToCreateNewUsername').value;
            
            callback(); //Callback for order of operations with loadHomePage().
}


async function checkIfUsernameIsValid() {
    if (username.length <= 10) {
        console.log('valid username');
    } else {
        console.log('invalid username');
    }
}






const usernamePOST = {
    username: username,


    
}





////////////////////////////////////
//This function loads the replace
function loadHomePage() {
    console.log(username); //Test case of username to console.
    window.location.replace("homePage.html"); //Replacing current page with homePage.html
}






 





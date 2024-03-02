//loginPage.js

var username = ''; //Initializing the empty "username" string to store the username in, see line 4.

//This function stores the value of the login page's "username" textbox form into the initialized "var username" and then uses a callback to ensure order of operations with the next task of calling loadHomePage().
function storeFormValueIntoUsername(callback) {
            username = document.getElementById('textBoxFormForUserToCreateNewUsername').value;
            console.log('storeFormValueIntoUsername function ran'); //Test case of method run success to console.
            callback(); //Callback for order of operations with loadHomePage().
}

//This function loads the replace
function loadHomePage() {
    console.log(username); //Test case of username to console.
    window.location.replace("homePage.html"); //Replacing current page with homePage.html
}






 





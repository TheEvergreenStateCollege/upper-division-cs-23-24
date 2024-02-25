//loginPage.js
const baseURL = 'http://localhost:5000';
var initUsername;
var initPassword;
var checkedInitUsername;
var checkedInitPassword;


//This function stores the value of the login page's "username" textbox form into the initial initUsername
function getInitUsername(){
    initUsername = document.getElementById('textBoxFormForUserToCreateNewUsername').value;
}

function getInitPassword(){
    initPassword = document.getElementById('textBoxFormForUserToCreateNewPassword').value;
}

//This function checks if the initUsername is valid (ie. less than 10 charachters and whatever we specify)
function checkIfInitUsernameIsValid(){
    if(initUsername.length <= 10 && initUsername.length !=0){
        console.log('initUsername length is valid');
        checkedInitUsername = initUsername; 
        //Make POST to server with checkedUsername.
        //Make GET to server to load homePage for user.
    } else {
        console.log('Username is invalid, must be between 2-10 charachters long, please try again..');
        window.alert('Username is invalid, must be between 2-10 charachters long, please try again..');
        location.reload();
    }
}

function checkIfInitPasswordIsValid(){
    if(initPassword.length <= 10 && initPassword.length != 0){
        console.log('initPassword length is valid');
        checkedInitPassword = initPassword; 
        //Make POST to server with checkedUsername.
        //Make GET to server to load homePage for user.
    } else {
        console.log('Password is invalid, must be between 2-10 charachters long, please try again.');
        window.alert('Password is invalid, must be between 2-10 charachters long, please try again..');
        location.reload();
    }
}

function initialCheckUsernameAndPasswordTextboxes(){
    getInitUsername();
    checkIfInitUsernameIsValid();
    console.log('The final value of checkedUsername that was POSTED to the server was ' + '[' + checkedInitUsername + ']');
    getInitPassword();
    checkIfInitPasswordIsValid();
    console.log('The final value of checkedPassword that was POSTED to the server was ' + '[' + checkedInitPassword + ']');
}





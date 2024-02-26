//loginPage.js
const baseURL = 'http://localhost:5000';
var initUsername;
var initUsernameLen;
var initPassword;
var initPasswordLen;
var checkedInitUsername;
var checkedInitPassword;


//This function stores the value of the login page's "username" textbox form into the initial initUsername
function getInitUsername(){
    initUsername = document.getElementById('textBoxFormForUserToCreateNewUsername').value;
    initUsernameLen = initUsername.length;
}

function getInitPassword(){
    initPassword = document.getElementById('textBoxFormForUserToCreateNewPassword').value;
    initPasswordLen = initPassword.length;
}

//This function checks if the initUsername is valid (ie. less than 10 charachters and whatever we specify)
function checkIfInitUsernameIsValid(){
    if(initUsernameLen >=2 && initUsernameLen <= 10){
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
    if(initPasswordLen >=2 && initPasswordLen <= 10){
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

async function initialCheckUsernameAndPasswordTextboxes(){
    getInitUsername();
    checkIfInitUsernameIsValid();
    console.log('The final value of checkedUsername that was POSTED to the server was ' + '[' + checkedInitUsername + ']');
    getInitPassword();
    checkIfInitPasswordIsValid();
    console.log('The final value of checkedPassword that was POSTED to the server was ' + '[' + checkedInitPassword + ']');
}

async function loginPageRegisterRequest(){
    initialCheckUsernameAndPasswordTextboxes();
    console.log('REGISTER REQ USER' + '' + '[' + checkedInitUsername + ']');
    console.log('REGISTER REQ PASS' + '' + '[' + checkedInitPassword + ']');
    



}




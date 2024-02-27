//userRegisterAndLoginPage.js
const baseURL = 'http://localhost:5000';
var initUsernameValueEntered;
var initUsernameValueEnteredLen;
var initPasswordValueEntered;
var initPasswordValueEnteredLen;
var initUsernameForRegistrationWithValidSyntax;
var initPasswordForRegistrationWithValidSyntax;


//This function stores the value of the login page's "username" textbox form into the initial initUsername
function getInitUsernameValueEntered(){
    initUsernameValueEntered = document.getElementById('textBoxFormForUserToCreateNewUsername').value;
    initUsernameValueEnteredLen = initUsernameValueEntered.length;
}

function getInitPasswordValueEntered(){
    initPasswordValueEntered = document.getElementById('textBoxFormForUserToCreateNewPassword').value;
    initPasswordValueEnteredLen = initPasswordValueEntered.length;
    
}

//This function checks if the initUsername is valid (ie. less than 10 charachters and whatever we specify)
function checkIfInitUsernameSyntaxIsValidForRegistration(){
    if(initUsernameValueEnteredLen >=2 && initUsernameValueEnteredLen <= 10){
        console.log('Initial username entered has valid syntax for registration');
        initUsernameForRegistrationWithValidSyntax = initUsernameValueEntered; 
        //Make POST to server with checkedUsername.
        //Make GET to server to load homePage for user.
    } else {
        console.log('Username is invalid, must be between 2-10 charachters long, please try again..');
        window.alert('Username is invalid, must be between 2-10 charachters long, please try again..');
        location.reload();
    }
}

function checkIfInitPasswordSyntaxIsValidForRegistration(){
    if(initPasswordValueEnteredLen >=2 && initPasswordValueEnteredLen <= 10){
        console.log('Initial password entered has valid syntax for registration');
        initPasswordForRegistrationWithValidSyntax = initPasswordValueEntered;
        //Make POST to server with checkedUsername.
        //Make GET to server to load homePage for user.
    } else {
        console.log('Password is invalid, must be between 2-10 charachters long, please try again.');
        window.alert('Password is invalid, must be between 2-10 charachters long, please try again..');
        location.reload();
    }
}

async function initialCheckIfUsernameAndPasswordSyntaxIsValidForRegistration() {
    getInitUsernameValueEntered();
    checkIfInitUsernameSyntaxIsValidForRegistration()
    console.log('The username entered which passed check for valid syntax was' + '[' + initUsernameForRegistrationWithValidSyntax + ']');
    
    getInitPasswordValueEntered();
    checkIfInitPasswordSyntaxIsValidForRegistration();
    console.log('The password entered which passed check for valid syntax was ' + '[' + initPasswordForRegistrationWithValidSyntax+ ']');
}

async function registerRequest(){
    initialCheckIfUsernameAndPasswordSyntaxIsValidForRegistration();
    console.log('REGISTER REQ PASS ' + '[' + initUsernameForRegistrationWithValidSyntax + ']')
    console.log('REGISTER REQ PASS ' + '[' + initPasswordForRegistrationWithValidSyntax + ']');
}




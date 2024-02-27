//userRegisterAndLoginPage.js
const baseURL = 'http://localhost:5000';
var initUsernameValueEntered;
var initUsernameValueEnteredLen;
var initPasswordValueEntered;
var initPasswordValueEnteredLen;
var initUsernameForRegistrationWithValidSyntax;
var initPasswordForRegistrationWithValidSyntax;
var enteredUsernameAndPasswordSyntaxIsValidForRegistration;

var invalidUsernameAlertMessage = 'Username is invalid, must be between 2-10 charachters long, please try again.';
var invalidPasswordAlertMessage = 'Password is invalid, must be between 2-10 charachters long, please try again.';



//This function stores the value of the login page's "username" textbox form into the initial initUsername
function getInitUsernameValueEntered(){
    initUsernameValueEntered = document.getElementById('textBoxFormForUserToCreateNewUsername').value;
    initUsernameValueEnteredLen = initUsernameValueEntered.length;
}

function getInitPasswordValueEntered(){
    initPasswordValueEntered = document.getElementById('textBoxFormForUserToCreateNewPassword').value;
    initPasswordValueEnteredLen = initPasswordValueEntered.length;
    
}

//This function checks if the initUsername is valid (ie. between 2 and 10 char, or whatever we specify)
function checkIfInitUsernameSyntaxIsValidForRegistration(){
    if(initUsernameValueEnteredLen >=2 && initUsernameValueEnteredLen <= 10){
        //console.log('Initial username entered has valid syntax for registration');
        initUsernameForRegistrationWithValidSyntax = initUsernameValueEntered; 
    
    } else {
        //console.log('Username is invalid, must be between 2-10 charachters long, please try again..');
        window.alert(invalidUsernameAlertMessage);
        location.reload();
    }
}

function checkIfInitPasswordSyntaxIsValidForRegistration(){
    if(initPasswordValueEnteredLen >=2 && initPasswordValueEnteredLen <= 10){
        //console.log('Initial password entered has valid syntax for registration');
        initPasswordForRegistrationWithValidSyntax = initPasswordValueEntered;
        
    } else {
        window.alert(invalidPasswordAlertMessage);
        location.reload();
    }
}

async function initialCheckIfUsernameAndPasswordSyntaxIsValidForRegistration() {
    getInitUsernameValueEntered();
    checkIfInitUsernameSyntaxIsValidForRegistration()
    //console.log('The username entered which passed check for valid syntax was' + '[' + initUsernameForRegistrationWithValidSyntax + ']');
    
    getInitPasswordValueEntered();
    checkIfInitPasswordSyntaxIsValidForRegistration();

    //console.log('The password entered which passed check for valid syntax was ' + '[' + initPasswordForRegistrationWithValidSyntax+ ']');

    var enteredUsernameAndPasswordSyntaxIsValidForRegistration = true;



}


async function registerRequest(){
    initialCheckIfUsernameAndPasswordSyntaxIsValidForRegistration();

    if (initPasswordForRegistrationWithValidSyntax = true) {
        
        console.log('REGISTER REQ PASS ' + '[' + initUsernameForRegistrationWithValidSyntax + ']')
        console.log('REGISTER REQ PASS ' + '[' + initPasswordForRegistrationWithValidSyntax + ']');
    }

  
}




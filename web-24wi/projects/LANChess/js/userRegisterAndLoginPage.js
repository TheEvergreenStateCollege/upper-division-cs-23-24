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




function getInitUsernameValueEntered(){ //This function stores the value of the login page's "username" textbox form into the initial initUsername
    initUsernameValueEntered = document.getElementById('textBoxFormForUserToCreateNewUsername').value;
    initUsernameValueEnteredLen = initUsernameValueEntered.length;
}

function getInitPasswordValueEntered(){
    initPasswordValueEntered = document.getElementById('textBoxFormForUserToCreateNewPassword').value;
    initPasswordValueEnteredLen = initPasswordValueEntered.length;
    
}

function checkIfInitUsernameSyntaxIsValidForRegistration(){ //This function checks if the initUsername is valid (ie. between 2 and 10 char, or whatever we specify)
    if(initUsernameValueEnteredLen >=2 && initUsernameValueEnteredLen <= 10){
        
        initUsernameForRegistrationWithValidSyntax = initUsernameValueEntered; 
    
    } else {
        
        window.alert(invalidUsernameAlertMessage);
        location.reload();
    }
}

function checkIfInitPasswordSyntaxIsValidForRegistration(){
    if(initPasswordValueEnteredLen >=2 && initPasswordValueEnteredLen <= 10){
       
        initPasswordForRegistrationWithValidSyntax = initPasswordValueEntered;
        
    } else {
        window.alert(invalidPasswordAlertMessage);
        location.reload();
    }
}

async function initialCheckIfUsernameAndPasswordSyntaxIsValidForRegistration() {
    getInitUsernameValueEntered();
    checkIfInitUsernameSyntaxIsValidForRegistration()
   
    
    getInitPasswordValueEntered();
    checkIfInitPasswordSyntaxIsValidForRegistration();

    

    var enteredUsernameAndPasswordSyntaxIsValidForRegistration = true;
}

async function registerRequest(){
    initialCheckIfUsernameAndPasswordSyntaxIsValidForRegistration();

    if (initPasswordForRegistrationWithValidSyntax = true) {
        
        console.log('REGISTER REQ PASS ' + '[' + initUsernameForRegistrationWithValidSyntax + ']');
        console.log('REGISTER REQ PASS ' + '[' + initPasswordForRegistrationWithValidSyntax + ']');
    } 
}




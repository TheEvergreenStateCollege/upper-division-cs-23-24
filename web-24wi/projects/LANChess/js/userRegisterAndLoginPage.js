//userRegisterAndLoginPage.js
const baseURL = 'http://localhost:5000';
var usernameValue;
var usernameValueLen;
var passwordValue;
var passwordValueLen;

async function credentials() { //This function stores the value of the login page's "username" textbox form into the initial initUsername
    usernameValue = document.getElementById('usernameForm').value;
    passwordValue = document.getElementById('passwordForm').value;
    usernameValueLen = usernameValue.length;
    passwordValueLen = passwordValue.length;
}

async function credentialsSyntaxCheckForRegistration(){
    if ((usernameValueLen && passwordValueLen >=2) && (usernameValueLen && passwordValueLen <=10)) {
        usernameValue = usernameValue;
        passwordValue = passwordValue;
        console.log("success");
    } else {
        window.alert('Invalid username or password');
    }
}

async function registerNewUser(){
    credentials();
    credentialsSyntaxCheckForRegistration();
}

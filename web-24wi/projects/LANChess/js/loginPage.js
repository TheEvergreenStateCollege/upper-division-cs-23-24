//loginPage.js
const baseURL = 'http://localhost:5000';
var initUsername
var checkedUsername;

//This function stores the value of the login page's "username" textbox form into the initial initUsername
function getInitUsername(){
    initUsername = document.getElementById('textBoxFormForUserToCreateNewUsername').value;
}

//This function checks if the initUsername is valid (ie. less than 10 charachters and whatever we specify)
function checkIfUsernameIsValid(){
    if(initUsername.length <= 10){
        console.log('username length is valid');
        checkedUsername = initUsername; 
        //Make POST to server with checkedUsername.
        //Make GET to server to load homePage for user.
    } else {
        console.log('Username length is invalid, please try again');
        window.alert("Username is invalid, must be 10 charachters or less, please try again.");
        location.reload();
    }
}

function okButton(){
    getInitUsername();
    checkIfUsernameIsValid();
    console.log(checkedUsername);
}



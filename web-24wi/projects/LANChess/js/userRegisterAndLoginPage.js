//userRegisterAndLoginPage.js
const baseURL = 'http://localhost:5000';
var usernameValue;
var usernameValueLen;
var passwordValue;
var passwordValueLen;

async function setCookie(name, value, daysToLive) {
    const date = new Date();
    date.setTime(date.getTime() + (daysToLive * 24 * 60 * 60 * 1000))
    let expires = "expires=" + date.toUTCString();
    document.cookie = '${name}=${value}; ${expires}; path=/';
}

async function credentials() { //This function stores the value of the login page's "username" textbox form into the initial initUsername
    usernameValue = document.getElementById('textBoxFormForUserToCreateNewUsername').value;
    passwordValue = document.getElementById('textBoxFormForUserToCreateNewPassword').value;
    usernameValueLen = usernameValue.length;
    passwordValueLen = passwordValue.length;
}

async function credentialsSyntaxCheckForRegistration(){
    if ((usernameValueLen && passwordValueLen >=2) && (usernameValueLen && passwordValueLen <=10)) {
        usernameValue = usernameValue;
        passwordValue = passwordValue;
        console.log("Username:" + "[" + usernameValue + "]");
        console.log("Password:" + "[" + passwordValue + "]");
    } else {
        window.alert('Invalid username or password');
    }
}

async function registerNewUser(){
    credentials();
    credentialsSyntaxCheckForRegistration();
    

        try {
            const response = await fetch("/register", {
                method: "POST", 
                headers: {
                    'Accept': 'application/json',
                    "Content-Type": "application/json",
            },

            body: JSON.stringify({
                username: usernameValue,
                password: passwordValue
          })})

            const result = await response.json();
            console.log("Success:", result);

            document.cookie = result
            console.log("cookie value: " + document.cookie);


        } catch (error) {
            console.error("Error:", error);
        }
      }

async function loginUser(){
    credentials();

        try {
            const response = await fetch("/login", {
                method: "POST", 
                headers: {
                    
                    'Accept': 'application/json',
                    "Content-Type": "application/json",
                },
    
                body: JSON.stringify({
                    username: usernameValue,
                    password: passwordValue
              })})
    
                
                const resultObj = await response.json();
                const userID = resultObj.id;
                const userToken = resultObj.token;
                console.log("Success: ");
                console.log(userID);
                console.log(userToken);

                setCookie("userID", userID, 1);
                console.log(document.cookie);



    
            } catch (error) {
                console.error("Error:", error);
            }
          }
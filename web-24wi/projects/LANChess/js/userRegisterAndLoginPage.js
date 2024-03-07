//userRegisterAndLoginPage.js
const baseURL = 'http://localhost:5000';
var usernameValue;
var usernameValueLen;
var passwordValue;
var passwordValueLen;

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
    
                
                const result = await response.json();
                console.log("Success:", result);
    
                document.cookie = result
                const cookieString = document.cookie

                console.log("cookie value: " + document.cookie);
    
            } catch (error) {
                console.error("Error:", error);
            }
          }
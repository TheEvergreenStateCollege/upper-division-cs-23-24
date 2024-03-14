//userRegisterAndLoginPage.js

//***GLOBAL VAR***//////////////////////////////////////////////////////////////////////

var usernameValue;
var usernameValueLen;
var passwordValue;
var passwordValueLen;


//***CALL FUNCTIONS***///////////////////////////////////////////////////////////

//Storing the value of the login form into usernameValue, also finding len.
async function credentials() { //This function stores the value of the login page's "username" textbox form into the initial initUsername
    usernameValue = document.getElementById('textBoxFormForUserToCreateNewUsername').value;
    passwordValue = document.getElementById('textBoxFormForUserToCreateNewPassword').value;
    usernameValueLen = usernameValue.length;
    passwordValueLen = passwordValue.length;
}

//Check if the syntax for login are valid:
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

//Register new user if valid syntax:
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

            const userRegisterResponseObj = await response.json();
            const userID = userRegisterResponseObj.id;
            const userToken = userRegisterResponseObj.token;

            localStorage.setItem('userID', userID);
            localStorage.setItem('userToken', userToken);
               
            console.log("Credentials stored to local storage successfully");

            //After successful login redirect them to the home page.
            window.location.href = "/home";

        } catch (error) {
            console.error("Failed registration error:", error);
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
    
                const userLoginResponseObj = await response.json();
                const userID = userLoginResponseObj.id;
                const userToken = userLoginResponseObj.token;

                localStorage.setItem('userID', userID);
                localStorage.setItem('userToken', userToken);

                //After successful login redirect them to the home page.
                window.location.href = "/home"; 
                
        } catch (error) {
            console.error("Failed login error:", error);
        }
}
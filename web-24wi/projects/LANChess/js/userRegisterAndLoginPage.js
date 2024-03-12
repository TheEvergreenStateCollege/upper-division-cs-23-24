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
            const response = await fetch(baseURL + "/register", {
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

            window.location.href = "/home"; //Redirect to home page.

        } catch (error) {
            console.error("Failed registration error:", error);
        }
      }

async function loginUser(){
    credentials();

        try {
            const response = await fetch(baseURL + "/login", {
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

                //   console.log("Credentials stored to local storage successfully");
           
                window.location.href = "/home"; //Redirect to home page.
                
        } catch (error) {
            console.error("Failed login error:", error);
        }
}
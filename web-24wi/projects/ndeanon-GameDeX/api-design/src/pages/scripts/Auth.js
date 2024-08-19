import API from "./API.js";
import Router from "./Router.js";

const Auth = {
    isLoggedIn: false,
    account: null,
    postLogin:(response,user) => {
        if(response.ok) {
            Auth.isLoggedIn = true;
            Auth.account = user;
            Auth.updateStatus();
            Router.go("/account");
        } else{
            alert(response.message);
        }
    },
    register:async (event) => {
        event.preventDefault();
        const user = {
            name: document.getElementById("register-name").value,
            email: document.getElementById("register-email").value,
            password: document.getElementById("register-password").value,
        }
        
        try {
            const response = await API.register(user);
            console.log(response);
            Auth.postLogin(response, {
                name:user.name,
                email: user.email
            });
            //Successfully handled registration
        } catch(error) {
            console.error('Registration failed:', error);
            //Handle Registration error
        }
        
    },

    login:async (event) => {
        event.preventDefault();
        const credentials = {
            email: document.getElementById("login-email").value,
            password: document.getElementById("login-password").value
        }
        try{
        const response = await API.login(credentials);
        Auth.postLogin(response, {
            ...credentials,
            name: response.name
        });
        console.log(response);
        //Handled Login response
        } catch(error) {
            console.error('Login failed', error);
            //Failed login
        }
    },
    updateStatus() {
        if (Auth.isLoggedIn && Auth.account) {
            document.querySelectorAll(".logged_out").forEach(
                e => e.style.display = "none"
            );
            document.querySelectorAll(".logged_in").forEach(
                e => e.style.display = "block"
            );
            document.querySelectorAll(".account_name").forEach(
                e => e.innerHTML = Auth.account.name
            );
            document.querySelectorAll(".account_username").forEach(
                e => e.innerHTML = Auth.account.email
            );

        } else {
            document.querySelectorAll(".logged_out").forEach(
                e => e.style.display = "block"
            );
            document.querySelectorAll(".logged_in").forEach(
                e => e.style.display = "none"
            );

        }
    },    
    init: () => {
        //Adding the event listener since ChatGPT thinks thats the problem but I already added it -_- whatever.
        const registerForm = document.getElementById("register-form");
        if(registerForm){
            registerForm.addEventListener("submit", Auth.register);
        }

        const loginForm = document.getElementById("login-form");
        if(loginForm){
            loginForm.addEventListener("submit", Auth.login); 
        }
        
    }
}
Auth.updateStatus();

export default Auth;

// make it a global object
window.Auth = Auth;
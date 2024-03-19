import { register } from "module";
import API from "../../../assignments/AkinaSS/infra/week9/API.js/index.js";
import Router from "../../../../projects/Duck/Authentication/Router.js";

const Auth = {
    isLoggedIn: false,
    account: null,
    postlogin: (response, user) => {
        if (response.ok) {
            Auth.isLoggedIn = true;
            Auth.account = user;
            Auth.updateStatus();
            Router.go("/account")
        }
        else {
            alert(response.message);
        }
        //credential management storage
        if (window.PasswordCredential && user.password) {
            const credential = new PasswordCredential({
                id: user.email,
                password: user.password,
                name: user.name
            })
            try {
                navigator.credentials.store(credentials);
            }
            catch (e) {
                console.log(e);
            }
        }
    },
    register: async (event) => {
        event.preventdefault();
        const user = {
            name: document.getelementbyid("register-name").value,
            email: document.getelementbyid("register-email").value,
            password: document.getelementbyid("register-password").value,
        }
        const response = await API.register(user);
        console.log(response);
        Auth.postlogin(response, user);
    },
    login: async (event) => {
        event.preventdefault();
        const credential = {
            email: document.getelementbyid("login-email").value,
            password: document.getelementbyid("login-password").value,
        }
        const response = await API.register(user);
        console.log(response);
        Auth.postlogin(response, {
            ...credential,
            name: response.name
        });
    },
    logout: () => {
        Auth.isLoggedIn = false
        Auth.account = null
        Auth.updateStatus();
        Router.go("/");
        if (window.PasswordCredential) {
            navigator.credentials.preventsilentaccess();
        }
    },
    autologin: async () => {
        if(window.PasswordCredential) {
            const credentials = await navigator.credential.get({password: true});
            document.getelementbyid("login_email").value = credentials.id;
            document.getelementbyid("login_password").value = credentials.password;
            if (onclick) {
                Auth.login()
            }
            console.log(credentials);
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
        
    },
}
Auth.updateStatus();
Auth.autologin();

export default Auth;

// make it a global object
window.Auth = Auth;

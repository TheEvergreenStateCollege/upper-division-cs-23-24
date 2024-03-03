import { Router } from "react-router-dom";

const API = {
    endpoint: "/auth", 
    login: async (event) => {
        if (event) event.preventDefault();
        const user = {
            username: document.getElementById("uname").value,
            password: document.getElementById("pword").value
        };
        const response = await API.login(user);
        Auth.postLogin(response, {
            ...user,
            name: response.name

        });
    },
    register: async (event) => {
        event.preventDefault();
        const user ={
            name: document.getElementById
        }
        const response = await API.register(user);
        Auth.postLogin(response, user)
    },

    postLogin: (response, user) => {
        if (response.ok) {
            Auth.isLoggedIn = true;
            Auth.account = user;
            Auth.updateStatus();

            Router.go("/account")
        }
        else{
            alert(response.message)
        }
    },

    logout: () => {
        Auth.isLoggedIn = false;
        Auth.account = null;
        Auth.updateStatus();
        Router.go("/");
    }
    

}

export default API;
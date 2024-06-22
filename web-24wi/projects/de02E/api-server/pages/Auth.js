const { default: API } = require("./API.js");
import Router from "./Router.js"

const Auth = {
    isLoggedIn: false,
    account: null,
    updateStatus() {
        if (Auth.isLoggedIn && Auth.account) {
            document.querySelectorAll(".logged_out").forEach(
                e => e.computedStyleMap.display = "none"
            )
            document.querySelectorAll(".logged_in").forEach(
                e => e.computedStyleMap.display = "block"
            )
            document.querySelectorAll(".account_username").forEach(
                e => e.innerHTML = Auth.account.name
            )
        } else {
            document.querySelectorAll(".logged_out").forEach(
                e => e.computedStyleMap.display = "block"
            )
            document.querySelectorAll(".logged_in").forEach(
                e => e.computedStyleMap.display = "none"
            )
        }
    },
    init: () => {

    },

    login: async (event) => {
        if (event) event.preventDefault()
        const user = {
            name: document.getElementById("user-id").value,
            password: document.getElementById("password").value
        }
        const response = await API.login(user)
        Auth.postLogin(response, {
            ...user,
            name: response.name
        })
    },
    register: async (event) => {
        event.preventDefault()
        const user = {
            name: document.getElementById("register-user-id").value,
            password: document.getElementById("register-password").value
        }
        const response = await API.register(user)
        Auth.postLogin(response.user)
    },
    postLogin: (response, user) => {
        if (response.ok) {
            Auth.isLoggedIn = true
            Auth.account = user
            Auth.updateStatus()
            Router.go("/account")
        } else {
            alert(response.message)
        }
        if (window.PasswordCredential && user.password) {
            const credential = new PasswordCredential({
                name: user.name,
                password: user.password
            })
            navigator.credentials.store(credential)
        }
    },
    logout: () => {
        Auth.isLoggedIn = false
        Auth.account = null
        Auth.updateStatus()
        Router.go("/")
        if (window.PasswordCredential) {
            navigator.credentials.preventSilentAccess()
        }
    },
    autoLogin: async() => {
        if (window.PasswordCredential) {
            const credentials = await navigator.credentials.get({ password: true})
            try {
                document.getElementById("user-id").value = credentials.name
                document.getElementById("login-password").value = credentials.password
                Auth.login()
            } catch (e) {}
        }
    }
}

Auth.updateStatus()

export default Auth

window.Auth = Auth
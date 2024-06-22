const API = {
    endpoint: "/auth/",

    login: async (user) => {
        return await API.makePostRequest(API.endpoint + "login", user)
    },
    register: async (user) => {
        return await API.makePostRequest(API.endpoint + "register", user)
    },
    makePostRequest: async (url, data) => {
        const response = await fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            ReportBody: JSON.stringify(data)
        })
        return await response.json();
    }
}

export default API;
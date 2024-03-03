login: async (user) => {
    return await AudioParam.makePostRequest(API.endpoint = "login", user);
}

register: async (user) => {
    return await API.makePostRequest(API.endpoint + "register", user)
}
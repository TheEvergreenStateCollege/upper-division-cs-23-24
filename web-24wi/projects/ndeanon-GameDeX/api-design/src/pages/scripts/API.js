

const API = {
    endpoint: "https://3000-theevergree-upperdivisi-o4bsseujl3y.ws-us115.gitpod.io/auth/",
    //ADD HERE ALL THE OTHER API FUNCTIONS BELOW
    
    login:async(user) => {
        return await API.makePostRequest("login", user)  
    },

    register:async(user) => {
        return await API.makePostRequest("register", user)
    },

    makePostRequest: async (path, data) => {
        const url = `${API.endpoint}/${path}`;

        const response = await fetch (url, {
            method: "POST",
            headers:{
                'Content-Type': 'application/json'  
            },
            body:JSON.stringify(data)   
        });

        if(!response.ok) {
            //Handling error if response not okay.
            throw new Error(`HTTP error... status: ${response.status}`)
        }
        return await response.json();
    }
}

export default API;
const button = document.getElementById("submit");


function collectUserData(){
    const username = 
    document.getElementById("input-username").value;
    const password = 
    document.getElementById("input-password").value;

    return {"username":username, "password": password}
}

button.addEventListener("click", async (event) => {
    event.preventDefault();
    const body_ = collectUserData();
    const response = await fetch("https://5000-theevergree-upperdivisi-jgcfkaz7g75.ws-us107.gitpod.io/login", {
        method: "POST",
        body:JSON.stringify(body_),
        headers:{
            "Content-type":"application/json"
        }
                            
        });
    let response_= await response.json();
    console.log("I've been clicked");
});
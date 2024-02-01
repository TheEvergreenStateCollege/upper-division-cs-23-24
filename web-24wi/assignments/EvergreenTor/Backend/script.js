const button = 
document.getElementById("submit");
const username = 
document.getElementById("input-username");
const password = 
document.getElementById("input-password");

button.addEventListener("click", async (event) 
=> {
    event.preventDefault();
    const response = await fetch("http://sub.arcology.builders:5000", {
        "method": "post",
                            "body":{username, password
                            
                            
    }
        });
      
    console.log("I've been clicked");
});
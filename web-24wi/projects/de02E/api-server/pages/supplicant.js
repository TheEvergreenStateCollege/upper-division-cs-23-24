const button = document.getElementById("login");

const usernameElt = document.getElementById("user-id");
const passwordElt = document.getElementById("password");


button.addEventListener("click", async (event) => {
    event.preventDefault();
  
    const username = usernameElt.value;
    const password = passwordElt.value;
    
    console.log(`username ${username}`);
    console.log(`password ${password}`);
  
  // add a fetch here
     const response = await fetch("https://indira.arcology.builders/signin", {
      "method": "post",
      "headers": {
        "Content-Type": "application/json",
        "Access-Control-Allow-Origin": "https://fluffy-waffle-x5w756jggxqq26v6j-8000.app.github.dev"
      },
      "body": JSON.stringify({ usrn: username, pass: password })
    });
  
  // response has a json body
  const jsonBody = await response.json();
    console.log(`Response ${JSON.stringify(jsonBody)}`);
    if (jsonBody.hasOwnProperty("token")) {
      console.log(jsonBody.token)
    }
  });


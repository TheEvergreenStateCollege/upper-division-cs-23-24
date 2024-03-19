const button = document.getElementById("submit");
const usernameElt = document.getElementById("input-username");
const passwordElt = document.getElementById("input-password");
const nameElt = document.getElementById("input-name");

button.addEventListener("click", async (event) => {
  event.preventDefault();

  const username = usernameElt.value;
  const password = passwordElt.value;
  const name = nameElt.value;
  
  console.log(`username ${username}`);
  console.log(`password ${password}`);
  console.log(`password ${name}`);

// add a fetch here
   const response = await fetch("https://tor.arcology.builders/signin", {
    "method": "post",
    "headers": {
      "Content-Type": "application/json",
    },
    "body": JSON.stringify({ username, password, name })
  });

// response has a json body
const jsonBody = await response.json();
  console.log(`Response ${JSON.stringify(jsonBody)}`);
  
});
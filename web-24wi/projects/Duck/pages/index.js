const username = document.getElementById("input-name");
const password = document.getElementById("input-password");
const button = document.getElementById("button-submit");

button.addEventListener("click", async (event) => {
  event.preventDefault();

  const usernamelog = username.value;
  const passwordlog = password.value;
  
  console.log(`username ${usernamelog}`);
  console.log(`password ${passwordlog}`);

// add a fetch here
   const response = await fetch("https://indira.arcology.builders/signin", {
    "method": "post",
    "headers": {
      "Content-Type": "application/json",
    },
    "body": JSON.stringify({ username, password })
  });

// response has a json body
const jsonBody = await response.json();
  console.log(`Response ${JSON.stringify(jsonBody)}`);
  
});
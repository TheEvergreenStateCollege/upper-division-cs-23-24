const username = document.getElementById("input-name");
const password = document.getElementById("input-password");
const button = document.getElementById("button-submit");

button.addEventListener("click", async(Event) => {
    Event.preventDefault();
    const response = await fetch("http://localhost:5000/login", {
      "method": "post",
      "headers": {"Content-Type": "application/json"},
      "body": { username, password }
    });
    console.log("I've been clicked.");
    print(response);
});
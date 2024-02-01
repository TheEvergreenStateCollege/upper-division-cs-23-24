const name_input = document.getElementById("input-name");
const password_input = document.getElementById("input-password");
const submit_button = document.getElementById("button-submit");

submit_button.addEventListener("click", async (event) => {
    fetch(
        "http://cassidy.arcology.builders:5000/login",
        {
            "method": "post",
            "body": {
                username,
                password
            }
        }
    );
    console.log("I've been clicked 😳")
});


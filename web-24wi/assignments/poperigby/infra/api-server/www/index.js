const submitButton = document.getElementById("button-submit");

submitButton.addEventListener("click", async (event) => {
    event.preventDefault();

    const body = {
        "username": document.getElementById("input-name").value,
        "password": document.getElementById("input-password").value,
    }

    fetch(
        "http://cassidy.arcology.builders:5000/login",
        {
            "method": "post",
            "body": JSON.stringify(body)
        }
    );
    console.log("I've been clicked 😳")
});


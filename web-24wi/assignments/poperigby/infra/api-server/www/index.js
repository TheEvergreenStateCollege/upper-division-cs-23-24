const submitButton = document.getElementById("button-submit");

submitButton.addEventListener("click", async (event) => {
    event.preventDefault();

    const body = {
        "username": document.getElementById("input-name").value,
        "password": document.getElementById("input-password").value,
    }

    fetch(
        "https://cassidy.arcology.builders/login",
        {
            "method": "post",
	    "headers": {
	        "Content-Type": "application/json",
	    },
            "body": JSON.stringify(body)
        }
    );
    console.log("I've been clicked 😳")
});


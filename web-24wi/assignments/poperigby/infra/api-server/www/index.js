const submit = document.getElementById("button-submit");

submit.addEventListener("click", async (event) => {
    console.log("yo mama");

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


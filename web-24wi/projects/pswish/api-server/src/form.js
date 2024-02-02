const button = document.getElementById('submit');
const username = document.getElementById('input-username');
const password = document.getElementById('input-password');

button.addEventListener('click', async(e) => {

    e.preventDefault();
    const response = await fetch('http://pswish-corp.arcology.builders:5000/', 
    { "method" : "post",
    "body" : JSON.stringify({"username": username.value, "password": password.value}),
})
})
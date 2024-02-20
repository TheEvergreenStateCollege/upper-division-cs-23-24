const form = document.getElementById("contactForm");
const nameField = document.getElementById("Name");
const emailField = document.getElementById("Email");
const errName = document.getElementById("errorName");
const errEmail = document.getElementById("errorEmail");
const goBack = document.getElementById("Back");
const messageField = document.getElementById("Message");

form.addEventListener('input', function(event) {
    switch(event.target.id) {
        case 'Name':
            checkName();
            break;
        case 'Email':
            checkEmail();
            break;
    }
});

form.addEventListener('submit', function(event) {   //I add some extra things for aesthetic, don't mind me
    // Prevent the default form submission behavior
    event.preventDefault();
    // Redirect to the thank you page
    window.location.href = "thankyou.html";
    //The thank you line has been moving to thankyou.html with center modification
});

//Go back to main page, not working currently
goBack.addEventListener("click", getBack());
function getBack() {
    window.location.href = "index.html";
}

nameField.addEventListener('change', function() {
    this.style.backgroundColor = "#e4fae4";
});

emailField.addEventListener('change', function() {
    this.style.backgroundColor = "#e4fae4";
});

messageField.addEventListener('change', function() {
    this.style.backgroundColor = "#e4fae4";
});

function checkName() {
    if (nameField.value == "") {
        errName.textContent = "Please enter your name";
    }
    else {
        errName.textContent = "";
    }
}

function checkEmail() {
    if (emailField.value == "") {
        errEmail.textContent = "Please enter your email";
    }
    else {
        errEmail.textContent = "";
    }
}

// Client-side validation and sanitization
function handleFormSubmit(event, actionType) {
    event.preventDefault();
    console.log(`Handling form submission for action: ${actionType}`);

    const endpoint = actionType === 'login' ? '/login' : '/create-profile';
    const form = document.forms["loginForm"];
    const formData = new FormData(form);

    // Validation checks
    const firstName = formData.get("firstName") ? formData.get("firstName").trim() : '';
    const lastName = formData.get("lastName") ? formData.get("lastName").trim() : '';
    const email = formData.get("email") ? formData.get("email").trim() : '';
    const password = formData.get("password") ? formData.get("password").trim() : '';
    const emailRegex = /\S+@\S+\.\S+/;
    const passwordMinLength = 10;

    if (!firstName || !lastName || !emailRegex.test(email) || password.length < passwordMinLength) {
        alert("Please fill in all fields correctly and ensure the password meets the minimum length.");
        return false;
    }

    // Manually create URL-encoded data string
    const data = new URLSearchParams();
    for (const pair of formData) {
        data.append(pair[0], pair[1]);
    }

    fetch(endpoint, {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: data
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            if (data.success && data.redirect) {
                window.location.href = data.redirect;
            } else {
                alert("Submission was successful, but no redirect was provided.");
            }
        })
        .catch(error => {
            console.error("Submission error: ", error);
            alert("There was a problem with your submission. Please try again.");
        });

    if (actionType === 'login') {
        console.log("Logging in...");
    } else if (actionType === 'register') {
        console.log("Registering...");
    }
}


// Attach event listeners to buttons
document.addEventListener('DOMContentLoaded', function() {
    const loginBtn = document.getElementById('loginBtn');
    const form = document.forms["loginForm"];
    
    // Login button
    loginBtn.addEventListener('click', function(event) {
        handleFormSubmit(event, 'login');
        });
    
    // Submission form event listener
    form.addEventListener('submit', function(event) {
        handleFormSubmit(event, 'register');
    });
});

document.getElementById('logoutBtn').addEventListener('click', function() {
    const userEmail = document.forms["loginForm"]["email"].value; 
    // Send a fetch request to a new logout route
    fetch('/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({email: userEmail})
    }).then(response => {
        if(response.ok) {

        }
    }).catch(error => {
        console.error("Logout error", error);
    });
});
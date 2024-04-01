document.addEventListener('DOMContentLoaded', function() {
    const loginLinkForgotPasswordForm = document.getElementById('loginLinkForgot');
    const signupForm = document.querySelector('.signup_form');
    const forgotPasswordForm = document.querySelector('.forgot_password_form');
    const signupLink = document.getElementById('signupLink');
    const loginLink = document.getElementById('loginLink');
    const forgotPasswordLink = document.querySelector('.forgot_pw');
    const loginForm = document.querySelector('.login_form');

    // Initially hide the signup and forgot password forms
    signupForm.style.display = 'none';
    forgotPasswordForm.style.display = 'none';

    // Function to toggle display of signup form
    function toggleSignupForm() {
        signupForm.style.display = signupForm.style.display === 'none' ? 'block' : 'none';
        loginForm.style.display = 'none';
        forgotPasswordForm.style.display = 'none';
    }

    // Function to toggle display of forgot password form
    function toggleForgotPasswordForm() {
        forgotPasswordForm.style.display = forgotPasswordForm.style.display === 'none' ? 'block' : 'none';
        signupForm.style.display = 'none';
        loginForm.style.display = 'none';
    }

    // Function to toggle display of login form
    function toggleLoginForm() {
        loginForm.style.display = loginForm.style.display === 'none' ? 'block' : 'none';
        signupForm.style.display = 'none';
        forgotPasswordForm.style.display = 'none';
    }

    // Show/hide signup form when signup link is clicked
    signupLink.addEventListener('click', function(event) {
        event.preventDefault();
        toggleSignupForm();
    });

    // Show/hide login form when login link is clicked
    loginLink.addEventListener('click', function(event) {
        event.preventDefault();
        toggleLoginForm();
    });

    // Show/hide forgot password form when forgot password link is clicked
    forgotPasswordLink.addEventListener('click', function(event) {
        event.preventDefault();
        toggleForgotPasswordForm();
    });

    // Show login form when login link in the forgot password form is clicked
    loginLinkForgotPasswordForm.addEventListener('click', function(event) {
        event.preventDefault();
        toggleLoginForm();
    });

    // Event listener for signup form submission
    signupForm.addEventListener('submit', async function(event) {
        event.preventDefault();
        const signupEmail = document.getElementById('signupEmail').value;
        const signupPassword = document.getElementById('signupPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;

        // Check if passwords match
        if (signupPassword !== confirmPassword) {
            alert('Passwords do not match');
            return;
        }

        // Retrieve existing user data from Local Storage
        //let userData = localStorage.getItem('userData');
        //userData = userData ? JSON.parse(userData) : {};
        const userData = await fetch('/user', {
            method: 'post',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({"username": signupEmail, "password": signupPassword}),
        })

        // Check if user already exists
        //change this into a fetch call to database
        if (userData.hasOwnProperty(signupEmail)) {
            alert('User already exists. Please use a different email.');
            return;
        }

        // Add new user to data
        //userData[signupEmail] = signupPassword;

        // Save updated user data to Local Storage
        localStorage.setItem('userData', JSON.stringify(userData));

        alert('Signup successful. Please login.');
        toggleLoginForm();
    });

    // Event listener for login form submission
    loginForm.addEventListener('submit', async function(event) {
        event.preventDefault();
        const loginEmail = document.getElementById('loginEmail').value;
        const loginPassword = document.getElementById('loginPassword').value;

        // Retrieve user data from Local Storage
        //const userData = JSON.parse(localStorage.getItem('userData'));
        const response = await fetch('/signin', {
            method: 'post',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({"username": loginEmail, "password": loginPassword}),
        });
        if (response.status === 401) {
            const { message } = await response.json();
            alert(message);
            return;
        }
        // Make a request to the server to authenticate the user
        // Assume the server returns a token upon successful authentication
        const { token } = await response.json();

        // Save the token to localStorage
        localStorage.setItem('token', token);
        localStorage.setItem('isLoggedIn', 'true');
        alert('Login successful!');

        // Redirect the user to the home page
        redirectAndShowAlert();

    });

    async function redirectAndShowAlert() {
        window.location.href = '/';
        await new Promise(resolve => setTimeout(resolve, 1000)); // Wait for 1 second
        window.alert('You have successfully logged in as ' + loginEmail + '!');
    }

    // Event listener for forgot password form submission
    forgotPasswordForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const forgotPasswordEmail = document.getElementById('forgotPasswordEmail').value;

        // Implement forgot password logic here
        // For example, you can send a password reset link to the email provided
        alert('Password reset link sent to ' + forgotPasswordEmail);
        toggleLoginForm(); // Assuming you want to go back to the login form after submitting the forgot password form
    });
});

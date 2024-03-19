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
    signupForm.addEventListener('submit', function(event) {
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
        let userData = localStorage.getItem('userData');
        userData = userData ? JSON.parse(userData) : {};

        // Check if user already exists
        if (userData.hasOwnProperty(signupEmail)) {
            alert('User already exists. Please use a different email.');
            return;
        }

        // Add new user to data
        userData[signupEmail] = signupPassword;

        // Save updated user data to Local Storage
        localStorage.setItem('userData', JSON.stringify(userData));

        alert('Signup successful. Please login.');
        toggleLoginForm();
    });

    // Event listener for login form submission
    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();
        const loginEmail = document.getElementById('loginEmail').value;
        const loginPassword = document.getElementById('loginPassword').value;

        // Retrieve user data from Local Storage
        const userData = JSON.parse(localStorage.getItem('userData'));

        // Check if user exists and password matches
        if (userData && userData.hasOwnProperty(loginEmail) && userData[loginEmail] === loginPassword) {
            alert('Login successful!');
            // Redirect or perform any other action after successful login
        } else {
            alert('Invalid email or password.');
        }
    });

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

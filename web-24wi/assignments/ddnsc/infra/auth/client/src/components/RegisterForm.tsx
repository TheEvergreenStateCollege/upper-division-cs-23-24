import React, { useState, FormEvent } from 'react';

interface RegisterFormProps {
    showPassword: boolean;
    togglePasswordVisibility: () => void; // Add this line
}

const RegisterForm: React.FC<RegisterFormProps> = ({ showPassword }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = event.target;
        if (name === 'username') setUsername(value);
        if (name === 'password') setPassword(value);
    }

    const handleSubmit = async (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault();

        // Make sure username and password are not empty
        if (username.trim() === '' || password.trim() === '') {
            alert("Username and password cannot be empty!");
            return;
        }

        try {
            const response = await fetch('http://localhost:5000/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, password })
            });

            if (response.ok) {
                const data = await response.json();
                const token = data.token; // Assuming server responds with a 'token' field in JSON
                // Store the token in local storage or a cookie for future use
                localStorage.setItem('token', token);

                alert("Registration successful!");
                // Redirect to another page or perform any other action
                // window.location.href = "login.html";
            } else {
                alert("Registration failed. Please try again.");
            }
        } catch (error) {
            console.error('Error:', error);
            alert("Registration failed. Please try again later.");
        }
    }

    return (
        <div>
            <form onSubmit={handleSubmit}>
                <label htmlFor="username">Username:</label><br />
                <input
                    type="text"
                    id="username"
                    name="username"
                    value={username}
                    onChange={handleInputChange}
                /><br />
                <label htmlFor="password">Password:</label><br />
                <input
                    type="password"
                    id="password"
                    name="password"
                    value={password}
                    onChange={handleInputChange}
                /><br /><br />
                <button type="submit">Register</button>
            </form>
        </div>
    );
}

export default RegisterForm;

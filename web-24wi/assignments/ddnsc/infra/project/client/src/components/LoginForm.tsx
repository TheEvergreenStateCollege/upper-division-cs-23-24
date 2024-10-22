import React, { useState, FormEvent } from 'react';
import { Link } from 'react-router-dom';
import fetchInstance from "../services/fetch";


interface LoginFormProps {
    showPassword: boolean;
    togglePasswordVisibility: () => void;
}

const LoginForm: React.FC<LoginFormProps> = ({ showPassword, togglePasswordVisibility }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (event: FormEvent) => {
        event.preventDefault();

        try {
            const response = await fetchInstance('login', 'POST', {
                username,
                password,
            });

            // Handle successful login
            console.log('Login successful:', response);
        } catch (error) {
            // Check if the error is an HTTP error and if it has a response
            if (error instanceof Error && error.message.startsWith('HTTP error!')) {
                // Handle login error
                console.error('Login error:', error.message);
            } else {
                // Handle other types of errors
                console.error('Unexpected error:', error);
            }
        }
    };

    const handleWebAuthnLogin = (event: React.MouseEvent<HTMLAnchorElement, MouseEvent>) => {
        event.preventDefault();
        // WebAuthn login logic
    };

    return (
        <form onSubmit={handleLogin}>
            <fieldset>
                <label htmlFor="username">Username</label>
                <input
                    type="text"
                    placeholder="Username"
                    id="login-username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                    autoComplete="username"
                />

                <label htmlFor="login_password">Password</label>
                <input
                    type={showPassword ? 'text' : 'password'}
                    id="login_password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    autoComplete="current-password"
                />
                <label>
                    <input
                        type="checkbox"
                        checked={showPassword}
                        onChange={togglePasswordVisibility}
                    />
                    Show Password
                </label>
            </fieldset>

            <button type="submit">Submit</button>

            <p>
                <a href="#" className="navlink" onClick={handleWebAuthnLogin}>
                    Log in with your Authenticator
                </a>
            </p>
            <p>
                <Link to="/register" className="navlink">
                    Register a new account
                </Link>
            </p>
        </form>
    );
};

export default LoginForm;

import React, { useState, FormEvent } from 'react';
import { Link } from 'react-router-dom';
import axios, { AxiosError } from 'axios';

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
            const response = await axios.post('http://localhost:5000/login', {
                username,
                password,
            });

            // Handle successful login
            console.log('Login successful:', response.data);
        } catch (error: unknown) {
            // Explicitly type the error as AxiosError
            if (axios.isAxiosError(error)) {
                console.error('Login error:', error.response?.data);
            } else {
                console.error('An unknown error occurred:', error);
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

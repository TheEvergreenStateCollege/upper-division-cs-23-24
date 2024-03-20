import React, { useState, FormEvent } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import fetchInstance from "../services/fetch";

interface LoginFormProps {
    showPassword: boolean;
    togglePasswordVisibility: () => void;
}

const LoginForm: React.FC<LoginFormProps> = ({ showPassword, togglePasswordVisibility }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (event: FormEvent) => {
        event.preventDefault();

        try {
            const response = await fetchInstance('login', 'POST', {
                username,
                password,
            });

            if (response.token) {
                // Store the token in local storage or session storage
                localStorage.setItem('token', response.token);
                // Redirect to profile page
                navigate('/profile');
            } else {
                console.error('Login error:', response.error);
            }
        } catch (error) {
            console.error('Unexpected error:', error);
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

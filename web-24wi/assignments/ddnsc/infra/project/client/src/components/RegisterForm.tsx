import React, { useState, FormEvent } from 'react';
import axios from 'axios';

interface RegisterFormProps {
    showPassword: boolean;
    togglePasswordVisibility: () => void;
}

const RegisterForm: React.FC<RegisterFormProps> = ({ showPassword, togglePasswordVisibility }) => {
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleRegister = async (event: FormEvent) => {
        event.preventDefault();

        try {
            const response = await axios.post('http://localhost:5000/register', {
                email,
                username,
                password,
            });

            // Handle successful registration
            console.log('Registration successful:', response.data);
        } catch (error: any) {
            // Handle registration error
            if (error.response && error.response.data) {
                console.error('Registration error:', error.response.data);
            } else {
                console.error('Unexpected error:', error.message);
            }
        }
    };

    return (
        <form id="formRegister" onSubmit={handleRegister}>
            <fieldset>
                <label htmlFor="register_email">Email</label>
                <input
                    type="email"
                    placeholder="Email"
                    id="register_email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                    autoComplete="email"
                />

                <label htmlFor="register_username">Username</label>
                <input
                    type="text"
                    placeholder="Username"
                    id="register_username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    required
                    autoComplete="username"
                />

                <label htmlFor="register_password">Password</label>
                <input
                    type={showPassword ? 'text' : 'password'}
                    id="register_password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    autoComplete="new-password"
                />
            </fieldset>

            <button type="submit">Register Account</button>
        </form>
    );
};

export default RegisterForm;

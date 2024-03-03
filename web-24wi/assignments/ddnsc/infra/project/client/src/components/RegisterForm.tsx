import React, { FormEvent, useState } from 'react';

interface RegisterFormProps {
    showPassword: boolean;
    togglePasswordVisibility: () => void;
}

const RegisterForm: React.FC<RegisterFormProps> = ({ showPassword, togglePasswordVisibility }) => {
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleRegister = (event: FormEvent) => {
        event.preventDefault();

        console.log('Email:', email);
        console.log('Username:', username);
        console.log('Password:', password);

        // Reset form fields after registration
        setEmail('');
        setUsername('');
        setPassword('');
    };

    return (
        <form id="formRegister" onSubmit={handleRegister}>
            <fieldset>
                <label htmlFor="register_email">Email</label>
                <input
                    type="email"
                    placeholder="email"
                    id="register_email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                    autoComplete="email"
                />

                <label htmlFor="register_username">Username</label>
                <input
                    type="text"
                    placeholder="username"
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
                <label>
                    <input
                        type="checkbox"
                        checked={showPassword}
                        onChange={togglePasswordVisibility}
                    />
                    Show Password
                </label>
            </fieldset>

            <button type="submit">Register Account</button>
        </form>
    );
};

export default RegisterForm;

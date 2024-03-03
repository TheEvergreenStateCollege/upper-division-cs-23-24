import React, { useState } from 'react';
import LoginForm from '../components/LoginForm';

const LoginPage: React.FC = () => {
    const [showPassword, setShowPassword] = useState(false);

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    return (
        <div>
            <h2>Login Page</h2>
            <LoginForm
                showPassword={showPassword}
                togglePasswordVisibility={togglePasswordVisibility}
            />
        </div>
    );
};

export default LoginPage;

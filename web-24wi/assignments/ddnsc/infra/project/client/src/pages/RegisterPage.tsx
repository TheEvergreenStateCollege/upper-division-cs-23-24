import React, { useState } from "react";
import RegisterForm from "../components/RegisterForm";

const RegisterPage: React.FC = () => {
    const [showPassword, setShowPassword] = useState(false);

    const togglePasswordVisibility = () => {
        setShowPassword(!showPassword);
    };

    return (
        <div>
            <h2>Register Page</h2>
            <RegisterForm
                showPassword={showPassword}
                togglePasswordVisibility={togglePasswordVisibility}
            />
        </div>
    );
};

export default RegisterPage;

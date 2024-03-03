import React from 'react';
import { Link } from 'react-router-dom';

const Navigation: React.FC = () => {
    return (
        <nav>
            <Link to="/login">Login</Link>
            <Link to="/register">Register a new account</Link>
        </nav>
    );
};

export default Navigation;
import React from 'react';
import { Link } from 'react-router-dom';

const LandingPage: React.FC = () => {
    return (
        <div>
            <h2>Welcome</h2>
            <Link to="/login">Login or Register an Account</Link>
        </div>
    );
};

export default LandingPage;
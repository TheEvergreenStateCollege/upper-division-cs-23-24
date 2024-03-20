import React, { useState } from 'react';
import { Navigate } from 'react-router-dom';

const ProfilePage: React.FC = () => {
    // State to manage redirect after logout
    const [loggedOut, setLoggedOut] = useState(false);

    // Check if user is authenticated based on JWT token presence
    const isAuthenticated = !!localStorage.getItem('token');

    const handleLogout = () => {
        // Clear the JWT token from local storage upon logout
        localStorage.removeItem('token');
        // Set the state to trigger the redirect
        setLoggedOut(true);
    };

    if (!isAuthenticated) {
        // If user is not authenticated, redirect to login page
        return <Navigate to="/login" />;
    }

    if (loggedOut) {
        // If logout is triggered, redirect to login page
        return <Navigate to="/login" />;
    }

    return (
        <div>
            <h1>Profile Page</h1>
            <button onClick={handleLogout}>Logout</button>
        </div>
    );
};

export default ProfilePage;

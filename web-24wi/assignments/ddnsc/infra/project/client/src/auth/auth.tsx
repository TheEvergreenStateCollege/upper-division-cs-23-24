import React, { createContext, useContext, useState, useEffect, ReactNode } from 'react';

interface AuthContextType {
    token: string | null;
    login: (token: string) => void;
    logout: () => void;
    isAuthenticated: () => boolean;
}

// Create a context to manage authentication state
export const AuthContext = createContext<AuthContextType | undefined>(undefined);

// Custom hook to access the authentication context
export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};

// AuthProvider component to wrap your app and provide authentication context
interface AuthProviderProps {
    children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
    const [authToken, setAuthToken] = useState<string | null>(null);

    // Check if a token is stored in localStorage on component mount
    useEffect(() => {
        const storedToken = localStorage.getItem('token');
        if (storedToken) {
            setAuthToken(storedToken);
        }
    }, []);

    // Function to set the authentication token
    const login = (token: string) => {
        setAuthToken(token);
        localStorage.setItem('token', token);
    };

    // Function to clear the authentication token
    const logout = () => {
        setAuthToken(null);
        localStorage.removeItem('token');
    };

    // Check if the user is authenticated
    const isAuthenticated = () => {
        return authToken !== null;
    };

    // Provide the authentication context to the app
    const contextValue: AuthContextType = {
        token: authToken,
        login,
        logout,
        isAuthenticated,
    };

    return <AuthContext.Provider value={contextValue}>{children}</AuthContext.Provider>;
};

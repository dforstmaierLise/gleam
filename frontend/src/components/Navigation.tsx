import React from 'react';
import './Navigation.css';
import UserLogin from "./UserLogin.tsx";

const Navigation: React.FC = () => {
    return (
        <nav className="navigation">
            <UserLogin/>
        </nav>
    );
}

export default Navigation;

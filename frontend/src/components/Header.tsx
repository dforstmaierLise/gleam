import React, {useState} from 'react';
import {getOrCreateUser} from "../services/api.ts";
import {RegisterUserRequest} from "../data/RegisterUserRequest.ts";
import './Header.css';
import {UserDto} from "../data/UserDto.ts";

const Header: React.FC = () => {
    const [inputValue, setInputValue] = useState<string>('');
    const [user, setUser] = useState<UserDto | null>(null);

    const handleLogin = async () => {

        if (user) {
            // Fake logout.
            setUser(null);
            return;
        }

        // Fake login.
        try {
            const request: RegisterUserRequest = {
                username: inputValue,
                password: "Hardcoded@Password123!"
            }
            const dto = await getOrCreateUser(request);
            setUser(dto);
            console.log(dto)
        } catch (error) {
            console.error(error);
        }
    }

    return (
        <header className="header">
            <div className="header-content">
                {user ? (
                    <p>Welcome, {user.username}!</p>
                ) : (
                    <>
                        <input className="header-input"
                               type="text"
                               placeholder="Enter username"
                               value={inputValue}
                               onChange={(e) => setInputValue(e.target.value)}/>
                    </>
                )}
                <button className="header-button"
                        onClick={handleLogin}>{user ? ("Logout") : ("Login")}
                </button>
            </div>
        </header>
    );
};

export default Header;

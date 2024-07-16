import React, {useState} from 'react';
import {getOrCreateUser} from "../services/api.ts";
import {RegisterUserRequest} from "../data/RegisterUserRequest.ts";
import './UserLogin.css';
import {UserDto} from "../data/UserDto.ts";
import {Button} from "@mui/material";
import {useQuery} from "@tanstack/react-query";

const UserLogin: React.FC = () => {
    const [inputValue, setInputValue] = useState<string>('');
    const [user, setUser] = useState<UserDto | undefined>(undefined);
    const [errorMsg, setErrorMsg] = useState<string | null>(null);

    const registerUserRequest: RegisterUserRequest = {
        username: inputValue,
        password: "Hardcoded@Password123!"
    }

    const {data, error, refetch} = useQuery<UserDto>({
        queryKey: ['registerUserRequest', registerUserRequest],
        queryFn: () => getOrCreateUser(registerUserRequest),
        enabled: false, // only fetch data on calling 'refetch'
        retry: false
    });

    const updateErrorMessage = (name: string, error: Error | null) => {
        if (!error) {
            setErrorMsg(null);
            return;
        }

        const allowLettersAndNumbers = (): RegExp => {
            return /^[A-Za-z0-9]+$/;
        };

        if (name.length < 3 || name.length > 20) {
            setErrorMsg('The user name must be between 3 and 20 characters long.');
            return;
        }

        if (!allowLettersAndNumbers().test(name)) {
            setErrorMsg('The user name may only contain letters and numbers.');
            return;
        }

        setErrorMsg(null);
    };

    // On a logout I want to have the user data set to "undefined".
    // TanStack doesnt really support setting the data to undefined manually,
    // hence the workaround with the useState user data.
    React.useEffect(() => {
        setUser(data);
        updateErrorMessage(inputValue, error);
    }, [data, error]);


    const onButtonClick = () => {
        if (user) {
            // Fake logout.
            setUser(undefined);
            return;
        }

        // Fake login.
        refetch();
    };

    return (
        <div className="login-container">
            <p>{errorMsg}</p>
            {user ? (
                <p>Welcome, {user?.username}!</p>
            ) : (
                <>
                    <input className="login-input"
                           type="text"
                           placeholder="Enter username"
                           value={inputValue}
                           onChange={(e) => setInputValue(e.target.value)}/>
                </>
            )}
            <Button variant="contained" className="login-button"
                    onClick={onButtonClick}>{user ? ("Logout") : ("Login")}
            </Button>
        </div>
    );
};

export default UserLogin;

import React from 'react';
import './App.css';
import Header from "./Header.tsx";
import Navigation from "./Navigation.tsx";
import MainContent from "./MainContent.tsx";
import theme from "../theme.ts";
import {ThemeProvider} from "@mui/material";

const App: React.FC = () => {
    return (
        <ThemeProvider theme={theme}>
            <div className="app">
                <div className="app-container">
                    <Header/>
                    <Navigation/>
                    <MainContent/>
                </div>
            </div>
        </ThemeProvider>
    );
};

export default App;

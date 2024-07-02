import React from 'react';
import './App.css';
import Header from "./Header.tsx";
import Navigation from "./Navigation.tsx";
import MainContent from "./MainContent.tsx";
import theme from "../theme.ts";
import {ThemeProvider} from "@mui/material";
import {DialogProvider} from "./dialogs/DialogContext.tsx";
import DialogManager from "./dialogs/DialogManager.tsx";

const App: React.FC = () => {
    return (
        <ThemeProvider theme={theme}>
            <DialogProvider>
                <DialogManager/>
                <div className="app">
                    <div className="app-container">
                        <Header/>
                        <Navigation/>
                        <MainContent/>
                    </div>
                </div>
            </DialogProvider>
        </ThemeProvider>
    );
};

export default App;

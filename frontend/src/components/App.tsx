import React from 'react';
import './App.css';
import Header from "./Header.tsx";
import Navigation from "./Navigation.tsx";
import MainContent from "./MainContent.tsx";

const App: React.FC = () => {
    return (
        <div className="app">
            <div className="app-container">
                <Header/>
                <Navigation/>
                <MainContent/>
            </div>
        </div>
    );
};

export default App;

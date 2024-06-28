import React from 'react';
import './MainContent.css';
import GameTable from "./GameTable.tsx";

const MainContent: React.FC = () => {
    return (
        <div className="container">
            <div className="sidebar">
                <h2>Filter</h2>
                <p>Here you will find some check boxes to select some platforms to filter.</p>
            </div>
            <div className="main-content">
                <GameTable/>
            </div>
        </div>
    );
}

export default MainContent;

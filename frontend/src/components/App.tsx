import React, { useState } from 'react';
import Popup from "./PopupRateGame.tsx";
import GameTable from "./GameTable.tsx";
import './App.css';

const App: React.FC = () => {
    const [isPopupVisible, setIsPopupVisible] = useState<boolean>(false);

    const handleClosePopup = () => {
        setIsPopupVisible(false);
    };

    return (
        <div className="App">
            <GameTable/>
            {isPopupVisible && <Popup onClose={handleClosePopup}/>}
        </div>
    );
};

export default App;

import React, { useState } from 'react';
import Popup from "./PopupRateGame.tsx";
import GameTable from "./GameTable.tsx";

const App: React.FC = () => {
    const [isPopupVisible, setIsPopupVisible] = useState<boolean>(false);

    const handleArticleClick = () => {
        setIsPopupVisible(true);
    };

    const handleClosePopup = () => {
        setIsPopupVisible(false);
    };

    return (
        <div className="App">
            <GameTable onOpen={handleArticleClick}/>
            {isPopupVisible && <Popup onClose={handleClosePopup}/>}
        </div>
    );
};

export default App;

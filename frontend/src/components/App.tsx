import React, {useState} from 'react';
import Popup from "./PopupRateGame.tsx";
import GameTable from "./GameTable.tsx";
import './App.css';
import Header from "./Header.tsx";

const App: React.FC = () => {
    const [isPopupVisible, setIsPopupVisible] = useState<boolean>(false);

    const handleClosePopup = () => {
        setIsPopupVisible(false);
    };

    return (
        <div className="App">
            <Header/>
            <GameTable/>
            {isPopupVisible && <Popup onClose={handleClosePopup}/>}
        </div>
    );
};

export default App;

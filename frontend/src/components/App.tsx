import React, {useState} from 'react';
import Popup from "./PopupRateGame.tsx";
import './App.css';
import Header from "./Header.tsx";
import Navigation from "./Navigation.tsx";
import MainContent from "./MainContent.tsx";

const App: React.FC = () => {
    const [isPopupVisible, setIsPopupVisible] = useState<boolean>(false);

    const handleClosePopup = () => {
        setIsPopupVisible(false);
    };

    return (
        <div className="app">
            <div className="app-container">
                <Header/>
                <Navigation/>
                <MainContent/>
            </div>
            {isPopupVisible && <Popup onClose={handleClosePopup}/>}
        </div>
    );
};

export default App;

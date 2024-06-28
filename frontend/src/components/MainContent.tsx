import React, {useState} from 'react';
import './MainContent.css';
import GameTable from "./GameTable.tsx";
import CheckboxList from "./CheckboxList.tsx";

const MainContent: React.FC = () => {

    const [selectedValues, setSelectedValues] = useState<string[]>(["PC"]);

    const handleCheckboxListChange = (values: string[]) => {
        setSelectedValues(values);
        // console.log(selectedValues);
    };


    return (
        <div className="container">
            <div className="sidebar">
                <h2>Filter</h2>
                <p>Here you will find some check boxes to select some platforms to filter.</p>
                <CheckboxList
                    values={['PC', 'PS5', 'Xbox', 'Switch']}
                    onChange={handleCheckboxListChange}/>
            </div>
            <div className="main-content">
                <GameTable platformFilter={selectedValues}/>
            </div>
        </div>
    );
}

export default MainContent;

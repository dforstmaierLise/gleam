import React, {useState} from 'react';
import './MainContent.css';
import GameTable from "./GameTable.tsx";
import CheckboxList from "./CheckboxList.tsx";
import SearchByName from "./SearchByName.tsx";

const MainContent: React.FC = () => {

    const [platformFilter, setPlatformFilter] = useState<string[]>(["PC"]);
    const [prefixFilter, setPrefixFilter] = useState<string>("");

    const handleCheckboxListChange = (values: string[]) => {
        setPlatformFilter(values);
    };

    const handleSearchByNameChange = (selectedPrefix: string) => {
        setPrefixFilter(selectedPrefix);
    };



    return (
        <div className="container">
            <div className="sidebar">
                <h2>Filter</h2>
                <SearchByName platformFilter={platformFilter} onPrefixChange={handleSearchByNameChange}/>
                <CheckboxList
                    values={['PC', 'PS5', 'Xbox', 'Switch']}
                    onChange={handleCheckboxListChange}/>
            </div>
            <div className="main-content">
                <GameTable platformFilter={platformFilter} prefixFilter={prefixFilter}/>
            </div>
        </div>
    );
}

export default MainContent;

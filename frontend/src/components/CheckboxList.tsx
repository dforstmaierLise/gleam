import React, {useState} from 'react';
import './CheckboxList.css';

interface CheckboxListProps {
    values: string[];
    onChange: (selectedValues: string[]) => void;
}

const CheckboxList: React.FC<CheckboxListProps> = ({values, onChange}) => {
    const [selectedValues, setSelectedValues] = useState<string[]>(['PC']);

    const handleCheckboxChange = (value: string) => {
        let updatedValues = [...selectedValues];
        if (updatedValues.includes(value)) {
            updatedValues = updatedValues.filter(v => v !== value);
        } else {
            updatedValues.push(value);
        }
        setSelectedValues(updatedValues);
        onChange(updatedValues);
    };

    return (
        <div className="filter">
            {values.map(value => (
                <label key={value}>
                    <input
                        type="checkbox"
                        value={value}
                        checked={selectedValues.includes(value)}
                        onChange={() => handleCheckboxChange(value)}
                    />
                    {value}
                </label>
            ))}
        </div>
    );
};

export default CheckboxList
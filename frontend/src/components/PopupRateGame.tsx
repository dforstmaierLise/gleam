import React, {useState} from "react";
import './PopupRateGame.css';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import ThumbDownIcon from '@mui/icons-material/ThumbDown';
import ToggleButton from '@mui/material/ToggleButton';
import ToggleButtonGroup from '@mui/material/ToggleButtonGroup';

interface PopupProps {
    onClose: () => void;
}

const Popup: React.FC<PopupProps> = ({onClose}) => {
    const [inputValue, setInputValue] = useState<string>('');

    const [selectedOption, setSelectedOption] = useState(null);

    const handleConfirm = () => {
        alert(`You entered: ${inputValue}`);
        onClose();
    };

    const handleInputChange = (event) => {
        setInputValue(event.target.value);
    };

    const handleOptionChange = (event, newOption) => {
        setSelectedOption(newOption);
    };

    return (
        <div className="popup">
            <div className="popup-content">
                <Grid container spacing={2} alignItems="center">
                    <Grid item xs={4}>
                        <ToggleButtonGroup
                            value={selectedOption}
                            exclusive
                            onChange={handleOptionChange}
                            aria-label="toggle thumbs up/down"
                        >
                            <ToggleButton value="thumbs-up" aria-label="thumbs up">
                                <ThumbUpIcon/>
                            </ToggleButton>
                            <ToggleButton value="thumbs-down" aria-label="thumbs down">
                                <ThumbDownIcon/>
                            </ToggleButton>
                        </ToggleButtonGroup>
                    </Grid>
                    <Grid item xs={4}>
                        <TextField
                            label="Additional Input"
                            variant="outlined"
                            value={inputValue}
                            onChange={handleInputChange}
                            fullWidth
                        />
                    </Grid>
                    <Grid item xs={4} container justifyContent="flex-end" spacing={2}>
                        <Grid item>
                            <Button variant="contained" color="secondary" onClick={onClose}>
                                X
                            </Button>
                        </Grid>
                        <Grid item>
                            <Button variant="contained" color="primary" onClick={handleConfirm}>
                                Confirm
                            </Button>
                        </Grid>
                    </Grid>
                </Grid>
            </div>
        </div>
    );
};

export default Popup;
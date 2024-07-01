import React, {useEffect, useState} from "react";
import {getGameTitlesWithPrefix} from "../services/api.ts";
import {Autocomplete, CircularProgress, TextField} from "@mui/material";
import {GetGamesRequest} from "../data/GetGamesRequest.ts";

interface SearchByNameProps {
    platformFilter: string[];
    onPrefixChange: (selectedPrefix: string) => void;
}

const SearchByName: React.FC<SearchByNameProps> = ({platformFilter, onPrefixChange}) => {
    const [inputValue, setInputValue] = useState<string>('');
    const [suggestedGames, setSuggestedGames] = useState<string[]>([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        const fetchTitles = async () => {
            if (inputValue.length < 2) {
                setSuggestedGames([]);
                return;
            }

            setLoading(true);

            try {
                const request: GetGamesRequest = {
                    platforms: platformFilter,
                    prefix: inputValue
                }
                const suggestions = await getGameTitlesWithPrefix(request);
                setSuggestedGames(suggestions);
            } catch (error) {
                setSuggestedGames([]);
            } finally {
                setLoading(false);
            }
        }

        // Debounce API call to reduce amount of calls.
        const timeoutId = setTimeout(() => {
            fetchTitles();
        }, 300);

        return () => clearTimeout(timeoutId);

    }, [inputValue, platformFilter]);

    const handleSelectionChange = (event: React.ChangeEvent<unknown>, value: string | null) => {
        onPrefixChange(value === null ? "" : value);
    };

    return (
        <Autocomplete
            freeSolo
            options={suggestedGames}
            inputValue={inputValue}
            onInputChange={(event, value) => setInputValue(value)}
            onChange={handleSelectionChange}
            renderInput={(params) => (
                <TextField
                    {...params}
                    label="Search games"
                    variant="outlined"
                    InputProps={{
                        ...params.InputProps,
                        endAdornment: (
                            <>
                                {loading ? <CircularProgress color="inherit" size={20}/> : null}
                                {params.InputProps.endAdornment}
                            </>
                        )
                    }}
                />
            )}
        />
    );
}

export default SearchByName;
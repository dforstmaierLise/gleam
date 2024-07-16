import React, {useState} from "react";
import {getGameTitlesWithPrefix} from "../services/api.ts";
import {Autocomplete, CircularProgress, TextField} from "@mui/material";
import {GetGamesRequest} from "../data/GetGamesRequest.ts";
import {useQuery} from "@tanstack/react-query";

interface SearchByNameProps {
    platformFilter: string[];
    onPrefixChange: (selectedPrefix: string) => void;
}

const SearchByName: React.FC<SearchByNameProps> = ({platformFilter, onPrefixChange}) => {
    const [inputValue, setInputValue] = useState<string>('');
    const getGamesRequest: GetGamesRequest = {
        platforms: platformFilter,
        prefix: inputValue
    };
    const {data, isLoading} = useQuery<string[]>({
        queryKey: ['getGameTitlesWithPrefix', getGamesRequest],
        queryFn: () => getGameTitlesWithPrefix(getGamesRequest),
        enabled: inputValue.length > 1
    });

    const handleSelectionChange = (event: React.ChangeEvent<unknown>, value: string | null) => {
        onPrefixChange(value ?? "");
    };

    return (
        <Autocomplete
            freeSolo
            options={data ?? []}
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
                                {isLoading ? <CircularProgress color="inherit" size={20}/> : null}
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
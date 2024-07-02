import React from 'react';
import {Dialog, DialogContent, DialogTitle, Stack, TextField, Typography} from '@mui/material';
import {useDialog} from './DialogContext';
import {default as _ReactPlayer} from 'react-player/lazy';
import {ReactPlayerProps} from "react-player/types/lib";

const ReactPlayer = _ReactPlayer as unknown as React.FC<ReactPlayerProps>;

export interface GameDetailsDialogProps {
    title: string;
    description: string;
    releaseDate: string;
    developerName: string;
    score: string;
    youtubeTrailer: string;
}

const GameDetailsDialog: React.FC = () => {
    const {dialogProps, closeDialog} = useDialog();

    if (!dialogProps || !(dialogProps as GameDetailsDialogProps).title) {
        return null; // or handle the empty state as needed
    }

    const {
        title,
        description,
        releaseDate,
        developerName,
        score,
        youtubeTrailer
    } = dialogProps as GameDetailsDialogProps;

    return (
        <Dialog open={!!title} onClose={closeDialog} maxWidth="md" fullWidth>
            <ReactPlayer
                url={youtubeTrailer}
                playing={true}
                width="100%"
                height="400px"
                loop={true}
            />
            <DialogTitle>
                {title}
            </DialogTitle>
            <DialogContent>
                <Stack spacing={2}>
                    <Typography variant="body1">{description}</Typography>
                    <Typography variant="body2"><strong>Erscheinungsjahr:</strong> {releaseDate}</Typography>
                    <Typography variant="body2"><strong>Entwickler:</strong> {developerName}</Typography>
                    <Typography variant="body2"><strong>Glam Score:</strong> {score}</Typography>
                    <TextField
                        fullWidth label="fullWidth" id="fullWidth"
                        InputProps={{sx: {height: 150}}}
                    />
                </Stack>
            </DialogContent>
        </Dialog>
    );
};

export default GameDetailsDialog;

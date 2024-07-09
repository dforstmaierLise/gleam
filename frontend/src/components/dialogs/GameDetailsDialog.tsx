import React from 'react';
import {Dialog, DialogContent, DialogTitle, Stack, Typography} from '@mui/material';
import {useDialog} from './useDialog.ts';
import {default as _ReactPlayer} from 'react-player/lazy';
import {ReactPlayerProps} from "react-player/types/lib";

const ReactPlayer = _ReactPlayer as unknown as React.FC<ReactPlayerProps>;

export type GameDetailsDialogProps = {
    title: string;
    description: string;
    releaseDate: string;
    developerName: string;
    score: string;
    youtubeTrailer: string;
}

const GameDetailsDialog: React.FC = () => {
    const {dialogProps, closeDialog} = useDialog();

    if (dialogProps === null) {
        return (
            <Dialog open={true} onClose={closeDialog} maxWidth="md" fullWidth>
                <DialogTitle> Id not found </DialogTitle>
                <DialogContent>
                    <Stack spacing={2}>
                        <Typography variant="body1">Could not find any details.</Typography>
                    </Stack>
                </DialogContent>
            </Dialog>
        );
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
                </Stack>
            </DialogContent>
        </Dialog>
    );
};

export default GameDetailsDialog;

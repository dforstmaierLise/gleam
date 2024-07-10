import React from 'react';
import {Dialog, DialogContent, DialogTitle, Stack, Typography} from '@mui/material';
import {useDialog} from './useDialog.ts';
import ReactPlayer from 'react-player/lazy'

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

    const gameDetailsProps = dialogProps as GameDetailsDialogProps;

    return (
        <Dialog open={true} onClose={closeDialog} maxWidth="md" fullWidth>
            <ReactPlayer
                url={gameDetailsProps.youtubeTrailer}
                playing={true}
                width="100%"
                height="400px"
                loop={true}
            />
            <DialogTitle>
                {gameDetailsProps.title}
            </DialogTitle>
            <DialogContent>
                <Stack spacing={2}>
                    <Typography variant="body1">{gameDetailsProps.description}</Typography>
                    <Typography variant="body2"><strong>Erscheinungsjahr:</strong> {gameDetailsProps.releaseDate}
                    </Typography>
                    <Typography variant="body2"><strong>Entwickler:</strong> {gameDetailsProps.developerName}
                    </Typography>
                    <Typography variant="body2"><strong>Glam Score:</strong> {gameDetailsProps.score}</Typography>
                </Stack>
            </DialogContent>
        </Dialog>
    );
};

export default GameDetailsDialog;

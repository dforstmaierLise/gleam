import React from 'react';
import {Button, Dialog, DialogActions, DialogContent, DialogTitle, Typography} from '@mui/material';
import {useDialog} from './DialogContext';

export interface InfoDialogProps {
    title: string;
    message: string;
}

const InfoDialog: React.FC = () => {
    const {dialogProps, closeDialog} = useDialog();

    if (!dialogProps || !(dialogProps as InfoDialogProps).title) {
        return null; // or handle the empty state as needed
    }

    const {title, message} = dialogProps as InfoDialogProps;

    return (
        <Dialog open={!!title} onClose={closeDialog}>
            <DialogTitle>{title}</DialogTitle>
            <DialogContent>
                <Typography>{message}</Typography>
            </DialogContent>
            <DialogActions>
                <Button onClick={closeDialog}>Close</Button>
            </DialogActions>
        </Dialog>
    );
};

export default InfoDialog;

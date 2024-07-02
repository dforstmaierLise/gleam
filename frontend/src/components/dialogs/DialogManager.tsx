import React from 'react';
import {useDialog} from './DialogContext';
import InfoDialog from './InfoDialog';
import GameDetailsDialog from "./GameDetailsDialog.tsx";

const DialogManager: React.FC = () => {
    const {dialogType} = useDialog();

    switch (dialogType) {
        case 'info':
            return <InfoDialog/>;
        case 'gameDetails':
            return <GameDetailsDialog/>;
        default:
            return null;
    }
};

export default DialogManager;

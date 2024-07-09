import React, {createContext, ReactNode, useState} from 'react';
import {InfoDialogProps} from "./InfoDialog.tsx";
import {GameDetailsDialogProps} from "./GameDetailsDialog.tsx";

export type DialogType = 'info' | 'gameDetails';

export type DialogPropsMap = {
    info: InfoDialogProps;
    gameDetails: GameDetailsDialogProps;
};

interface DialogContextProps {
    dialogType: DialogType | null;
    dialogProps: DialogPropsMap[DialogType] | null;
    openDialog: <T extends DialogType>(type: T, props: DialogPropsMap[T]) => void;
    openInfo: (title: string, message: string) => void;
    closeDialog: () => void;
}

export const DialogContext = createContext<DialogContextProps | undefined>(undefined);

export const DialogProvider: React.FC<{ children: ReactNode }> = ({children}) => {
    const [dialogType, setDialogType] = useState<DialogType | null>(null);
    const [dialogProps, setDialogProps] = useState<DialogPropsMap[DialogType] | null>(null);

    const openDialog = <T extends DialogType>(type: T, props: DialogPropsMap[T]) => {
        setDialogType(type);
        setDialogProps(props);
    };

    const openInfo = (title: string, message: string) => {
        setDialogType('info')

        const infoProps: InfoDialogProps = {
            title: title,
            message: message
        }
        setDialogProps(infoProps);
    }

    const closeDialog = () => {
        setDialogType(null);
        setDialogProps(null);
    };

    return (
        <DialogContext.Provider value={{dialogType, dialogProps, openDialog, openInfo, closeDialog}}>
            {children}
        </DialogContext.Provider>
    );
};
import React from "react";
import './GameEntry.css';
import {addDislike, addLike, getGameDetails} from "../services/api.ts";
import {Game} from "../data/Game.ts";
import ThumbUpIcon from "@mui/icons-material/ThumbUp";
import ThumbDownIcon from "@mui/icons-material/ThumbDown";
import {Badge, BadgeProps, Button, IconButton, styled} from "@mui/material";
import {useDialog} from "./dialogs/useDialog.ts";
import {GameDetailsDialogProps} from "./dialogs/GameDetailsDialog.tsx";
import {useMutation, useQuery} from "@tanstack/react-query";
import {GameDetails} from "../data/GameDetails.ts";

interface GameEntryProps {
    game: Game;
    onLike: () => void;
}

const StyledBadge = styled(Badge)<BadgeProps>(() => ({
    '& .MuiBadge-badge': {
        right: 12,
    },
}));

const GameEntry: React.FC<GameEntryProps> = ({game, onLike}) => {
    const {openDialog} = useDialog();

    const {refetch: fetchGameDetails} = useQuery<GameDetails>({
        queryKey: ['getGameDetails', game],
        queryFn: () => getGameDetails(game.id),
        enabled: false
    });

    const addLikeMutation = useMutation({
        mutationFn: (gameId: string) => addLike(gameId),
        onSuccess: onLike
    });

    const addDislikeMutation = useMutation({
        mutationFn: (gameId: string) => addDislike(gameId),
        onSuccess: onLike
    });

    if (!game || !openDialog) {
        return (
            <div className="gameCard">
                <p>Game not found</p>
            </div>
        );
    }


    const calcGlamFactor = (likes: number, dislikes: number): number => {
        const sum = likes + dislikes;
        const factor = likes / sum;
        // Return the exponential value to add more visual emphasis on very good games
        return factor * factor;
    }

    const getGlamString = (glamFactor: number): string => {
        return (glamFactor * 100).toFixed(0);
    }

    const glamFactor = calcGlamFactor(game.likes, game.dislikes);
    const glamString = getGlamString(glamFactor);


    const handleDetailsClick = async () => {

        try {
            const result = await fetchGameDetails();
            if (result.data) {
                const gameDetailsProps: GameDetailsDialogProps = {
                    title: game.title,
                    developerName: game.developer,
                    releaseDate: game.releaseDate,
                    score: glamString,
                    youtubeTrailer: result.data.trailerUrl,
                    description: result.data.description
                }

                openDialog('gameDetails', gameDetailsProps);
            }
        } catch (error) {
            openDialog('info', {title: "Game not found", message: error.message});
        }
    };

    return (
        <div
            className="gameCard"
            style={{
                '--glow-intensity': `${glamFactor}`,
            }}>
            <img src={game.coverUrl} alt={"game logo"}/>
            <div className="containerDetails">
                <h4 className="detailItem"><b>{game.title}</b></h4>
                <div className="detailItem">
                    <p className="detailItem">Glam score: <b>{glamString}</b></p>
                    <p className="detailItem">Developer: {game.developer}</p>
                    <p className="detailItem">Release: {game.releaseDate}</p>
                    <p className="detailItem">Platforms: {game.platforms?.join(', ')}</p>
                </div>
                <div className="detailItem buttonList">
                    <IconButton value="thumbs-up" aria-label="thumbs up"
                                onClick={() => {
                                    addLikeMutation.mutate(game.id)
                                }}>
                        <ThumbUpIcon/>
                    </IconButton>
                    <StyledBadge badgeContent={game.likes} color="primary" overlap="rectangular" max={9999}/>
                    <IconButton value="thumbs-down" aria-label="thumbs down"
                                onClick={() => {
                                    addDislikeMutation.mutate(game.id)
                                }}>
                        <ThumbDownIcon/>
                    </IconButton>
                    <StyledBadge badgeContent={game.dislikes} color="warning" overlap="rectangular" max={9999}/>
                    <Button onClick={handleDetailsClick}>Details</Button>
                </div>
            </div>
        </div>
    );
}

export default GameEntry
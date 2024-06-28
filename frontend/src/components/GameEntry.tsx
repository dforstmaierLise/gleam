import React from "react";
import './GameEntry.css';
import {addDislike, addLike} from "../services/api.ts";
import {Game} from "../data/Game.ts";
import ThumbUpIcon from "@mui/icons-material/ThumbUp";
import ThumbDownIcon from "@mui/icons-material/ThumbDown";
import {Badge, BadgeProps, IconButton, styled} from "@mui/material";

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
    if (!game) {
        return null;
    }

    const transformGameName = (gameName: string): string => {
        return gameName.toLowerCase().replace(/ /g, '_');
    };

    const calcGlamFactor = (likes: number, dislikes: number): number => {
        const sum = likes + dislikes;
        const factor = likes / sum;
        // Return the exponential value to add more visual emphasis on very good games
        return factor * factor;
    }

    const handleAddLike = async () => {
        try {
            await addLike(game.id);
            onLike();
        } catch (error) {
            console.error(error);
        }
    }

    const handleAddDislike = async () => {
        try {
            await addDislike(game.id);
            onLike();
        } catch (error) {
            console.error(error);
        }
    }

    const transformedName = transformGameName(game.title);
    const logoUrl = `/images/logo-${transformedName}.webp`;
    const glamFactor = calcGlamFactor(game.likes, game.dislikes);

    return (
        <div
            className="gameCard"
            style={{
                '--glow-intensity': `${glamFactor}`,
            }}>
            <img src={logoUrl} alt={"game logo"}/>
            <div className="containerDetails">
                <h4 className="detailItem"><b>{game.title}</b></h4>
                <div className="detailItem">
                    <p className="detailItem">Glam score: <b>{(glamFactor * 100).toFixed(0)}</b></p>
                    <p className="detailItem">Developer: {game.developer}</p>
                    <p className="detailItem">Release-Date: {game.releaseDate}</p>
                    <p className="detailItem">Platforms: {game.platforms?.join(', ')}</p>
                </div>
                <div className="detailItem buttonList">
                    <IconButton value="thumbs-up" aria-label="thumbs up" onClick={handleAddLike}>
                        <ThumbUpIcon/>
                    </IconButton>
                    <StyledBadge badgeContent={game.likes} color="primary" overlap="rectangular" max={9999}/>
                    <IconButton value="thumbs-down" aria-label="thumbs down" onClick={handleAddDislike}>
                        <ThumbDownIcon/>
                    </IconButton>
                    <StyledBadge badgeContent={game.dislikes} color="warning" overlap="rectangular" max={9999}/>
                </div>
            </div>
        </div>
    );
}

export default GameEntry
import {getAllGames} from "../services/api.ts";
import React, { FunctionComponent, useEffect, useState } from 'react';
import GameDto from "../data/GameDto.ts";
import GameEntry from "./GameEntry.tsx";

interface PopupProps{
    onOpen: () => void;
}

const GameTable: FunctionComponent<PopupProps> = ({onOpen}) => {

    const [allGames, setAllGames ] = useState<GameDto[]>();

    useEffect(() => {
        refreshGames();
    }, []);

    const refreshGames = async() => {
        try {
            const data:GameDto[] = await getAllGames();
            setAllGames(data);
        } catch (error){
            console.log(error);
        }
    }

    // refreshGames();

    const handleOpen = () => {
        onOpen();
    };

    const handleLike = () => {
        refreshGames();
    };

    return (
        <div>
            <div className="listGames">
                <h1>Amazing games</h1>
                {allGames?.map((game) => (
                    <article>
                        <GameEntry key={game.title} game={game} onOpen={handleOpen} onLike={handleLike} />
                    </article>
                ))}
            </div>
        </div>
    );
}

export default GameTable
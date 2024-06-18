import {getAllGames} from "../services/api.ts";
import React, { FunctionComponent, useEffect, useState } from 'react';
import GameDto from "../data/GameDto.ts";
import GameEntry from "./GameEntry.tsx";

const GameTable: FunctionComponent = () => {

    const [allGames, setAllGames ] = useState<GameDto[]>();

    useEffect(() => {
        const fetchAllGames = async () => {
            const data:GameDto[] = await getAllGames();
            setAllGames(data);
        };

        fetchAllGames();

    }, []);


    return (
        <div>
            <div className="listGames">
                <h1>Amazing games</h1>
                {allGames?.map((game) => (
                    <article>
                        <GameEntry key={game.title} game={game}/>
                    </article>
                ))}
            </div>
        </div>
    );
}

export default GameTable
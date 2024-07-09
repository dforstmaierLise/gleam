import {FunctionComponent, useEffect, useState} from 'react';
import GameEntry from "./GameEntry.tsx";
import {Game} from "../data/Game.ts";
import './GameTable.css';
import {GetGamesRequest} from "../data/GetGamesRequest.ts";
import {getGames} from "../services/api.ts";

interface GameTableProps {
    platformFilter: string[];
    prefixFilter: string;
}

const GameTable: FunctionComponent<GameTableProps> = ({platformFilter, prefixFilter}) => {

    const [allGames, setAllGames] = useState<Game[]>();

    useEffect(() => {
        refreshGames();
    }, [platformFilter, prefixFilter]);

    const refreshGames = async () => {
        try {
            const request: GetGamesRequest = {
                platforms: platformFilter,
                prefix: prefixFilter
            }
            const data: Game[] = await getGames(request);
            setAllGames(data);
        } catch (error) {
            console.log(error);
        }
    }

    const handleLike = () => {
        refreshGames();
    };

    return (
        <div>
            <div className="listGames">
                <p>{allGames?.length} entries found.</p>
                {allGames?.map((game) => (
                    <article>
                        <GameEntry key={game.id} game={game} onLike={handleLike}/>
                    </article>
                ))}
            </div>
        </div>
    );
}

export default GameTable
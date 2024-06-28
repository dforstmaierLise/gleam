import {getAllGamesByPlatforms} from "../services/api.ts";
import {FunctionComponent, useEffect, useState} from 'react';
import GameEntry from "./GameEntry.tsx";
import {Game} from "../data/Game.ts";
import './GameTable.css';

interface GameTableProps {
    platformFilter: string[];
}

const GameTable: FunctionComponent<GameTableProps> = ({platformFilter}) => {

    const [allGames, setAllGames] = useState<Game[]>();

    useEffect(() => {
        refreshGames();
    }, [platformFilter]);

    const refreshGames = async () => {
        try {
            const data: Game[] = await getAllGamesByPlatforms(platformFilter);
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
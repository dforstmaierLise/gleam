import {getAllGames} from "../services/api.ts";
import {FunctionComponent, useEffect, useState} from 'react';
import GameEntry from "./GameEntry.tsx";
import {Game} from "../data/Game.ts";
import './GameTable.css';

interface PopupProps {
}

const GameTable: FunctionComponent<PopupProps> = () => {

    const [allGames, setAllGames] = useState<Game[]>();

    useEffect(() => {
        refreshGames();
    }, []);

    const refreshGames = async () => {
        try {
            const data: Game[] = await getAllGames();
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
                <h1>Glamorous games</h1>
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
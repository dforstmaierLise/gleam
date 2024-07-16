import {FunctionComponent} from 'react';
import GameEntry from "./GameEntry.tsx";
import {Game} from "../data/Game.ts";
import './GameTable.css';
import {GetGamesRequest} from "../data/GetGamesRequest.ts";
import {getGames} from "../services/api.ts";
import {useQuery} from "@tanstack/react-query";

interface GameTableProps {
    platformFilter: string[];
    prefixFilter: string;
}

const GameTable: FunctionComponent<GameTableProps> = ({platformFilter, prefixFilter}) => {

    const getGamesRequest: GetGamesRequest = {
        platforms: platformFilter,
        prefix: prefixFilter
    };

    const {data, error, isLoading, refetch} = useQuery<Game[], Error>({
        queryKey: ['getGames', getGamesRequest],
        queryFn: () => getGames(getGamesRequest)
    });

    if (isLoading) return <div>Loading...</div>

    if (error) return <div>Error loading games: {error.message}</div>

    return (
        <div>
            <div className="listGames">
                <p>{data?.length} entries found.</p>
                {data?.map((game) => (
                    <article>
                        <GameEntry key={game.id} game={game} onLike={() => refetch()}/>
                    </article>
                ))}

            </div>
        </div>
    );
}

export default GameTable
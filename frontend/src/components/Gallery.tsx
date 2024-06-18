import {getGame} from "../services/api.ts";
import { FunctionComponent, useEffect, useState } from 'react';
import GameDto from "../data/GameDto.ts";
import Entry from "./Entry.tsx";

const Gallery: FunctionComponent = () => {

    const defaultGame: GameDto = {
        title: 'Default',
        developer: 'Default',
        rating: 0,
        releaseDate: 'Default'
    };

    const [gameData, setGameData ] = useState<GameDto>(defaultGame);

    useEffect(() => {
        const fetchGameData = async () => {
            const data:GameDto = await getGame('Metal Gear Solid');
            setGameData(data);
        };

        fetchGameData();

    }, []);


    return (
        <section>
            <h1>Amazing games</h1>
            <Entry game={gameData}/>
        </section>
    );
}

export default Gallery
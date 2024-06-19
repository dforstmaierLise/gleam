import Entry from './Entry';
import {Game} from "./data/Game.ts";

export default function Gallery(){
    const oneGame = new Game("Unreal Tournament", "Epic", new Date('1990-01-01'), 4);

    return (
        <section>
            <h1>Amazing games</h1>
            <Entry game={oneGame}/>
        </section>
    );
}


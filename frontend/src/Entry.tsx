import { Game } from './data/Game.ts'
import React from "react";

function Card({ children }) {
    return (
        <div className="card">
            {children}
        </div>
    );
}

const Avatar : React.FC<GameComponentProps> = ({game}) => {
    return (
        <div>
            <p>{game.name}</p>
            <img
                className="avatar"
                src="https://i.imgur.com/MK3eW3As.jpg"
                alt={game.name}
            />
            <p>Rating: {game.rating}</p>
            <p>Developer: {game.developer}</p>
            <p>Release Date: {game.releaseDate.toDateString()}</p>
        </div>
    );
}

interface GameComponentProps {
    game: Game;
}

const Entry : React.FC<GameComponentProps> = ({game}) => {
    return (
        <Card>
            <Avatar game={game}/>
        </Card>
    );
}

export default Entry
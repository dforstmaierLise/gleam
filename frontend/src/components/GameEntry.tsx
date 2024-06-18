import React from "react";
import GameDto from "../data/GameDto.ts";

const GameEntry : React.FC<{game:GameDto}> = ({game}) => {

    if( !game ){
        return null;
    }
    const transformGameName = (gameName: string): string => {
        return gameName.toLowerCase().replace(/ /g, '_');
    };

    const transformedName = transformGameName(game.title);
    const logoUrl = `/images/logo-${transformedName}.webp`;

    return (
        <div className="gameCard">
            <img src={logoUrl} />
            <div className="containerDetails">
                <h4 className="detailItem"><b>{game.title}</b></h4>
                <p className="detailItem">Release-Date: {game.releaseDate}</p>
                <p className="detailItem">Developer: {game.developer}</p>
                <p className="detailItem">Ratings: <b>Sehr positiv</b> ({game.ratings?.length ?? 0} Ratings)</p>
                <button className="detailItem">Add rating</button>
            </div>
        </div>
    );
}

export default GameEntry
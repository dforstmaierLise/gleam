import React from "react";
import {addDislike, addLike} from "../services/api.ts";
import {Game} from "../data/Game.ts";

interface GameEntryProps{
    game: Game;
    onOpen: () => void;
    onLike: () => void;
}


const GameEntry : React.FC<GameEntryProps> = ({game, onOpen, onLike}) => {

    if( !game ){
        return null;
    }
    const transformGameName = (gameName: string): string => {
        return gameName.toLowerCase().replace(/ /g, '_');
    };

    const handleOpen = () => {
        onOpen();
    };

    const handleAddLike = async (id:string) => {
        try {
            await addLike(id);
            onLike();
        } catch(error){
            console.error(error);
        }
    }

    const handleAddDislike = async (id:string) => {
        try {
            await addDislike(id);
            onLike();
        } catch(error){
            console.error(error);
        }
    }

    const transformedName = transformGameName(game.title);
    const logoUrl = `/images/logo-${transformedName}.webp`;

    return (
        <div className="gameCard">
            <img src={logoUrl}  alt={"game logo"}/>
            <div className="containerDetails">
                <h4 className="detailItem"><b>{ game.title }</b></h4>
                <p className="detailItem">Release-Date: { game.releaseDate }</p>
                <p className="detailItem">Developer: { game.developer }</p>
                <p className="detailItem">Likes: { game.likes }</p>
                <p className="detailItem">Dislikes: { game.dislikes }</p>
                <p className="detailItem">Ratings: { game.reviewIds?.length ?? 0 } Ratings</p>
                <div className="detailItem buttonList">
                    <button onClick={ () => handleAddLike(game.id) }>Like</button>
                    <button onClick={ () => handleAddDislike(game.id) }>Dislike</button>
                    <button disabled={true} onClick={ handleOpen }>Add review</button>
                </div>
            </div>
        </div>
    );
}

export default GameEntry
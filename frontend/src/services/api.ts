import axios from 'axios';
import Game from '../data/GameDto.ts';

const API_URL = 'http://localhost:8080';

export const getGame = async ( title : string ) => {
    const response = await axios.get<Game>(`${API_URL}/api/games/getGame?title=${title}`);
    return response.data;
}
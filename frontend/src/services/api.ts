import axios from 'axios';
import Game from '../data/Game.ts';

const API_URL = 'http://localhost:8080';

export const getAllGames = async () => {
    const response = await axios.get<Game[]>(`${API_URL}/api/games/getAllGames`);
    return response.data;
}

export const addLike = async (id : string ) => {
    const response = await axios.post<Game>(`${API_URL}/api/games/addLike?id=${id}`);
    return response.data;
}

export const addDislike = async (id : string ) => {
    const response = await axios.post<Game>(`${API_URL}/api/games/addDislike?id=${id}`);
    return response.data;
}
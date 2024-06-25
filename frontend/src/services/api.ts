import axios from 'axios';
import {Game} from '../data/Game.ts';
import {RegisterUserRequest} from "../data/RegisterUserRequest.ts";
import {UserDto} from "../data/UserDto.ts";

const API_URL = 'http://localhost:8080';

export const getAllGames = async () => {
    const response = await axios.get<Game[]>(`${API_URL}/api/getAllGames`);
    return response.data;
}

export const addLike = async (id: string) => {
    const response = await axios.post<Game>(`${API_URL}/api/games/addLike?id=${id}`);
    return response.data;
}

export const addDislike = async (id: string) => {
    const response = await axios.post<Game>(`${API_URL}/api/games/addDislike?id=${id}`);
    return response.data;
}

export const getOrCreateUser = async (request: RegisterUserRequest) => {
    const response = await axios.post<UserDto>(`${API_URL}/api/getOrCreateUser`, request);
    return response.data;
}
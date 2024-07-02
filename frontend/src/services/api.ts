import axios from 'axios';
import {Game} from '../data/Game.ts';
import {RegisterUserRequest} from "../data/RegisterUserRequest.ts";
import {UserDto} from "../data/UserDto.ts";
import {GetGamesRequest} from "../data/GetGamesRequest.ts";
import {GameDetails} from "../data/GameDetails.ts";

const API_URL = 'http://localhost:8080';

export const getGames = async (request: GetGamesRequest) => {
    const response = await axios.post<Game[]>(`${API_URL}/api/getGames`, request);
    return response.data;
}

export const addLike = async (id: string) => {
    const response = await axios.post<Game>(`${API_URL}/api/addLike?id=${id}`);
    return response.data;
}

export const addDislike = async (id: string) => {
    const response = await axios.post<Game>(`${API_URL}/api/addDislike?id=${id}`);
    return response.data;
}

export const getOrCreateUser = async (request: RegisterUserRequest) => {
    const response = await axios.post<UserDto>(`${API_URL}/api/getOrCreateUser`, request);
    return response.data;
}

export const getGameTitlesWithPrefix = async (request: GetGamesRequest) => {
    const response = await axios.post<string[]>(`${API_URL}/api/getGameTitlesWithPrefix`, request);
    return response.data;
}

export const getGameDetails = async (id: string) => {
    const response = await axios.get<GameDetails>(`${API_URL}/api/getGameDetails?id=${id}`);
    return response.data;
}
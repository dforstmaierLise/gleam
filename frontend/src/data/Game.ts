export type Game = {
    id: string;
    igdbId: string;
    title: string;
    releaseDate: string;
    developer: string;
    reviewIds: string[];
    likes: number;
    dislikes: number;
    platforms: string[];
    coverUrl: string;
}
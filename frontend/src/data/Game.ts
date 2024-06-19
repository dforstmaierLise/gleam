export class Game {
    name: string;
    releaseDate: Date;
    developer: string;
    rating: number;

    constructor(name: string, developer: string, releaseDate: Date, rating: number) {
        this.name = name;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }
}
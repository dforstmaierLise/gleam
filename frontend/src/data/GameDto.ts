import RatingDto from "./RatingDto.ts";

export default interface GameDto {
    title: string;
    releaseDate: string;
    developer: string;
    ratings: RatingDto[];
}
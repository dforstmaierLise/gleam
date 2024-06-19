package com.gleam.backend.controller;

import com.gleam.backend.dto.GameDto;
import com.gleam.backend.dto.RatingDto;
import com.gleam.backend.mapper.GameMapper;
import com.gleam.backend.mapper.RatingMapper;
import com.gleam.backend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private RatingMapper ratingMapper;

    @GetMapping("/getAllGames")
    public List<GameDto> getAllGames() {
        var games = gameService.getAllGames();
        return gameMapper.toDtoList(games);
    }

    @GetMapping("/getGamesCount")
    public long getGamesCount(){
        return gameService.getGamesCount();
    }

    @GetMapping("/getGamesWithPrefix")
    public List<GameDto> getGamesWithPrefix(@RequestParam(value = "prefix") String prefix) {
        var games = gameService.getGamesWithPrefix(prefix);
        return gameMapper.toDtoList(games);
    }

    @GetMapping("/getGameWithPrefix")
    public GameDto getGameWithPrefix(@RequestParam(value = "prefix") String prefix) {
        var game = gameService.getGameWithPrefix(prefix);
        return gameMapper.toDto(game);
    }

    @PostMapping("/addRating")
    public void addRating(@RequestBody RatingDto ratingDto) {
        var game = gameService.getGame(ratingDto.title());
        if (game != null) {
            var rating = ratingMapper.toEntity(ratingDto);
            gameService.addRating(game, rating);
        }
    }

    @GetMapping("/displayGameDetails")
    public void displayGameDetails(@RequestParam(value = "title") String title) {
        var game = gameService.getGame(title);
        gameService.displayGameDetails(game);
    }
}

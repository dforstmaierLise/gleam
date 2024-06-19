package com.gleam.backend.controller;

import com.gleam.backend.dto.GameDto;
import com.gleam.backend.dto.ReviewDto;
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

    @GetMapping("/getGame")
    public GameDto getGame(@RequestParam(value="id") String id) {
        var game = gameService.getGame(id);
        return gameMapper.toDto(game);
    }

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

    @PostMapping("/addReview")
    public void addReview(@RequestBody ReviewDto reviewDto) {
        var game = gameService.getGame(reviewDto.title());
        if (game != null) {
            var rating = ratingMapper.toEntity(reviewDto);
            gameService.addRating(game, rating);
        }
    }

    @PostMapping("/addLike")
    public GameDto addLike(@RequestParam(value = "id") String id) {
        var game = gameService.getGame(id);
        gameService.addLike(game);
        return gameMapper.toDto(game);
    }

    @PostMapping("/addDislike")
    public GameDto addDislike(@RequestParam(value = "id") String id) {
        var game = gameService.getGame(id);
        gameService.addDislike(game);
        return gameMapper.toDto(game);
    }

    @GetMapping("/displayGameDetails")
    public void displayGameDetails(@RequestParam(value = "id") String id) {
        var game = gameService.getGame(id);
        gameService.displayGameDetails(game);
    }
}

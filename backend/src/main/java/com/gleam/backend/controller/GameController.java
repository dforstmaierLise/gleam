package com.gleam.backend.controller;

import com.gleam.backend.dto.GameDto;
import com.gleam.backend.dto.ReviewDto;
import com.gleam.backend.mapper.GameMapper;
import com.gleam.backend.mapper.RatingMapper;
import com.gleam.backend.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    private final GameMapper gameMapper;

    private final RatingMapper ratingMapper;

    public GameController(GameService gameService, GameMapper gameMapper, RatingMapper ratingMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
        this.ratingMapper = ratingMapper;
    }

    @GetMapping("/getGame")
    public GameDto getGame(@RequestParam(value="id") String id) {
        var optionalGame = gameService.getGame(id);
        return optionalGame.map(gameMapper::toDto).orElse(null);
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
        var optionalGame = gameService.getGame(reviewDto.title());
        if( optionalGame.isEmpty() )
        {
            return;
        }

        var rating = ratingMapper.toEntity(reviewDto);
        gameService.addRating(optionalGame.get(), rating);
    }

    @PostMapping("/addLike")
    public GameDto addLike(@RequestParam(value = "id") String id) {
        var game = gameService.getGame(id);
        if( game.isEmpty() )
        {
            return null;
        }

        gameService.addLike(game.get());
        return gameMapper.toDto(game.get());
    }

    @PostMapping("/addDislike")
    public GameDto addDislike(@RequestParam(value = "id") String id) {
        var game = gameService.getGame(id);
        if( game.isEmpty() )
        {
            return null;
        }

        gameService.addDislike(game.get());
        return gameMapper.toDto(game.get());
    }
}

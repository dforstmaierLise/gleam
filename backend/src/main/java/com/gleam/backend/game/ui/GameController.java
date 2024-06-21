package com.gleam.backend.game.ui;

import com.gleam.backend.game.application.GameService;
import com.gleam.backend.game.domain.Game;
import com.gleam.backend.game.mapper.GameMapper;
import com.gleam.backend.game.ui.dto.GameDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    private final GameMapper gameMapper;

    public GameController(GameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @GetMapping("/getGame")
    public ResponseEntity<GameDto> getGame(@RequestParam(value = "id") String id) {
        return createReponseEntity(gameService.getGame(id));
    }

    private ResponseEntity<GameDto> createReponseEntity(Optional<Game> optionalGame) {
        return optionalGame
                .map(gameMapper::toDto)
                .map(gameDto -> new ResponseEntity<>(gameDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/getAllGames")
    public ResponseEntity<List<GameDto>> getAllGames() {
        var games = gameService.getAllGames();
        var gamesDto = gameMapper.toDtoList(games);
        return new ResponseEntity<>(gamesDto, HttpStatus.OK);
    }

    @GetMapping("/getGamesCount")
    public ResponseEntity<Long> getGamesCount() {
        var count = gameService.getGamesCount();
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    @GetMapping("/getGamesWithPrefix")
    public ResponseEntity<List<GameDto>> getGamesWithPrefix(@RequestParam(value = "prefix") String prefix) {
        var games = gameService.getGamesWithPrefix(prefix);
        var gamesDto = gameMapper.toDtoList(games);
        return new ResponseEntity<>(gamesDto, HttpStatus.OK);
    }

    @GetMapping("/getGameWithPrefix")
    public ResponseEntity<GameDto> getGameWithPrefix(@RequestParam(value = "prefix") String prefix) {
        return createReponseEntity(gameService.getGameWithPrefix(prefix));
    }

    @PostMapping("/addLike")
    public ResponseEntity<GameDto> addLike(@RequestParam(value = "id") String id) {
        var game = gameService.getGame(id);
        if (game.isEmpty()) {
            return null;
        }

        gameService.addLike(game.get());
        return createReponseEntity(game);
    }

    @PostMapping("/addDislike")
    public ResponseEntity<GameDto> addDislike(@RequestParam(value = "id") String id) {
        var game = gameService.getGame(id);
        if (game.isEmpty()) {
            return null;
        }

        gameService.addDislike(game.get());
        return createReponseEntity(game);
    }
}

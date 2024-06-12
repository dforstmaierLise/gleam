package com.gleam.backend.controller;

import com.gleam.backend.model.Game;
import com.gleam.backend.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/getAllGames")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/getGamesCount")
    public long getGamesCount(){
        return getAllGames().size();
    }

    @GetMapping("/getGamesWithPrefix")
    public List<Game> getGamesWithPrefix(@RequestParam(value = "prefix") String prefix)
    {
        return getAllGames().stream()
                .filter(game -> game.getTitle().toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/getGameWithPrefix")
    public Game getGameWithPrefix(@RequestParam(value = "prefix") String prefix)
    {
        return getGamesWithPrefix(prefix).stream()
                .min(Comparator.comparing(Game::getTitle, String.CASE_INSENSITIVE_ORDER))
                .orElse(null);
    }
}

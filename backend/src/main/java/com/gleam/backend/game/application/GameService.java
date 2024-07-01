package com.gleam.backend.game.application;

import com.gleam.backend.game.domain.Game;
import com.gleam.backend.game.infrastructure.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGame(String id) {
        return getAllGames().stream()
                .filter(game -> game.getId().equals(id))
                .findFirst();
    }
    
    public void addLike(Game game) {
        game.setLikes(game.getLikes() + 1);
        gameRepository.save(game);
    }

    public void addDislike(Game game) {
        game.setDislikes(game.getDislikes() + 1);
        gameRepository.save(game);
    }

    public List<Game> getGames(String[] platforms, String prefix) {
        var platformsList = Arrays.stream(platforms).toList();
        return gameRepository.findAll().stream()
                .filter(game -> game.getPlatforms().stream().anyMatch(platformsList::contains))
                .filter(game -> game.getTitle().toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<String> getGameTitlesByPrefix(String[] platforms, String prefix) {
        var games = getGames(platforms, prefix);
        return games.stream().map(Game::getTitle).collect(Collectors.toList());
    }
}

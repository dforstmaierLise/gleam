package com.gleam.backend.game.application;

import com.gleam.backend.game.domain.Game;
import com.gleam.backend.game.domain.GameDetails;
import com.gleam.backend.game.infrastructure.GameDetailsRepository;
import com.gleam.backend.game.infrastructure.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameDetailsRepository gameDetailsRepository;

    public GameService(GameRepository gameRepository, GameDetailsRepository gameDetailsRepository) {
        this.gameRepository = gameRepository;
        this.gameDetailsRepository = gameDetailsRepository;
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
        var platformsList = Arrays.asList(platforms);
        return gameRepository.findByPlatformsAndTitle(platformsList, prefix);
    }

    public List<String> getGameTitlesByPrefix(String[] platforms, String prefix) {
        var games = getGames(platforms, prefix);
        return games.stream().map(Game::getTitle).collect(Collectors.toList());
    }

    public Optional<GameDetails> getGameDetails(String gameId) {
        return gameDetailsRepository.findByGameId(gameId);
    }

    public Optional<Game> getGameByIgdbId(String igdbId) {
        return gameRepository.findAll().stream()
                .filter(g -> g.getIgdbId().equals(igdbId))
                .findFirst();
    }

    public void updateGameByIgdb(String igdbId, String coverUrl) {

        var gameOptional = getGameByIgdbId(igdbId);
        if (gameOptional.isEmpty()) {
            return;
        }

        Game gleamGame = gameOptional.get();
        gleamGame.setIgdbId(igdbId);
        gleamGame.setCoverUrl(coverUrl);
        gameRepository.save(gleamGame);

        System.out.println("Updated game: " + gleamGame.getTitle());
    }

    public void addOrUpdateGameDetailsByIgdb(String igdbId, String description, String trailerUrl) {

        var gameOptional = getGameByIgdbId(igdbId);
        if (gameOptional.isEmpty()) {
            return;
        }

        var gameId = gameOptional.get().getId();
        var gameDetailsOptional = gameDetailsRepository.findByGameId(gameId);

        if (gameDetailsOptional.isPresent()) {
            var gameDetails = gameDetailsOptional.get();
            gameDetails.setDescription(description);
            gameDetails.setTrailerUrl(trailerUrl);
            gameDetailsRepository.save(gameDetails);
            return;
        }

        var gameDetails = new GameDetails(gameId, description, trailerUrl);
        gameDetailsRepository.save(gameDetails);
    }
}

package com.gleam.backend.service;

import com.gleam.backend.model.Game;
import com.gleam.backend.model.Review;
import com.gleam.backend.repository.GameRepository;
import com.gleam.backend.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;

    private final RatingRepository ratingRepository;

    public GameService(GameRepository gameRepository, RatingRepository ratingRepository) {
        this.gameRepository = gameRepository;
        this.ratingRepository = ratingRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public long getGamesCount(){
        return gameRepository.count();
    }

    public List<Game> getGamesWithPrefix(String prefix)
    {
        return getAllGames().stream()
                .filter(game -> game.getTitle().toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Optional<Game> getGameWithPrefix(String prefix)
    {
        return getGamesWithPrefix(prefix).stream()
                .min(Comparator.comparing(Game::getTitle, String.CASE_INSENSITIVE_ORDER));
    }

    public Optional<Game> getGame(String id) {
        return getAllGames().stream()
                .filter(game -> game.getId().equals(id))
                .findFirst();
    }

    public void addRating(Game game, Review review) {
        ratingRepository.save(review);
        game.getReviewIds().add(review.getId());
        gameRepository.save(game);
    }

    public void addLike(Game game)
    {
        game.setLikes( game.getLikes() + 1);
        gameRepository.save(game);
    }

    public void addDislike(Game game)
    {
        game.setDislikes( game.getDislikes() + 1);
        gameRepository.save(game);
    }
}

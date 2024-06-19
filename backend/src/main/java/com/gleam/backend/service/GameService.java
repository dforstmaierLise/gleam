package com.gleam.backend.service;

import com.gleam.backend.model.Game;
import com.gleam.backend.model.Rating;
import com.gleam.backend.repository.GameRepository;
import com.gleam.backend.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public long getGamesCount(){
        return getAllGames().size();
    }

    public List<Game> getGamesWithPrefix(String prefix)
    {
        return getAllGames().stream()
                .filter(game -> game.getTitle().toLowerCase().startsWith(prefix.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Game getGameWithPrefix( String prefix)
    {
        return getGamesWithPrefix(prefix).stream()
                .min(Comparator.comparing(Game::getTitle, String.CASE_INSENSITIVE_ORDER))
                .orElse(null);
    }

    public Game getGame(String title) {
        return getAllGames().stream()
                .filter(game -> game.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }

    public void addRating(Game game, Rating rating) {
        ratingRepository.save(rating);
        if (game.getRatingIds() == null) {
            game.setRatingIds(new ArrayList<>());
        }

        game.getRatingIds().add(rating.getId());
        gameRepository.save(game);
    }

    public void displayGameDetails(Game game) {
        if (game == null) {
            return;
        }

        System.out.println("Game Title: " + game.getTitle());

        var ratingIds = game.getRatingIds();
        if (!ratingIds.isEmpty()) {
            System.out.println("Ratings:");
            var ratings = ratingRepository.findAllById(ratingIds);
            for (var rating : ratings) {
                System.out.println("- " + rating.getRating() + " stars. Comment: " + rating.getComment());
            }
        } else {
            System.out.println("No ratings found for this game.");
        }
    }
}

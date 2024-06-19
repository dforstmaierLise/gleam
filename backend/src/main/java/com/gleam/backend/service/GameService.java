package com.gleam.backend.service;

import com.gleam.backend.model.Game;
import com.gleam.backend.model.Review;
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

    public Game getGame(String id) {
        return getAllGames().stream()
                .filter(game -> game.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addRating(Game game, Review review) {
        ratingRepository.save(review);
        if (game.getReviewIds() == null) {
            game.setReviewIds(new ArrayList<>());
        }

        game.getReviewIds().add(review.getId());
        gameRepository.save(game);
    }


    public List<Review> getRatings(Game game)
    {
        if( game == null) {
            return null;
        }

        var ratingIds = game.getReviewIds();
        if (!ratingIds.isEmpty()) {
            return ratingRepository.findAllById(ratingIds);
        }

        return null;
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

    public void displayGameDetails(Game game) {
        if (game == null) {
            return;
        }

        System.out.println("Game Title: " + game.getTitle());

        var ratings = getRatings(game);
        if( ratings != null )
        {
            for (var rating : ratings) {
                System.out.println("Comment: " + rating.getComment());
            }
        }
    }
}

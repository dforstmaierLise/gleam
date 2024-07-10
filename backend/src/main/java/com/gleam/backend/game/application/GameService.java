package com.gleam.backend.game.application;

import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.exceptions.RequestException;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.ProtoRequestKt;
import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.ImageBuilderKt;
import com.api.igdb.utils.ImageSize;
import com.api.igdb.utils.ImageType;
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

    public GameDetails addOrUpdateGameDetails(String gameId, String description, String trailerUrl) {

        var gameDetailsOptional = gameDetailsRepository.findByGameId(gameId);

        if (gameDetailsOptional.isPresent()) {
            var gameDetails = gameDetailsOptional.get();
            gameDetails.setDescription(description);
            gameDetails.setTrailerUrl(trailerUrl);
            gameDetailsRepository.save(gameDetails);
            return gameDetails;
        }

        var gameDetails = new GameDetails(gameId, description, trailerUrl);
        gameDetailsRepository.save(gameDetails);
        return gameDetails;
    }

    private void fetchDataFromIgdb(String igdbId) throws RequestException {

        // Retrieve game data from igdb.com.
        var apicalypse = new APICalypse().fields("*, cover.*, videos.*").where("id = " + igdbId);
        var igdbGames = ProtoRequestKt.games(IGDBWrapper.INSTANCE, apicalypse);
        var igdbGameOptional = igdbGames.stream().findFirst();
        if (igdbGameOptional.isEmpty()) {
            return;
        }
        var igdbGame = igdbGameOptional.get();

        // Retrieve game data from Gleam database.
        var gleamGameOptional = gameRepository.findAll().stream()
                .filter(g -> g.getIgdbId().equals(igdbId))
                .findFirst();

        if (gleamGameOptional.isEmpty()) {
            return;
        }

        // Update Gleam game & details.
        Game gleamGame = gleamGameOptional.get();
        gleamGame.setIgdbId(igdbId);
        gleamGame.setCoverUrl(getCoverUrl(igdbGame));
        gameRepository.save(gleamGame);
        addOrUpdateGameDetails(gleamGame.getId(), igdbGame.getSummary(), getTrailerUrl(igdbGame));
    }

    public void updateAllEntries(String secret, String clientId) {
        var token = TwitchAuthenticator.INSTANCE.requestTwitchToken(clientId, secret);

        var wrapper = IGDBWrapper.INSTANCE;
        assert token != null;
        wrapper.setCredentials(clientId, token.getAccess_token());

        var allGames = gameRepository.findAll();
        for (Game game : allGames) {
            try {
                fetchDataFromIgdb(game.getIgdbId());
            } catch (RequestException error) {
                System.out.println(error.getMessage());
            }
        }
    }

    private String getCoverUrl(proto.Game igdbGame) {
        return ImageBuilderKt.imageBuilder(igdbGame.getCover().getImageId(), ImageSize.COVER_BIG, ImageType.PNG);
    }

    private String getTrailerUrl(proto.Game igdbGame) {

        if (igdbGame.getVideosCount() > 0) {
            var videoId = igdbGame.getVideos(0).getVideoId();
            return String.format("https://www.youtube.com/watch?v=%s", videoId);
        }

        return "";
    }
}

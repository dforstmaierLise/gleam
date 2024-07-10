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
import org.springframework.stereotype.Service;

@Service
public class IgdbService {

    private final GameService gameService;

    public IgdbService(GameService gameService) {
        this.gameService = gameService;
    }

    private void fetchAndUpdateDataFromIgdb(String igdbId) throws RequestException {

        // Retrieve game data from igdb.com.
        var apicalypse = new APICalypse().fields("*, cover.*, videos.*").where("id = " + igdbId);
        var igdbGames = ProtoRequestKt.games(IGDBWrapper.INSTANCE, apicalypse);
        var igdbGameOptional = igdbGames.stream().findFirst();
        if (igdbGameOptional.isEmpty()) {
            return;
        }
        var igdbGame = igdbGameOptional.get();

        // Retrieve game data from Gleam database.
        gameService.updateGameByIgdb(igdbId, getCoverUrl(igdbGame));
        gameService.addOrUpdateGameDetailsByIgdb(igdbId, igdbGame.getSummary(), getTrailerUrl(igdbGame));
    }

    public void updateAllEntries(String secret, String clientId) {
        var token = TwitchAuthenticator.INSTANCE.requestTwitchToken(clientId, secret);

        var wrapper = IGDBWrapper.INSTANCE;
        assert token != null;
        wrapper.setCredentials(clientId, token.getAccess_token());

        var allGames = gameService.getAllGames();
        for (Game game : allGames) {
            try {
                fetchAndUpdateDataFromIgdb(game.getIgdbId());
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

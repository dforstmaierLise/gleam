package com.gleam.backend.game.application;

import com.gleam.backend.common.event.*;
import com.gleam.backend.game.mapper.GameDetailsMapper;
import com.gleam.backend.game.mapper.GameMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GameEventListener extends com.gleam.backend.common.event.EventListener {
    private final GameService gameService;
    private final GameMapper gameMapper;
    private final GameDetailsMapper gameDetailsMapper;
    private final IgdbService igdbService;

    public GameEventListener(GameService gameService, GameMapper gameMapper, GameDetailsMapper gameDetailsMapper, IgdbService igdbService) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
        this.gameDetailsMapper = gameDetailsMapper;
        this.igdbService = igdbService;
    }

    @EventListener
    public void handleGetAllGamesEvent(GetAllGamesEvent event) {
        var games = gameService.getAllGames();
        var gamesDto = gameMapper.toDtoList(games);
        event.getFuture().complete(gamesDto);
    }

    @EventListener
    public void handleAddLikeEvent(AddLikeEvent event) {
        var gameOptional = gameService.getGame(event.getGameId());
        if (gameOptional.isEmpty()) {
            handleIdNotFound(event.getFuture());
            return;
        }

        gameService.addLike(gameOptional.get());

        var gameDto = gameMapper.toDto(gameOptional.get());
        event.getFuture().complete(gameDto);
    }

    @EventListener
    public void handleAddDislikeEvent(AddDislikeEvent event) {
        var gameOptional = gameService.getGame(event.getGameId());
        if (gameOptional.isEmpty()) {
            handleIdNotFound(event.getFuture());
            return;
        }

        gameService.addDislike(gameOptional.get());

        var gameDto = gameMapper.toDto(gameOptional.get());
        event.getFuture().complete(gameDto);
    }


    @EventListener
    public void handleGetGameTitlesByPrefixEvent(GetGameTitlesByPrefixEvent event) {
        var games = gameService.getGameTitlesByPrefix(event.getPlatforms(), event.getPrefix());
        event.getFuture().complete(games.toArray(new String[0]));
    }

    @EventListener
    public void handleGetGamesEvent(GetGamesEvent event) {
        var games = gameService.getGames(event.getPlatforms(), event.getPrefix());
        var gamesDto = gameMapper.toDtoList(games);
        event.getFuture().complete(gamesDto);
    }

    @EventListener
    public void handleGetGameDetailsEvent(GetGameDetailsEvent event) {
        var details = gameService.getGameDetails(event.getGameId());
        if (details.isEmpty()) {
            handleIdNotFound(event.getFuture());
            return;
        }

        var gameDetailsDto = gameDetailsMapper.toDto(details.get());
        event.getFuture().complete(gameDetailsDto);
    }

    @EventListener
    public void updateAllEntriesFromIgdbEvent(UpdateAllEntriesFromIgdbEvent event) {
        igdbService.updateAllEntries(event.getSecret(), event.getClientId());
        event.getFuture().complete(true);
    }
}
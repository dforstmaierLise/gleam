package com.gleam.backend.game.application;

import com.gleam.backend.common.event.*;
import com.gleam.backend.game.mapper.GameMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class GameEventListener {
    private final GameService gameService;
    private final GameMapper gameMapper;

    public GameEventListener(GameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
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
}

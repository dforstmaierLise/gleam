package com.gleam.backend.game.application;

import com.gleam.backend.common.event.GetAllGamesEvent;
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
}

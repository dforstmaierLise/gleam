package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.GameDetailsDto;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
public class AddGameDetailsEvent implements Event {
    private String gameId;
    private String description;
    private String trailerUrl;
    private CompletableFuture<GameDetailsDto> future;

    public AddGameDetailsEvent(String gameId, String description, String trailerUrl, final CompletableFuture<GameDetailsDto> future) {
        this.gameId = gameId;
        this.future = future;
        this.description = description;
        this.trailerUrl = trailerUrl;
    }
}

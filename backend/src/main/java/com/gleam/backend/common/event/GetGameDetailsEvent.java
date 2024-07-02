package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.GameDetailsDto;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
public class GetGameDetailsEvent implements Event {
    private String gameId;
    private CompletableFuture<GameDetailsDto> future;

    public GetGameDetailsEvent(String gameId, final CompletableFuture<GameDetailsDto> future) {
        this.gameId = gameId;
        this.future = future;
    }
}

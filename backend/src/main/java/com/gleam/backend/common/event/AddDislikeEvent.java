package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.GameDto;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
public class AddDislikeEvent {
    private String gameId;
    private CompletableFuture<GameDto> future;

    public AddDislikeEvent(String gameId, CompletableFuture<GameDto> future) {
        this.gameId = gameId;
        this.future = future;
    }
}

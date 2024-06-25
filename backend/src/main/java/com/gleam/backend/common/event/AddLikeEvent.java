package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.GameDto;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
public class AddLikeEvent implements Event {
    private String gameId;
    private CompletableFuture<GameDto> future;

    public AddLikeEvent(String gameId, CompletableFuture<GameDto> future) {
        this.gameId = gameId;
        this.future = future;
    }
}

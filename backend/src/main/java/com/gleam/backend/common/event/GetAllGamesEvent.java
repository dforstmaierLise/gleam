package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.GameDto;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Data
public class GetAllGamesEvent implements Event {
    private CompletableFuture<List<GameDto>> future;

    public GetAllGamesEvent(final CompletableFuture<List<GameDto>> future) {
        this.future = future;
    }
}

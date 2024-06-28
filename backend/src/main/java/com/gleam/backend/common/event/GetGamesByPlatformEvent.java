package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.GameDto;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Data
public class GetGamesByPlatformEvent implements Event {
    private String[] platforms;
    private CompletableFuture<List<GameDto>> future;

    public GetGamesByPlatformEvent(String[] platforms, final CompletableFuture<List<GameDto>> future) {
        this.platforms = platforms;
        this.future = future;
    }
}

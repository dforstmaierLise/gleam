package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.GameDto;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Data
public class GetGamesEvent implements Event {
    private String[] platforms;
    private String prefix;
    private CompletableFuture<List<GameDto>> future;

    public GetGamesEvent(String[] platforms, String prefix, final CompletableFuture<List<GameDto>> future) {
        this.platforms = platforms;
        this.prefix = prefix;
        this.future = future;
    }
}

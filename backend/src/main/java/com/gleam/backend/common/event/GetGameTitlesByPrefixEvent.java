package com.gleam.backend.common.event;

import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
public class GetGameTitlesByPrefixEvent implements Event {
    private String[] platforms;
    private String prefix;
    private CompletableFuture<String[]> future;

    public GetGameTitlesByPrefixEvent(String[] platforms, String prefix, final CompletableFuture<String[]> future) {
        this.platforms = platforms;
        this.prefix = prefix;
        this.future = future;
    }
}

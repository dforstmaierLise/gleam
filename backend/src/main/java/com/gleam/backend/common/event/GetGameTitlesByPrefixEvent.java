package com.gleam.backend.common.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
@AllArgsConstructor
public class GetGameTitlesByPrefixEvent implements Event {
    private String[] platforms;
    private String prefix;
    private CompletableFuture<String[]> future;
}

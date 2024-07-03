package com.gleam.backend.common.event;

import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
public class UpdateAllEntriesFromIgdbEvent implements Event {
    private String secret;
    private String clientId;
    private CompletableFuture<Boolean> future;

    public UpdateAllEntriesFromIgdbEvent(String secret, String clientId, CompletableFuture<Boolean> future) {
        this.secret = secret;
        this.clientId = clientId;
        this.future = future;
    }
}

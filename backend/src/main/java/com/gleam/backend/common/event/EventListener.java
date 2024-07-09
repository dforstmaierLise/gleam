package com.gleam.backend.common.event;

import com.gleam.backend.common.exception.IdNotFoundException;

import java.util.concurrent.CompletableFuture;

public abstract class EventListener {

    protected void handleIdNotFound(CompletableFuture<?> future) {
        future.completeExceptionally(new IdNotFoundException());
    }
}

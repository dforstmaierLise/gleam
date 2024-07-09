package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.GameDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
@AllArgsConstructor
public class AddLikeEvent implements Event {
    private String gameId;
    private CompletableFuture<GameDto> future;
}

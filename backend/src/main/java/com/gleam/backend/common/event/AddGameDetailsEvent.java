package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.GameDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
@AllArgsConstructor
public class AddGameDetailsEvent implements Event {
    private String gameId;
    private String description;
    private String trailerUrl;
    private CompletableFuture<GameDetailsDto> future;
}

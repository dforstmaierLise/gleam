package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.GameDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Data
@AllArgsConstructor
public class GetAllGamesEvent implements Event {
    private CompletableFuture<List<GameDto>> future;
}

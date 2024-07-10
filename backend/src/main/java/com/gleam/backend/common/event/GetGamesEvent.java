package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.GameDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Data
@AllArgsConstructor
public class GetGamesEvent implements Event {
    private String[] platforms;
    private String prefix;
    private CompletableFuture<List<GameDto>> future;
}

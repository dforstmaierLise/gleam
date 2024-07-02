package com.gleam.backend.game.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class GameDetails {
    @Id
    private String id;
    private String gameId;
    private String description;
    private String trailerUrl;

    public GameDetails(final String gameId, final String description, final String trailerUrl) {
        this.gameId = gameId;
        this.description = description;
        this.trailerUrl = trailerUrl;
    }
}

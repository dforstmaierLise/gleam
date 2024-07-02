package com.gleam.backend.common.dto;

public record AddGameDetailsRequest(
        String gameId,
        String description,
        String trailerUrl) {
}

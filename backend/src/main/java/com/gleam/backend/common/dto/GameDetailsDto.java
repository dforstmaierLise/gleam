package com.gleam.backend.common.dto;

import org.springframework.data.annotation.Id;

public record GameDetailsDto(
        @Id String gameId,
        String description,
        String trailerUrl) {
}

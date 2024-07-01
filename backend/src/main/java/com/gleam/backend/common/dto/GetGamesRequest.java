package com.gleam.backend.common.dto;

public record GetGamesRequest(
        String[] platforms,
        String prefix) {
}

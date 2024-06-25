package com.gleam.backend.common.dto;

import org.springframework.data.annotation.Id;

public record UserDto(
        @Id
        String id,
        String username) {
}

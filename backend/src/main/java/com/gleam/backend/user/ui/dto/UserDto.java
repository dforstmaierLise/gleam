package com.gleam.backend.user.ui.dto;

import org.springframework.data.annotation.Id;

public record UserDto(
        @Id
        String id,
        String username) {
}

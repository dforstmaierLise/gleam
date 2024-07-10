package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.RegisterUserRequest;
import com.gleam.backend.common.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
@AllArgsConstructor
public class GetOrCreateUserEvent implements Event {
    private RegisterUserRequest request;
    private CompletableFuture<UserDto> future;
}

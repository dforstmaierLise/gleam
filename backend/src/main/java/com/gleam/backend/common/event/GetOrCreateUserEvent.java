package com.gleam.backend.common.event;

import com.gleam.backend.common.dto.RegisterUserRequest;
import com.gleam.backend.common.dto.UserDto;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

@Data
public class GetOrCreateUserEvent {
    private RegisterUserRequest request;
    private CompletableFuture<UserDto> future;

    public GetOrCreateUserEvent(RegisterUserRequest request, CompletableFuture<UserDto> future) {
        this.request = request;
        this.future = future;
    }
}

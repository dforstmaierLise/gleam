package com.gleam.backend.api.ui;

import com.gleam.backend.common.dto.RegisterUserRequest;
import com.gleam.backend.common.dto.UserDto;
import com.gleam.backend.common.event.GetOrCreateUserEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/api")
public class ApiController {
    private final ApplicationEventPublisher eventPublisher;

    public ApiController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/getOrCreateUser")
    public ResponseEntity<UserDto> getOrCreateUser(@RequestBody RegisterUserRequest requestDto) {
        var userFuture = new CompletableFuture<UserDto>();
        var event = new GetOrCreateUserEvent(requestDto, userFuture);
        eventPublisher.publishEvent(event);

        try {
            var userDto = userFuture.get(5, TimeUnit.SECONDS);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            throw new RuntimeException("Request timed out");
        }
    }
}

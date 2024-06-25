package com.gleam.backend.api.ui;

import com.gleam.backend.common.dto.GameDto;
import com.gleam.backend.common.dto.RegisterUserRequest;
import com.gleam.backend.common.dto.UserDto;
import com.gleam.backend.common.event.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
        var future = new CompletableFuture<UserDto>();
        var event = new GetOrCreateUserEvent(requestDto, future);
        return publishAndCreateResponse(event, future);
    }

    @GetMapping("/getAllGames")
    public ResponseEntity<List<GameDto>> getAllGames() {
        var future = new CompletableFuture<List<GameDto>>();
        var event = new GetAllGamesEvent(future);
        return publishAndCreateResponse(event, future);
    }

    @PostMapping("/addLike")
    public ResponseEntity<GameDto> addLike(@RequestParam(value = "id") String id) {
        var future = new CompletableFuture<GameDto>();
        var event = new AddLikeEvent(id, future);
        return publishAndCreateResponse(event, future);
    }

    @PostMapping("/addDislike")
    public ResponseEntity<GameDto> addDislike(@RequestParam(value = "id") String id) {
        var future = new CompletableFuture<GameDto>();
        var event = new AddDislikeEvent(id, future);
        return publishAndCreateResponse(event, future);
    }

    private <T> ResponseEntity<T> publishAndCreateResponse(Event event, CompletableFuture<T> future) {

        eventPublisher.publishEvent(event);

        try {
            T dto = future.get(5, TimeUnit.SECONDS);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            throw new RuntimeException("Request timed out");
        }
    }
}

package com.gleam.backend.api.ui;

import com.gleam.backend.common.dto.GameDto;
import com.gleam.backend.common.dto.RegisterUserRequest;
import com.gleam.backend.common.dto.UserDto;
import com.gleam.backend.common.event.GetAllGamesEvent;
import com.gleam.backend.common.event.GetOrCreateUserEvent;
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
    private final long timeout = 5;

    public ApiController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/getOrCreateUser")
    public ResponseEntity<UserDto> getOrCreateUser(@RequestBody RegisterUserRequest requestDto) {
        var userFuture = new CompletableFuture<UserDto>();
        var event = new GetOrCreateUserEvent(requestDto, userFuture);
        eventPublisher.publishEvent(event);

        try {
            var userDto = userFuture.get(timeout, TimeUnit.SECONDS);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            throw new RuntimeException("Request timed out");
        }
    }

    @GetMapping("/getAllGames")
    public ResponseEntity<List<GameDto>> getAllGames() {
        var future = new CompletableFuture<List<GameDto>>();
        var event = new GetAllGamesEvent(future);
        eventPublisher.publishEvent(event);

        try {
            var dto = future.get(timeout, TimeUnit.SECONDS);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            throw new RuntimeException("Request timed out");
        }
//        var games = gameService.getAllGames();
//        var gamesDto = gameMapper.toDtoList(games);
//        return new ResponseEntity<>(gamesDto, HttpStatus.OK);
    }
}

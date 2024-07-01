package com.gleam.backend.api.ui;

import com.gleam.backend.common.dto.GameDto;
import com.gleam.backend.common.dto.GetGamesRequest;
import com.gleam.backend.common.event.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class GameController extends ApiController {
    public GameController(ApplicationEventPublisher eventPublisher) {
        super(eventPublisher);
    }

    @GetMapping("/getAllGames")
    public ResponseEntity<List<GameDto>> getAllGames() {
        var future = new CompletableFuture<List<GameDto>>();
        var event = new GetAllGamesEvent(future);
        return publishAndCreateResponse(event, future);
    }

    @PostMapping("/getGames")
    public ResponseEntity<List<GameDto>> getGames(@RequestBody GetGamesRequest requestDto) {
        var future = new CompletableFuture<List<GameDto>>();
        var event = new GetGamesEvent(requestDto.platforms(), requestDto.prefix(), future);
        return publishAndCreateResponse(event, future);
    }

    @PostMapping("/getGameTitlesWithPrefix")
    public ResponseEntity<String[]> getGameTitlesWithPrefix(@RequestBody GetGamesRequest requestDto) {
        var future = new CompletableFuture<String[]>();
        var event = new GetGameTitlesByPrefixEvent(requestDto.platforms(), requestDto.prefix(), future);
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
}

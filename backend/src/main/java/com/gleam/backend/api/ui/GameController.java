package com.gleam.backend.api.ui;

import com.gleam.backend.common.dto.GameDto;
import com.gleam.backend.common.event.AddDislikeEvent;
import com.gleam.backend.common.event.AddLikeEvent;
import com.gleam.backend.common.event.GetAllGamesEvent;
import com.gleam.backend.common.event.GetGamesByPlatformEvent;
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

    @PostMapping("/getGamesByPlatform")
    public ResponseEntity<List<GameDto>> getGamesByPlatform(@RequestBody String[] platforms) {
        var future = new CompletableFuture<List<GameDto>>();
        var event = new GetGamesByPlatformEvent(platforms, future);
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

package com.gleam.backend.api.ui;

import com.gleam.backend.common.dto.RegisterUserRequest;
import com.gleam.backend.common.dto.UserDto;
import com.gleam.backend.common.event.GetOrCreateUserEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class UserController extends ApiController {

    public UserController(ApplicationEventPublisher eventPublisher) {
        super(eventPublisher);
    }

    @PostMapping("/getOrCreateUser")
    public ResponseEntity<UserDto> getOrCreateUser(@RequestBody RegisterUserRequest requestDto) {
        var future = new CompletableFuture<UserDto>();
        var event = new GetOrCreateUserEvent(requestDto, future);
        return publishAndCreateResponse(event, future);
    }
}

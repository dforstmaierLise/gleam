package com.gleam.backend.user.application;

import com.gleam.backend.common.event.GetOrCreateUserEvent;
import com.gleam.backend.user.mapper.UserMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener extends com.gleam.backend.common.event.EventListener {
    private final UserService userService;
    private final UserMapper userMapper;


    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]+$";

    public UserEventListener(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @EventListener
    public void handleGetOrCreateUserEvent(GetOrCreateUserEvent event) {
        var username = event.getRequest().username();
        var minLength = 3;
        var maxLength = 20;

        if (username.isEmpty() ||
                username.length() < minLength ||
                username.length() > maxLength ||
                !username.matches(USERNAME_PATTERN)) {
            handleIllegalUsername(event.getFuture());
            return;
        }

        var user = userService.getOrCreateUser(event.getRequest());
        var userDto = userMapper.toDto(user);
        event.getFuture().complete(userDto);
    }
}

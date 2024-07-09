package com.gleam.backend.user.application;

import com.gleam.backend.common.event.GetOrCreateUserEvent;
import com.gleam.backend.user.mapper.UserMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener extends com.gleam.backend.common.event.EventListener {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserEventListener(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @EventListener
    public void handleGetOrCreateUserEvent(GetOrCreateUserEvent event) {
        var user = userService.getOrCreateUser(event.getRequest());
        var userDto = userMapper.toDto(user);
        event.getFuture().complete(userDto);
    }
}

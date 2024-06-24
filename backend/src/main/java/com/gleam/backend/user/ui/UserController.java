package com.gleam.backend.user.ui;

import com.gleam.backend.user.application.UserService;
import com.gleam.backend.user.mapper.UserMapper;
import com.gleam.backend.user.ui.dto.RegisterUserRequest;
import com.gleam.backend.user.ui.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/getOrCreateUser")
    public ResponseEntity<UserDto> getOrCreateUser(@RequestBody RegisterUserRequest requestDto) {
        var newUser = userService.getOrCreateUser(requestDto);
        var newUserDto = userMapper.toDto(newUser);
        return new ResponseEntity<>(newUserDto, HttpStatus.CREATED);
    }
}

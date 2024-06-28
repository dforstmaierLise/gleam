package com.gleam.backend.user.application;

import com.gleam.backend.common.dto.RegisterUserRequest;
import com.gleam.backend.user.domain.User;
import com.gleam.backend.user.infrastructure.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getOrCreateUser(RegisterUserRequest requestDto) {
        var optionalUser = userRepository.findByUsername(requestDto.username());
        return optionalUser.orElseGet(() -> createUser(requestDto.username()));
    }

    private User createUser(String username) {
        return userRepository.save(new User(username, "fakeHashPassword"));
    }
}

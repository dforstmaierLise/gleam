package com.gleam.backend.controller;

import com.gleam.backend.dto.LoginDto;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @PostMapping("/login")
    public boolean login(@RequestBody LoginDto credentials) {

        if( credentials.username().isEmpty() ||
            credentials.password().isEmpty() ) {
            return false;
        }

        return Math.random() < 0.5;
    }
}

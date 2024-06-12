package com.gleam.backend;

import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @PostMapping("/login")
    public boolean login(@RequestBody Login credentials) {

        if( credentials.username().isEmpty() ||
            credentials.password().isEmpty() ) {
            return false;
        }

        return Math.random() < 0.5;
    }
}

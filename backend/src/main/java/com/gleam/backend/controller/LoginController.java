package com.gleam.backend.controller;

import com.gleam.backend.dto.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDto credentials) {

        if( credentials.username().isEmpty() ||
            credentials.password().isEmpty() ) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(Math.random() < 0.5, HttpStatus.OK);
    }
}

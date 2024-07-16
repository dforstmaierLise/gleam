package com.gleam.backend.api.ui;

import com.gleam.backend.common.event.Event;
import com.gleam.backend.common.exception.IdNotFoundException;
import com.gleam.backend.common.exception.IllegalUsernameException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class ApiController {
    private final ApplicationEventPublisher eventPublisher;

    public ApiController(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    protected <T> ResponseEntity<T> publishAndCreateResponse(Event event, CompletableFuture<T> future) {

        eventPublisher.publishEvent(event);

        try {
            T dto = future.get(5, TimeUnit.SECONDS);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (IdNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalUsernameException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

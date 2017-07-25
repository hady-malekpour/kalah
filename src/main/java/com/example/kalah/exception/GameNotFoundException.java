package com.example.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {
    }

    public GameNotFoundException(String message) {
        super(message);
    }
}

package com.example.kalah.rest;

import com.example.kalah.exception.GameNotFoundException;
import com.example.kalah.exception.InvalidActionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity handleConflict(GameNotFoundException exception) {
        return new ResponseEntity<Object>(exception.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidActionException.class)
    public ResponseEntity handleConflict(InvalidActionException exception) {
        return new ResponseEntity<Object>(exception.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}

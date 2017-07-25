package com.example.kalah.exception;

public class InvalidActionException extends RuntimeException {
    public InvalidActionException(String message){
        super(message);
    }
}

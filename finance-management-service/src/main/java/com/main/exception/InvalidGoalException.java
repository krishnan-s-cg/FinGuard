package com.main.exception;

public class InvalidGoalException extends RuntimeException {
    public InvalidGoalException(String message) {
        super(message);
    }
}

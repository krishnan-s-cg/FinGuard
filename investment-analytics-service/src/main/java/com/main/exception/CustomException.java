package com.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * CustomException is a runtime exception that is thrown when a specific resource is not found.
 * It is annotated with @ResponseStatus to return a 404 NOT FOUND HTTP status code when thrown.
 */
@ResponseStatus(HttpStatus.NOT_FOUND) // This will return a 404 Not Found response
public class CustomException extends RuntimeException {
    
    // Constructor that accepts a message
    public CustomException(String message) {
        super(message); // Pass the message to the superclass (RuntimeException)
    }
}
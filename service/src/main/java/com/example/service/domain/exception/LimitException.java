package com.example.service.domain.exception;

public class LimitException extends RuntimeException {
    public LimitException(
            final String message
    ) {
        super(message);
    }
}

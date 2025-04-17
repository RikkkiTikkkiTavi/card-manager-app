package com.example.card_manager_app.domain.exception;

public class LimitException extends RuntimeException {
    public LimitException(
            final String message
    ) {
        super(message);
    }
}

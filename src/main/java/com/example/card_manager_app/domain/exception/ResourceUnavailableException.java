package com.example.card_manager_app.domain.exception;

public class ResourceUnavailableException extends RuntimeException {
    public ResourceUnavailableException(
            final String message
    ) {
        super(message);
    }
}

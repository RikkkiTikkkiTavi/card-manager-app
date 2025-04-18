package com.example.service.domain.exception;

public class ResourceUnavailableException extends RuntimeException {
    public ResourceUnavailableException(
            final String message
    ) {
        super(message);
    }
}

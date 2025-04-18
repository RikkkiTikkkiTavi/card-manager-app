package com.example.service.domain.exception;

public class TransactionException extends RuntimeException {
    public TransactionException(
            final String message
    ) {
        super(message);
    }
}

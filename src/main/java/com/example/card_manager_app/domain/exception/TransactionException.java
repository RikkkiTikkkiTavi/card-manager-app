package com.example.card_manager_app.domain.exception;

public class TransactionException extends RuntimeException {
    public TransactionException(
            final String message
    ) {
        super(message);
    }
}

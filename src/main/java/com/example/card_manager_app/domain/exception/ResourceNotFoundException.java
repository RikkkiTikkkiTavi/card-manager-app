package com.example.card_manager_app.domain.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(
            final String message
    ) {
        super(message);
    }

}

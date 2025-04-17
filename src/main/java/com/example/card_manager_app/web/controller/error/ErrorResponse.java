package com.example.card_manager_app.web.controller.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

    @Getter
    @AllArgsConstructor
    public class ErrorResponse {
        String status;
        String reason;
        String message;
}

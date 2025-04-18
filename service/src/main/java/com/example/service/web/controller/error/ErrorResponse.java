package com.example.service.web.controller.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

    @Getter
    @AllArgsConstructor
    public class ErrorResponse {
        String status;
        String reason;
        String message;
}

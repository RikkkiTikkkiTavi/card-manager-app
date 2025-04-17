package com.example.card_manager_app.web.controller.error;

import com.example.card_manager_app.domain.exception.ResourceAlreadyExistsException;
import com.example.card_manager_app.domain.exception.ResourceNotFoundException;
import com.example.card_manager_app.domain.exception.ResourceUnavailableException;
import com.example.card_manager_app.domain.exception.TransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleTransaction(final TransactionException e) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFound(final ResourceNotFoundException e) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.toString(),
                HttpStatus.NOT_FOUND.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflict(final ResourceAlreadyExistsException e) {
        return new ErrorResponse(HttpStatus.CONFLICT.toString(),
                HttpStatus.CONFLICT.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleUnauthorized(final ResourceUnavailableException e) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED.toString(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(), e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleNotFound(final RuntimeException e) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e.getMessage());
    }
}

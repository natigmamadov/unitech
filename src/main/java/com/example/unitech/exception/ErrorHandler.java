package com.example.unitech.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.unitech.exception.ExceptionConstants.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception exception) {
        log.error("Exception", exception);
        return new ExceptionResponse(UNEXPECTED_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(NotFoundException exception) {
        log.error("NotFoundException", exception);
        return new ExceptionResponse(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(SameStatusException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(SameStatusException exception) {
        log.error("SameStatusException", exception);
        return new ExceptionResponse(exception.getCode(), exception.getMessage());
    }


    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(AlreadyExistsException ex) {
        log.error("AlreadyExistsException: ", ex);
        return new ExceptionResponse(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(WrongCredentialsException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ExceptionResponse handle(WrongCredentialsException exception) {
        log.error("WrongCredentialsException", exception);
        return new ExceptionResponse(WRONG_CREDENTIALS_CODE, WRONG_CREDENTIALS_MESSAGE);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handle(IllegalArgumentException exception) {
        log.error("IllegalArgumentException", exception);
        return new ExceptionResponse(WRONG_CREDENTIALS_CODE, WRONG_CREDENTIALS_MESSAGE);
    }



}

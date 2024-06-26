package com.example.unitech.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AlreadyExistsException extends RuntimeException {
    private String code;

    public AlreadyExistsException(String code, String message) {
        super(message);
        this.code = code;
    }
}
package com.example.unitech.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IllegalArgumentException extends RuntimeException {
    private String code;

    public IllegalArgumentException(String code, String message) {
        super(message);
        this.code = code;
    }
}

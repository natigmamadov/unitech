package com.example.unitech.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SameStatusException extends RuntimeException {
    private String code;

    public SameStatusException(String code, String message) {
        super(message);
        this.code = code;
    }
}

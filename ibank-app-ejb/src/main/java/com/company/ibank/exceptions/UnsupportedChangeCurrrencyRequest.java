package com.company.ibank.exceptions;

public class UnsupportedChangeCurrrencyRequest extends Exception {
    public UnsupportedChangeCurrrencyRequest(String message) {
        super(message);
    }

    public UnsupportedChangeCurrrencyRequest(String message, Throwable cause) {
        super(message, cause);
    }
}

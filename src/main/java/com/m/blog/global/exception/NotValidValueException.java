package com.m.blog.global.exception;

public class NotValidValueException extends RuntimeException {
    public NotValidValueException() {
    }

    public NotValidValueException(String message) {
        super(message);
    }

    public NotValidValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidValueException(Throwable cause) {
        super(cause);
    }
}
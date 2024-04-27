package com.m.blog.global.exception;

public class CustomIllegalArgumentException extends IllegalArgumentException {
    public CustomIllegalArgumentException() {
    }

    public CustomIllegalArgumentException(String message) {
        super(message);
    }

    public CustomIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomIllegalArgumentException(Throwable cause) {
        super(cause);
    }
}
package com.m.blog.global.exception;

public class MultipleChangedException extends RuntimeException {
    public MultipleChangedException() {
    }

    public MultipleChangedException(String message) {
        super(message);
    }

    public MultipleChangedException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultipleChangedException(Throwable cause) {
        super(cause);
    }
}
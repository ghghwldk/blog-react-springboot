package com.m.blog.global.exception;

public class NothingChangedException extends RuntimeException {
    public NothingChangedException() {
    }

    public NothingChangedException(String message) {
        super(message);
    }

    public NothingChangedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NothingChangedException(Throwable cause) {
        super(cause);
    }
}
package com.m.blog.global.exception;

public class PasswordNotMatchedException extends RuntimeException {
    public PasswordNotMatchedException() {
    }

    public PasswordNotMatchedException(String message) {
        super(message);
    }

    public PasswordNotMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordNotMatchedException(Throwable cause) {
        super(cause);
    }
}

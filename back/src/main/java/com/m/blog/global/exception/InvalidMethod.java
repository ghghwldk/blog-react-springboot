
package com.m.blog.global.exception;

public class InvalidMethod extends RuntimeException {
    public InvalidMethod() {
    }

    public InvalidMethod(String message) {
        super(message);
    }

    public InvalidMethod(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidMethod(Throwable cause) {
        super(cause);
    }
}
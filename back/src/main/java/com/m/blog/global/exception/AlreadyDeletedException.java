
package com.m.blog.global.exception;

public class AlreadyDeletedException extends RuntimeException {
    public AlreadyDeletedException() {
    }

    public AlreadyDeletedException(String message) {
        super(message);
    }

    public AlreadyDeletedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyDeletedException(Throwable cause) {
        super(cause);
    }
}
package com.m.blog.global.exception;

public class FileDeleteLogicException extends RuntimeException {
    public FileDeleteLogicException() {
    }

    public FileDeleteLogicException(String message) {
        super(message);
    }

    public FileDeleteLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileDeleteLogicException(Throwable cause) {
        super(cause);
    }
}

package com.m.blog.global.exception;

public class GetFileNullException extends RuntimeException {
    public GetFileNullException() {
    }

    public GetFileNullException(String message) {
        super(message);
    }

    public GetFileNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetFileNullException(Throwable cause) {
        super(cause);
    }
}
package org.example.library_on_spring.exception;

public class BookOrderException extends RuntimeException {
    public BookOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
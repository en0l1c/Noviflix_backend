package com.enolic.Noviflix_backend.exception.exceptions;

public class NoContentException extends RuntimeException {
    public NoContentException() {
        super("No content available");
    }
}
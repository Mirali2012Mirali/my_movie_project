package com.movie.dea.exception;

public class DirectorNotFoundException extends RuntimeException {
    public DirectorNotFoundException(String message) {
        super(message);
    }
}

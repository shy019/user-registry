package com.nisum.registration.exception;

public class UserException extends Exception {

    private static final long serialVersionUID = 1L;

    private final String message;

    public UserException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

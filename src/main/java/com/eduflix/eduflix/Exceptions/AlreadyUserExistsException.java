package com.eduflix.eduflix.Exceptions;

public class AlreadyUserExistsException extends Throwable {
    public AlreadyUserExistsException(String message) {
        super(message);
    }
}

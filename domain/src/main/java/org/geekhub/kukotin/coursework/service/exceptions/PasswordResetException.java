package org.geekhub.kukotin.coursework.service.exceptions;

public class PasswordResetException extends RuntimeException {

    public PasswordResetException(String message, Throwable cause) {
        super(message, cause);
    }
}

package org.geekhub.kukotin.coursework.service.exceptions;

public class EmailSendingException extends RuntimeException {

    public EmailSendingException(String message, Throwable cause) {
        super(message, cause);
    }
}

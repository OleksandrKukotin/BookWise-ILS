package org.geekhub.kukotin.coursework.webcontroller.user.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception e) {
        logger.error(e.getMessage(), e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = HttpClientErrorException.NotFound.class)
    public ResponseEntity<Object> handleNotFoundException(RuntimeException ex, WebRequest request) {
        logger.error("Resource not found: ", ex);
        String responseMessage = "The resource you're looking for isn't here. " +
            "Please, check the URL or try searching for what you need.";
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = HttpClientErrorException.Forbidden.class)
    public ResponseEntity<Object> handleForbiddenException(RuntimeException ex, WebRequest request) {
        logger.error("Access denied: ", ex);
        String responseMessage = "You don't have permission to access this. " +
            "If you think this is a mistake, please contact support.";
        return new ResponseEntity<>(responseMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException e) {
        logger.error(e.getMessage());
        String responseMessage = "There is nothing here. " +
            "If you think this is a mistake, please contact support.";
        return new ResponseEntity<>(responseMessage, HttpStatus.NOT_FOUND);
    }
}

package org.geekhub.kukotin.coursework.webcontroller.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String ERROR_PAGE_FILE = "errorPage";
    public static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public String handleAnyException(Exception e, Model model) {
        logger.error(e.getMessage(), e);
        String errorMessage = "Well... Something went wrong";
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, errorMessage);
        return ERROR_PAGE_FILE;
    }

    @ExceptionHandler(value = HttpClientErrorException.NotFound.class)
    public String handleNotFoundException(RuntimeException ex, Model model) {
        logger.error("Resource not found: ", ex);
        String errorMessage = "The resource you're looking for isn't here. " +
            "Please, check the URL or try searching for what you need.";
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, errorMessage);
        return ERROR_PAGE_FILE;
    }

    @ExceptionHandler(value = HttpClientErrorException.Forbidden.class)
    public String handleForbiddenException(RuntimeException ex, Model model) {
        logger.error("Access denied: ", ex);
        String errorMessage = "You don't have permission to access this. " +
            "If you think this is a mistake, please contact support.";
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, errorMessage);
        return ERROR_PAGE_FILE;
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public String handleNoResourceFoundException(NoResourceFoundException e, Model model) {
        logger.error(e.getMessage());
        String errorMessage = "There is nothing here. " +
            "If you think this is a mistake, please contact support.";
        model.addAttribute(ERROR_MESSAGE_ATTRIBUTE, errorMessage);
        return ERROR_PAGE_FILE;
    }
}

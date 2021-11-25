package com.example.springbootdemo.common.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.springbootdemo.common.constants.CommonConstants.BREAKING_LINE;
import static com.example.springbootdemo.common.constants.CommonConstants.ErrorMessage.*;

/**
 * The class to return the unique exception response for all controller
 *
 * @author Jackson
 */
@RestControllerAdvice
public class ExceptionalResponseAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleExceptions(HttpServletRequest request, Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put(URL, request.getRequestURL());
        response.put(ERROR_MESSAGE, GENERIC_EXCEPTION);
        response.put(ERROR_DETAIL, getStackMsg(exception));
        // TODO Will replace the console print statement with logger after the logger integrated
        System.out.println("Got exception, detail: " + exception.getMessage());
        return response;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handlePermissionExceptions(HttpServletRequest request, Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put(URL, request.getRequestURL());
        response.put(ERROR_MESSAGE, WITHOUT_PERMISSION);
        response.put(ERROR_DETAIL, getStackMsg(exception));
        // TODO Will replace the console print statement with logger after the logger integrated
        System.out.println("Got exception, detail: " + exception.getMessage());
        return response;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleDataIntegrityViolationException(HttpServletRequest request, Exception exception) {
        Map<String, Object> response = new HashMap<>();
        response.put(URL, request.getRequestURL());
        response.put(ERROR_MESSAGE, FOREIGN_KEY_CONSTRAINT);
        response.put(ERROR_DETAIL, getStackMsg(exception));
        // TODO Will replace the console print statement with logger after the logger integrated
        System.out.println("Got exception, detail: " + exception.getMessage());
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> onMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException methodArgumentNotValidException) {
        // assemble errorResponse
        Map<String, Object> response = new HashMap<>();
        response.put(URL, request.getRequestURL());
        // everything starts as a generic exception. The datafabric exception handler might override this value
        response.put(ERROR_MESSAGE, METHOD_ARGUMENT_NOT_VALID);
        // Get the error message field to send back to the user in the error response
        String errorMessage = methodArgumentNotValidException.getBindingResult().getFieldErrors()
                .stream()
                .map(item -> item.getDefaultMessage())
                .collect(Collectors.joining(", "));
        response.put(ERROR_DETAIL, errorMessage);
        // TODO Will replace the console print statement with logger after the logger integrated
        System.out.println("Got exception, detail: " + errorMessage);
        return response;
    }

    /**
     * Get stack message from exception
     *
     * @param e
     * @return String
     */
    private static String getStackMsg(Exception e) {
        StringBuffer traceBuffer = new StringBuffer();
        traceBuffer.append(e.getMessage() + BREAKING_LINE);
        for (StackTraceElement element : e.getStackTrace()) {
            traceBuffer.append(element.toString() + BREAKING_LINE);
        }
        return traceBuffer.toString();
    }
}

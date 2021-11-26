package com.example.springbootdemo.common.exception;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RestControllerAdvice
public class ExceptionalResponseAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleExceptions(HttpServletRequest request, Exception exception) {
        return generateExceptionResponse(request, exception, GENERIC_EXCEPTION);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public Map<String, Object> handlePermissionExceptions(HttpServletRequest request, Exception exception) {
        return generateExceptionResponse(request, exception, WITHOUT_PERMISSION);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleDataIntegrityViolationException(HttpServletRequest request, Exception exception) {
        return generateExceptionResponse(request, exception, FOREIGN_KEY_CONSTRAINT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> onMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException methodArgumentNotValidException) {
        // assemble errorResponse
        Map<String, Object> response = new HashMap<>(3);
        response.put(URL, request.getRequestURL());
        response.put(ERROR_MESSAGE, METHOD_ARGUMENT_NOT_VALID);
        // Get the error message field to send back to the user in the error response
        String errorMessage = methodArgumentNotValidException.getBindingResult().getFieldErrors()
                .stream()
                .map(item -> item.getDefaultMessage())
                .collect(Collectors.joining(", "));
        response.put(ERROR_DETAIL, errorMessage);
        log.error("Got exception, detail: {}", errorMessage);
        return response;
    }

    /**
     * Generate exception response
     *
     * @param request
     * @param exception
     * @param genericException
     * @return Map<String, Object>
     */
    private Map<String, Object> generateExceptionResponse(HttpServletRequest request, Exception exception, String genericException) {
        Map<String, Object> response = new HashMap<>(3);
        response.put(URL, request.getRequestURL());
        response.put(ERROR_MESSAGE, genericException);
        response.put(ERROR_DETAIL, getStackMsg(exception));
        log.error("Got exception, detail: {}", exception.getMessage());
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

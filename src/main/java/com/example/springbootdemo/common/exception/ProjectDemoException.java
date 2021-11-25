package com.example.springbootdemo.common.exception;

import lombok.Data;

/**
 * @author Jackson
 */
@Data
public class ProjectDemoException extends Exception {

    private String id;
    private String[] errorCodeArray;

    public String getErrorMessage() {
        return super.getMessage();
    }

    public void setErrorMessage() {}

    public Exception getOriginalException() {
        return (Exception) super.getCause();
    }

    public void setOriginalException(Exception originalException) {}

    public ProjectDemoException() {
        super();
    }

    public ProjectDemoException(Exception e) {
        super(e);
    }

    public ProjectDemoException(String id, Exception e) {
        super(e);
        this.id = id;
    }

    public ProjectDemoException(Exception e, String errorMessage) {
        super(errorMessage, e);
    }

    public ProjectDemoException(String id, Exception e, String errorMessage) {
        super(errorMessage, e);
        this.id = id;
    }

    public ProjectDemoException(String[] errorCodeArray) {
        super();
        this.errorCodeArray = errorCodeArray;
    }

    public ProjectDemoException(String errorMessage) {
        super(errorMessage);
    }

    public ProjectDemoException(String id, String errorMessage) {
        super(errorMessage);
        this.id = id;
    }

    public ProjectDemoException(String[] errorCodeArray, String errorMessage) {
        super(errorMessage);
        this.errorCodeArray = errorCodeArray;
    }

    public ProjectDemoException(Exception e, String[] errorCodeArray) {
        super(e.getMessage(), e);
        this.errorCodeArray = errorCodeArray;
    }
}

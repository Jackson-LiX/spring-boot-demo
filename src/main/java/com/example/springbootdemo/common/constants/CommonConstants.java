package com.example.springbootdemo.common.constants;

/**
 * The constants class
 *
 * @author Jackson
 */
public class CommonConstants {

    protected CommonConstants() {
        throw new UnsupportedOperationException();
    }

    public static final String BREAKING_LINE = "\n";

    public static class ErrorMessage {
        public static final String URL = "url";
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String ERROR_DETAIL = "errorDetail";
        public static final String GENERIC_EXCEPTION = "Generic exception";
        public static final String WITHOUT_PERMISSION = "You do not have permission to do that! Please contact your system administrator.";
        public static final String FOREIGN_KEY_CONSTRAINT = "Violates database constraint. Please contact your system administrator.";
        public static final String METHOD_ARGUMENT_NOT_VALID = "Method arguments are not valid. Please try again.";
    }

}

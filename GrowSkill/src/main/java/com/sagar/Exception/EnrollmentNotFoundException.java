package com.sagar.Exception;

public class EnrollmentNotFoundException extends RuntimeException {

    public EnrollmentNotFoundException() {
        super();
    }

    public EnrollmentNotFoundException(String message) {
        super(message);
    }

    public EnrollmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


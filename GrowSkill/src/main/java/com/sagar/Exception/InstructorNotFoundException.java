package com.sagar.Exception;

public class InstructorNotFoundException extends RuntimeException {

    public InstructorNotFoundException() {
        super();
    }

    public InstructorNotFoundException(String message) {
        super(message);
    }

    public InstructorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


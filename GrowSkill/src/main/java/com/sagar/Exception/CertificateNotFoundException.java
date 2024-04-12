package com.sagar.Exception;

public class CertificateNotFoundException extends RuntimeException {

    public CertificateNotFoundException() {
        super();
    }

    public CertificateNotFoundException(String message) {
        super(message);
    }

    public CertificateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


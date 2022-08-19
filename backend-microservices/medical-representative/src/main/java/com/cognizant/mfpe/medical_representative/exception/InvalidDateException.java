package com.cognizant.mfpe.medical_representative.exception;

public class InvalidDateException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidDateException() {
        super();
    }

    public InvalidDateException(String message) {
        super(message);
    }
}

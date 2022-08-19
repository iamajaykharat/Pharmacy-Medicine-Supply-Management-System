package com.cognizant.mfpe.pharmacy_supply.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenValidationFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TokenValidationFailedException(String message) {
        super(message);
    }
}

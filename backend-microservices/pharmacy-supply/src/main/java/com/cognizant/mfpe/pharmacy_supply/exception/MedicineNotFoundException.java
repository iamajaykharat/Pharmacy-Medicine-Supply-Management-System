package com.cognizant.mfpe.pharmacy_supply.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MedicineNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public MedicineNotFoundException(String message) {
        super(message);
    }
}

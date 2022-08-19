package com.cognizant.mfpe.pharmacy_supply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MedicineStock {
    private Integer medicineId;
    private String medicineName;
    private String chemicalComposition;
    private String targetAilment;
    private Date dateOfExpiry;
    private Integer numberOfTabletsInStock;
    private String pharmacyName;
}

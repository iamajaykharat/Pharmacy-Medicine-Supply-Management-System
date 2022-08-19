package com.cognizant.mfpe.pharmacy_supply.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PharmacyMedicineSupply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    private String pharmacyName;
    private String medicineName;
    private int supplyCount;

    public PharmacyMedicineSupply(
            String pharmacyName,
            String medicineName,
            int supplyCount
    ) {
        super();
        this.pharmacyName = pharmacyName;
        this.medicineName = medicineName;
        this.supplyCount = supplyCount;
    }
}

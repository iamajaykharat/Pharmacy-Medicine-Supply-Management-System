package com.cognizant.mfpe.medicine_stock.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineSupply {
    private String medicineName;
    private Integer supplyCount;
}

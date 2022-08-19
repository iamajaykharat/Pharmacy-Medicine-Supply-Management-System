package com.cognizant.mfpe.pharmacy_supply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "medicine_demand")
@Entity
public class MedicineDemand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String medicineName;
    private int demandCount;
    private int fulfilled;
    private LocalDate demandDate;

    public MedicineDemand(
            String medicineName,
            int demandCount,
            LocalDate demandDate,
            int fulfilled
    ) {
        super();
        this.medicineName = medicineName;
        this.demandCount = demandCount;
        this.demandDate = demandDate;
        this.fulfilled = fulfilled;
    }

    public MedicineDemand(int id, String medicineName, int demandCount) {
        super();
        this.id = id;
        this.medicineName = medicineName;
        this.demandCount = demandCount;
    }
}

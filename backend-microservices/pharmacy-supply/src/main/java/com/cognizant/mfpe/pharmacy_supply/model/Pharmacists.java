package com.cognizant.mfpe.pharmacy_supply.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pharmacists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pharmacistsId;
    private String pharmacistsName;
}

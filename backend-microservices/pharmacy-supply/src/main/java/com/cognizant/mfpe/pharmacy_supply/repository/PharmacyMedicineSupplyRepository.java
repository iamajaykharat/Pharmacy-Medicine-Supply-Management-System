package com.cognizant.mfpe.pharmacy_supply.repository;

import com.cognizant.mfpe.pharmacy_supply.model.PharmacyMedicineSupply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyMedicineSupplyRepository extends JpaRepository<PharmacyMedicineSupply, Integer> {
    public List<PharmacyMedicineSupply> findTop20ByOrderByIdDesc();
}

package com.cognizant.mfpe.medicine_stock.repository;

import com.cognizant.mfpe.medicine_stock.model.MedicineStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineStockRepo extends JpaRepository<MedicineStock, Integer> {
    List<MedicineStock> findByTargetAilment(String targetAilment);
}

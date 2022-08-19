package com.cognizant.mfpe.pharmacy_supply.repository;

import com.cognizant.mfpe.pharmacy_supply.model.MedicineDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineDemandRepository extends JpaRepository<MedicineDemand, Integer> {
    public List<MedicineDemand> findTop20ByOrderByIdDesc();
}

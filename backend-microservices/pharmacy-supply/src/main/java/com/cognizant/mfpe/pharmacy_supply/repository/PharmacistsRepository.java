package com.cognizant.mfpe.pharmacy_supply.repository;

import com.cognizant.mfpe.pharmacy_supply.model.Pharmacists;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacistsRepository extends JpaRepository<Pharmacists, Integer> {
}

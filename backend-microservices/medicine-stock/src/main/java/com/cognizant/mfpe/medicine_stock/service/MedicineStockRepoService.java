package com.cognizant.mfpe.medicine_stock.service;

import com.cognizant.mfpe.medicine_stock.model.MedicineStock;
import com.cognizant.mfpe.medicine_stock.pojo.MedicineSupply;
import com.cognizant.mfpe.medicine_stock.repository.MedicineStockRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MedicineStockRepoService {

    @Autowired
    MedicineStockRepo medicineStockRepo;

    public List<MedicineStock> getMedicineStockDetails() {
        return medicineStockRepo.findAll();
    }

    public List<String> getMedicineStockByAilment(String targetAilment) {
        return medicineStockRepo
                .findByTargetAilment(targetAilment)
                .stream()
                .map(MedicineStock::getMedicineName)
                .collect(Collectors.toList());
    }

    @Transactional
    public Boolean updateMedicineStock(List<MedicineSupply> updateMedicineStock) {
        List<MedicineStock> medicineDetails = medicineStockRepo.findAll();
        for (MedicineSupply updateMedicine : updateMedicineStock) {
            for (MedicineStock medicine : medicineDetails) {
                if (medicine.getMedicineName().equals(updateMedicine.getMedicineName())) {
                    medicine.setNumberOfTabletsInStock(updateMedicine.getSupplyCount());
                    medicineStockRepo.save(medicine);
                }
            }
        }
        return true;
    }
}

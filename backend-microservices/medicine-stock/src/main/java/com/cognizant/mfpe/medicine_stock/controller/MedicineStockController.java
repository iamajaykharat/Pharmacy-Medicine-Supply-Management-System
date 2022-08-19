package com.cognizant.mfpe.medicine_stock.controller;

import com.cognizant.mfpe.medicine_stock.model.MedicineStock;
import com.cognizant.mfpe.medicine_stock.pojo.ApiResponse;
import com.cognizant.mfpe.medicine_stock.pojo.MedicineSupply;
import com.cognizant.mfpe.medicine_stock.service.MedicineStockRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MedicineStockController {

    @Autowired
    MedicineStockRepoService medicineStockRepoService;

    @GetMapping("/")
    public ResponseEntity<List<MedicineStock>> getMedicineStock() {
        return new ResponseEntity<>(
                medicineStockRepoService.getMedicineStockDetails(),
                HttpStatus.OK);
    }

    @GetMapping("/byAilment/{targetAilment}")
    public ResponseEntity<List<String>> getMedicineByAilment(
            @PathVariable String targetAilment
    ) {
        try {
            return new ResponseEntity<>(
                    medicineStockRepoService.getMedicineStockByAilment(targetAilment),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/")
    public ResponseEntity<ApiResponse> updateMedicineStock(
            @RequestBody List<MedicineSupply> updateMedicineStockInfo
    ) {
        if (medicineStockRepoService.updateMedicineStock(updateMedicineStockInfo)) {
            return new ResponseEntity<>(new ApiResponse(
                    true,
                    "Updated Successfully"),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(new ApiResponse(
                false,
                "Unsuccessful"),
                HttpStatus.BAD_REQUEST
        );
    }
}

package com.cognizant.mfpe.pharmacy_supply.feign;

import com.cognizant.mfpe.pharmacy_supply.model.ApiResponse;
import com.cognizant.mfpe.pharmacy_supply.model.MedicineStock;
import com.cognizant.mfpe.pharmacy_supply.model.MedicineStockToUpdate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "${MEDICINE_STOCK_FEIGN_URL}", name = "${MEDICINE_STOCK_FEIGN_NAME}")
public interface MedicineStockFeign {

    @GetMapping("${MEDICINE_STOCK_GET_MEDICINE_STOCK_URL}")
    public List<MedicineStock> getMedicineStock();

    @PostMapping("${MEDICINE_STOCK_GET_MEDICINE_URL}")
    public ApiResponse updateMedicineStock(
            @RequestBody List<MedicineStockToUpdate> updateMedicineStockInfo);
}

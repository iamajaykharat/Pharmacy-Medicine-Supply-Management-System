package com.cognizant.mfpe.medical_representative.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        url = "${MEDICINE_STOCK_FEIGN_URL}",
        name = "${MEDICINE_STOCK_FEIGN_NAME}"
)
public interface MedicineStockFeignClient {

    @GetMapping("${MEDICINE_STOCK_FEIGN_TARGET_URL}")
    public List<String> getMedicineByAilment(
            @PathVariable String targetAilment
    );

}

package com.cognizant.mfpe.pharmacy_supply.feign;

import com.cognizant.mfpe.pharmacy_supply.model.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "${AUTH_FEIGN_NAME}", url = "${AUTH_FEIGN_URL}")
public interface AuthenticationFeignClient {

    @GetMapping(value = "${AUTH_FEIGN_VALIDATE_URL}")
    public ApiResponse validateToken(
            @RequestHeader(name = "Authorization") String token
    );

}

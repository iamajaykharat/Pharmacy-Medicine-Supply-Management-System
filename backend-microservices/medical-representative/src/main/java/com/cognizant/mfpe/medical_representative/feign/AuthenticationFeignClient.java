package com.cognizant.mfpe.medical_representative.feign;

import com.cognizant.mfpe.medical_representative.model.JwtResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(
        name = "${AUTH_FEIGN_NAME}",
        url = "${AUTH_FEIGN_URL}"
)
public interface AuthenticationFeignClient {


    @GetMapping(value = "${AUTH_FEIGN_VALIDATE_URL}")
    public JwtResponse verifyToken(
            @RequestHeader(name = "Authorization", required = true) String token
    );

    @GetMapping("${AUTH_FEIGN_USER_DETAILS_URL}")
    public List<String> allUserDetails();
}

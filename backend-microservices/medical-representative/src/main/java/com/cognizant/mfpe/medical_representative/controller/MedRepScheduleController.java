package com.cognizant.mfpe.medical_representative.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import com.cognizant.mfpe.medical_representative.exception.InvalidDateException;
import com.cognizant.mfpe.medical_representative.exception.TokenValidationFailedException;
import com.cognizant.mfpe.medical_representative.feign.AuthenticationFeignClient;
import com.cognizant.mfpe.medical_representative.feign.MedicineStockFeignClient;
import com.cognizant.mfpe.medical_representative.model.Doctor;
import com.cognizant.mfpe.medical_representative.model.JwtResponse;
import com.cognizant.mfpe.medical_representative.model.RepSchedule;
import com.cognizant.mfpe.medical_representative.service.MedRepScheduleService;
import com.cognizant.mfpe.medical_representative.util.CsvParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class MedRepScheduleController {

    @Autowired
    private MedRepScheduleService scheduleService;

    @Autowired
    private AuthenticationFeignClient authFeignClient;

    @Autowired
    private MedicineStockFeignClient medicineStockClient;

    @GetMapping("/{scheduleStartDate}")
    public ResponseEntity<List<RepSchedule>> getRepSchedule(
            @RequestHeader(name = "Authorization") final String token,
            @PathVariable("scheduleStartDate") final String scheduleStartDate
    ) throws InvalidDateException, TokenValidationFailedException {
        log.info("Start");
        log.debug("scheduleStartDate : {}", scheduleStartDate);
        List<RepSchedule> repSchedule = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(scheduleStartDate, format);
        log.debug("localDate : {}", localDate);
        if (!isValidSession(token)) {
            log.info("End");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (localDate == null) {
            log.info("End");
            throw new InvalidDateException("Invalid date");
        }
        repSchedule = scheduleService.getRepSchedule(token, localDate);
        log.debug("repSchedule : {}", repSchedule);
        log.info("End");
        return ResponseEntity.ok().body(repSchedule);
    }
    public Boolean isValidSession(String token) throws TokenValidationFailedException {
        log.info("Start");
        final JwtResponse response = authFeignClient.verifyToken(token);
        log.debug("response : {}", response);
        log.info("End");
        return true;
    }

    @GetMapping
    public ResponseEntity<?> getMedicinesByTreatingAilment(@RequestHeader(name = "Authorization") String token) {
        log.info("Start");
        final ResponseEntity<?> responseEntity = ResponseEntity.of(Optional.of(medicineStockClient.getMedicineByAilment("General")));
        log.info("End");
        return responseEntity;
    }


    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        log.info("Start");
        List<Doctor> doctors = CsvParseUtil.parseDoctors();
        log.info("End");
        return doctors;
    }
}

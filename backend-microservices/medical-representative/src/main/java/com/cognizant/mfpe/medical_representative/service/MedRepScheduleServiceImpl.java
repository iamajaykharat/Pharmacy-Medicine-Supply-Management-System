package com.cognizant.mfpe.medical_representative.service;

import com.cognizant.mfpe.medical_representative.exception.TokenValidationFailedException;
import com.cognizant.mfpe.medical_representative.feign.AuthenticationFeignClient;
import com.cognizant.mfpe.medical_representative.feign.MedicineStockFeignClient;
import com.cognizant.mfpe.medical_representative.model.Doctor;
import com.cognizant.mfpe.medical_representative.model.JwtResponse;
import com.cognizant.mfpe.medical_representative.model.RepSchedule;
import com.cognizant.mfpe.medical_representative.util.CsvParseUtil;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MedRepScheduleServiceImpl implements MedRepScheduleService {

    @Autowired
    private MedicineStockFeignClient medicineStockClient;


    @Autowired
    AuthenticationFeignClient authFeignClient;

    @Override
    public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate) throws TokenValidationFailedException {
        log.info("Start");
        if (!isValidSession(token)) {
            log.info("End");
            return null;
        }
        List<RepSchedule> repSchedules = new ArrayList<>();
        List<Doctor> doctors = CsvParseUtil.parseDoctors();
        log.debug("docters : {}", doctors);
        List<String> medicalRepresentatives = null;
        try {
            medicalRepresentatives = authFeignClient.allUserDetails();
            log.debug("medicalRepresentatives : {}", medicalRepresentatives);
        }
        catch(FeignException e) {
            log.error("error-->",e);
        }

        LocalDate localDate = scheduleStartDate;
        LocalTime now = LocalTime.now();
        LocalTime one = LocalTime.of(13, 0);

        LocalDate today = LocalDate.now();
        if (scheduleStartDate.isBefore(today)) {
            log.info("End");
            return repSchedules;
        }

        if (scheduleStartDate.equals(today)) {
            if (now.isAfter(one)) {
                localDate = localDate.plusDays(1);
            }
        }

        for (int i = 0; i < doctors.size(); i++) {
            if (localDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                localDate = localDate.plusDays(1);
            }
            Doctor doctor = doctors.get(i);
            String medicalRepresentative = medicalRepresentatives.get(i % medicalRepresentatives.size());
            RepSchedule repSchedule = new RepSchedule();
            repSchedule.setId(i + 1);
            repSchedule.setRepName(medicalRepresentative);
            repSchedule.setDoctorName(doctor.getName());
            repSchedule.setDoctorContactNumber(doctor.getContactNumber());
            repSchedule.setMeetingDate(localDate);
            repSchedule.setMeetingSlot(doctor.getTime());
            repSchedule.setTreatingAilment(doctor.getTreatingAilment());
            List<String> medicinesByTreatingAilment =  medicineStockClient.getMedicineByAilment(doctor.getTreatingAilment());
            repSchedule.setMedicines(medicinesByTreatingAilment);
            log.debug("repSchedule : {}", repSchedule);
            repSchedules.add(repSchedule);
            localDate = localDate.plusDays(1);
        }
        log.debug("repSchedules : {}", repSchedules);
        log.info("End");
        return repSchedules;
    }

    public Boolean isValidSession(String token) throws TokenValidationFailedException {
        log.info("Start");
        JwtResponse response = authFeignClient.verifyToken(token);
        log.info("End");
        return true;
    }
}

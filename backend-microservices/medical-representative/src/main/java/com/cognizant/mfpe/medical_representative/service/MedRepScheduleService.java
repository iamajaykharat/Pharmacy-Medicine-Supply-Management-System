package com.cognizant.mfpe.medical_representative.service;

import com.cognizant.mfpe.medical_representative.exception.TokenValidationFailedException;
import com.cognizant.mfpe.medical_representative.model.RepSchedule;

import java.time.LocalDate;
import java.util.List;

public interface MedRepScheduleService {
    public List<RepSchedule> getRepSchedule(String token, LocalDate scheduleStartDate)
            throws TokenValidationFailedException;
}

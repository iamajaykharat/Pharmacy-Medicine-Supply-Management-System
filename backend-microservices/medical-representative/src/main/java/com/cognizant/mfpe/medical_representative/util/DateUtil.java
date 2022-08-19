package com.cognizant.mfpe.medical_representative.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
public class DateUtil {

    public static LocalDate getDate(String scheduleStartDate) {
        LocalDate localDate = null;
        try {
            log.info("Start");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            localDate = LocalDate.parse(scheduleStartDate, formatter);
            log.debug("localDate : {}", localDate);
        } catch (Exception e) {
            log.error("Date Format Exception");
        }
        log.info("End");
        return localDate;
    }
}

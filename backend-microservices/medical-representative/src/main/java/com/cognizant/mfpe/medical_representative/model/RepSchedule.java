package com.cognizant.mfpe.medical_representative.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RepSchedule {

    private int id;
    private String repName;
    private String doctorName;
    private String meetingSlot;
    private LocalDate meetingDate;
    private String doctorContactNumber;
    private List medicines;
    private String treatingAilment;


}

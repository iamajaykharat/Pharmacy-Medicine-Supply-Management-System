package com.cognizant.mfpe.medical_representative.controller;

import com.cognizant.mfpe.medical_representative.exception.InvalidDateException;
import com.cognizant.mfpe.medical_representative.exception.TokenValidationFailedException;
import com.cognizant.mfpe.medical_representative.feign.AuthenticationFeignClient;
import com.cognizant.mfpe.medical_representative.model.JwtResponse;
import com.cognizant.mfpe.medical_representative.model.RepSchedule;
import com.cognizant.mfpe.medical_representative.service.MedRepScheduleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class MedRepScheduleControllerTest {

	@Mock
	private MedRepScheduleServiceImpl medicalRepresentativeScheduleService;

	@Mock
	private AuthenticationFeignClient authenticationFeignClient;

	@InjectMocks
	private MedRepScheduleController medicalRepresentativeScheduleController;

	@Mock
	private RepSchedule repSchedule;

	@Mock
	private List<RepSchedule> medicineStockList;

	@MockBean
	private MedRepScheduleServiceImpl scheduleService;

	public List<RepSchedule> getMockRepSchedule() {
		List<RepSchedule> repSchedules = new ArrayList<>();
		String[] medicines = { "Crocin", "Percocet" };

		RepSchedule mockRepSchedule = new RepSchedule();
		mockRepSchedule.setId(1);
		mockRepSchedule.setDoctorName("D1");
		mockRepSchedule.setMeetingSlot("1 PM to 2 PM");
		mockRepSchedule.setMeetingDate(LocalDate.of(2022, 4, 20));
		mockRepSchedule.setDoctorContactNumber("9449569825");
		//mockRepSchedule.setMedicines(medicines);
		repSchedules.add(mockRepSchedule);

		return repSchedules;
	}

	@Test
	public void retrieveDetailsForCourse() throws InvalidDateException, TokenValidationFailedException {

		when(authenticationFeignClient.verifyToken("token")).thenReturn(new JwtResponse("admin", true));
		ResponseEntity<?> allMedicineStockInformation = medicalRepresentativeScheduleController.getRepSchedule("token", "2020-02-03");
		assertNotNull(allMedicineStockInformation);

	}

	@Test
	public void testGetRepScheduleFails() throws InvalidDateException, TokenValidationFailedException {
		when(authenticationFeignClient.verifyToken("token")).thenReturn(new JwtResponse("admin", false));
		ResponseEntity<?> allProducts = medicalRepresentativeScheduleController.getRepSchedule("token", "2020-02-03");
		assertNotNull(allProducts);
	}
}

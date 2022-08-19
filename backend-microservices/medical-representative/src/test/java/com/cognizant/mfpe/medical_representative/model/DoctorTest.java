package com.cognizant.mfpe.medical_representative.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DoctorTest {

	@Mock
	public Doctor doctor;

	@Before(value = "")
	public void setup() {
		log.info("Start");
		doctor = new Doctor();
		doctor.setName("D1");
		doctor.setContactNumber("8877779292");
		doctor.setTreatingAilment("General");

		log.info("End");
	}

	@Test
	public void testMedicineStockDetails() throws Exception {
		log.info("Start");
		assertEquals("D1", doctor.getName());
		assertEquals("8877779292", doctor.getContactNumber());
		assertEquals("General", doctor.getTreatingAilment());

		log.info("End");
	}

	@Test
	public void testAllArgsConstructor() {

		Doctor doctor = new Doctor(1, "D1", "8877779292", "General","1-2");
		assertEquals("D1", doctor.getName());
	}

	@Test
	public void testToStringMethod() {
		log.info("Start");
		assertEquals(doctor.toString(), doctor.toString());
		log.info("End");
	}

	@Test
	public void testSetters() {
		doctor.setName("D1");
		
		assertEquals("D1", doctor.getName());
	}

	@Test
	public void testEqualsMethod() {
		boolean equals = doctor.equals(doctor);
		assertTrue(equals);
	}

	@Test
	public void testHashCodeMethod() {
		int hashCode = doctor.hashCode();
		assertEquals(hashCode, doctor.hashCode());
	}

}
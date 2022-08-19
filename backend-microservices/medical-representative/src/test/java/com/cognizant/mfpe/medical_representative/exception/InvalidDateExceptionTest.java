package com.cognizant.mfpe.medical_representative.exception;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class InvalidDateExceptionTest {

	@Mock
	private InvalidDateException dateNotFound;

	@Test
	public void testOneArgConstructor() {
		InvalidDateException exception = new InvalidDateException("Invalid date.");
		assertEquals("Invalid date.", exception.getMessage());
	}

	@Test
	public void testNoArgConstructor() {
		InvalidDateException exception = new InvalidDateException();
		assertNull(exception.getMessage());
	}
}
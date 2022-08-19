package com.cognizant.mfpe.medical_representative.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenValidationFailedExceptionTest {

	@Mock
	private TokenValidationFailedException tokenNotValid;

	@Test
	public void testOneArgConstructor() {
		TokenValidationFailedException tokenNotValid = new TokenValidationFailedException("Token validaton failed.");
		assertEquals("Token validaton failed.", tokenNotValid.getMessage());
	}

	@Test
	public void testNoArgConstructor() {
		TokenValidationFailedException tokenNotValid = new TokenValidationFailedException();
		assertNull(tokenNotValid.getMessage());
	}
}

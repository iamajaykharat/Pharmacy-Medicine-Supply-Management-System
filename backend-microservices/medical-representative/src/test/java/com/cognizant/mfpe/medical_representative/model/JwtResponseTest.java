package com.cognizant.mfpe.medical_representative.model;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@Slf4j
public class JwtResponseTest {

	@Mock
	JwtResponse jwtResponse;

	@Before
	public void setUp() throws Exception {
		log.info("Start");
		jwtResponse = new JwtResponse("admin", true);
		log.info("End");
	}

	@Test
	public void testAuthResponseDetails() throws Exception {
		log.info("Start");
		assertEquals("admin", jwtResponse.getMessage());
		//assertEquals("adminpass", jwtResponse.getName());
		assertEquals(true, jwtResponse.isSuccess());
		log.info("End");
	}

	

	@Test
	public void testNoArgsConstructor() {
		log.info("Start");
		JwtResponse authResponse = new JwtResponse();
		assertEquals(authResponse, authResponse);
		log.info("End");
	}

	@Test
	public void testSetters() {
		jwtResponse.setMessage("Rock");
		//jwtResponse.setId("admin");
		jwtResponse.setSuccess(true);
		assertEquals("Rock", jwtResponse.getMessage());
	}

	@Test
	public void testEqualsMethod() {
		boolean equals = jwtResponse.equals(jwtResponse);
		assertTrue(equals);
	}

	@Test
	public void testHashCodeMethod() {
		int hashCode = jwtResponse.hashCode();
		assertEquals(hashCode, jwtResponse.hashCode());
	}

}

package com.cognizant.mfpe.auth.pojo;

import com.cognizant.mfpe.auth.pojo.SignupRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@SpringBootTest
public class SignUpRequestTest {

		private SignupRequest signUpRequest;

		@Before
		public void setUp() {
			signUpRequest = new SignupRequest("admin", "admin", "root");
		}

		@Test
		public void testAllArgumentConstructor() {
			SignupRequest signUpRequest = new SignupRequest("admin", "admin", "root");
			assertEquals("admin", signUpRequest.getName());
			assertEquals("admin", signUpRequest.getUsername());
			assertEquals("root", signUpRequest.getPassword());
		}

		@Test
		public void testNoArgumentConstructor() {
			SignupRequest signUpRequest = new SignupRequest();
			assertEquals(signUpRequest, signUpRequest);
		}

		@Test
		public void testSetter() {
			SignupRequest user = new SignupRequest();
			user.setUsername("admin");
			assertEquals("admin", user.getUsername());
		}

		@Test
		public void testEqualsMethod() {
			boolean assertion = signUpRequest.equals(signUpRequest);
			assertTrue(assertion);
		}

}

package com.cognizant.mfpe.auth.pojo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@SpringBootTest
public class LoginRequestTest {

	
	private LoginRequest loginRequest;
	
	@Before
	public void setUp() {
		loginRequest = new LoginRequest("admin","admin");
	}
	
	@Test
	public void testAllArgumentConstructor() {
		LoginRequest userLog =new LoginRequest("admin","admin");
		assertEquals("admin",userLog.getUsername());
	}
	
	@Test
	public void testEquals() {
		boolean res= loginRequest.equals(loginRequest);
		assertTrue(res);
	}
	
	@Test
	public void testNoArgConstructor() {
		LoginRequest loginRequest=new LoginRequest();
		assertEquals(loginRequest,loginRequest);
	}
}

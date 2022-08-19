package com.cognizant.mfpe.auth.security;

import com.cognizant.mfpe.auth.AuthServiceApplication;
import com.cognizant.mfpe.auth.pojo.LoginRequest;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = AuthServiceApplication.class,
	webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class JwtTokenProviderTest {

	private UserDetails userDetails;

	@Autowired
	JwtTokenProvider jwtUtil;
	
	private LoginRequest loginRequest = new LoginRequest("test","test");

	@Test
	public void generateTokenTest() {
		String token = jwtUtil.generateToken(loginRequest);
		assertNotNull(token);
	}

	@Test
	public void validateTokenTest() {
		String token = jwtUtil.generateToken(loginRequest);
		boolean isValid = jwtUtil.validateToken(token);
		
		assertTrue(isValid);
	}
	
	@Test
	public void invalidTokenTest() {
		String token = jwtUtil.generateToken(loginRequest);
		boolean isValid ;
		try {
			isValid = jwtUtil.validateToken(token + " ");
			
		}catch(SignatureException e) {
			isValid = false; 
		}
		
		assertFalse(isValid);
	}
		
	@Test
	public void getUsernameFromTokenTest() {
		String token = jwtUtil.generateToken(loginRequest);
		String username = jwtUtil.getUsernameFromJWT(token);
		
		assertEquals("test", username);
	}
}

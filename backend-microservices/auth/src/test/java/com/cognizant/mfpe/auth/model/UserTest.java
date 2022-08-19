package com.cognizant.mfpe.auth.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
public class UserTest {

	private User user;
	
	@Before
	public void setUp() {
		user = new User("admin","admin","root");
	}
	
	@Test
	public void testAllArgumentConstructor() {
		User user = new User("admin","admin","root");
		assertEquals("admin",user.getName());
		assertEquals("admin",user.getUsername());
		assertEquals("root",user.getPassword());
	}
	
	@Test
	public void testNoArgumentConstructor() {
		User user=new User();
		assertEquals(user,user);
	}
	
	@Test
	public void testSetter() {
		User user=new User();
		user.setUsername("admin");
		assertEquals("admin",user.getUsername());
	}
	
	@Test
	public void testEqualsMethod() {
		boolean equals = user.equals(user);
		assertTrue(equals);
	}
}

package com.cognizant.mfpe.pharmacy_supply.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PharmacistTest {
	
	@Mock
	Pharmacists pharmacists;

	@Before
	public void setUp() throws Exception {
		pharmacists = new Pharmacists();
		pharmacists.setPharmacistsId(1);
		pharmacists.setPharmacistsName("Healthy Pharmacy");
	}

	@Test
	public void testSetters() {
		pharmacists.setPharmacistsId(1);
		pharmacists.setPharmacistsName("Healthy Pharmacy");
		assertEquals("Healthy Pharmacy", pharmacists.getPharmacistsName());
	}

	@Test
	public void testGetters() {
		assertEquals("Healthy Pharmacy", pharmacists.getPharmacistsName());
	}

	@Test
	public void NoArgsConstructorTest() {
		Pharmacists pharmacists = new Pharmacists();
		assertEquals(null, pharmacists.getPharmacistsId());
	}

	@Test
	public void AllArgConstTest() {
		Pharmacists pharmacists = new Pharmacists(1, "Healthy Pharmacy");
		assertEquals("Healthy Pharmacy", pharmacists.getPharmacistsName());

	}


}

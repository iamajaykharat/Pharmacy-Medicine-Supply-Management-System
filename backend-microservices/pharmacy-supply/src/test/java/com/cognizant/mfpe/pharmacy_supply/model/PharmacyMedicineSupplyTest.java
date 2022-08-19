package com.cognizant.mfpe.pharmacy_supply.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PharmacyMedicineSupplyTest {
	
	@Mock
	PharmacyMedicineSupply pharmacyMedicineSupply;

	@Before
	public void Setup() throws Exception {
		pharmacyMedicineSupply = new PharmacyMedicineSupply();
		pharmacyMedicineSupply.setId(1);
		pharmacyMedicineSupply.setMedicineName("Crocin");
		pharmacyMedicineSupply.setPharmacyName("Healthy Pharmacy");
		pharmacyMedicineSupply.setSupplyCount(10);
	}

	@Test
	public void testSetters() {
		pharmacyMedicineSupply.setId(1);
		pharmacyMedicineSupply.setMedicineName("Orthoherb");
		assertEquals("Orthoherb", pharmacyMedicineSupply.getMedicineName());
	}

	@Test
	public void testGetters() {
		assertEquals("Crocin", pharmacyMedicineSupply.getMedicineName());
	}

	@Test
	public void NoArgsConstructorTest() {
		PharmacyMedicineSupply supply = new PharmacyMedicineSupply();
		assertEquals(null, supply.getMedicineName());
	}

	@Test
	public void AllArgConstTest() {
		PharmacyMedicineSupply supply = new PharmacyMedicineSupply(1, "Healthy Pharmacy", "Crocin", 10);
		assertEquals("Crocin", supply.getMedicineName());
		assertEquals("Healthy Pharmacy", supply.getPharmacyName());

	}

	@Test
	public void testToString() {
		assertEquals(
				"PharmacyMedicineSupply(id=" + pharmacyMedicineSupply.getId() + ", pharmacyName=" + pharmacyMedicineSupply.getPharmacyName()
						+ ", medicineName=" + pharmacyMedicineSupply.getMedicineName() + ", supplyCount=" + pharmacyMedicineSupply.getSupplyCount() + ")",
						pharmacyMedicineSupply.toString());
	}
}

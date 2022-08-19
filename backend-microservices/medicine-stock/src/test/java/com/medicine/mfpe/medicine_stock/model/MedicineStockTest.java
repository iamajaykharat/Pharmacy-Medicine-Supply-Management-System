package com.medicine.mfpe.medicine_stock.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import com.cognizant.mfpe.medicine_stock.model.MedicineStock;
import org.junit.Before;
import org.junit.Test;


public class MedicineStockTest {
	
	private MedicineStock medicineStock;
	
	
	@Before
	public void setStockUsingArgsConstructor() {
		medicineStock = 
				new MedicineStock(1,"Crosin","chemical names","General",new Date(),200,"Nagpur"); 
	}

	@Test
	public void medicineStockTest() throws Exception{
		assertNotNull(medicineStock);
		assertEquals(200, (int) medicineStock.getNumberOfTabletsInStock());
		assertEquals("Crosin", medicineStock.getMedicineName());
		assertEquals("chemical names", medicineStock.getChemicalComposition());
		assertEquals("General", medicineStock.getTargetAilment());
		assertEquals("Nagpur", medicineStock.getPharmacyName());
		assertEquals((new Date()), medicineStock.getDateOfExpiry());
	}
	
	
	@Test
	public void noArgsConstrutorTest() {		
		 MedicineStock medicineStock1 = new MedicineStock();
		
		assertNotNull(medicineStock1);		
		
		medicineStock1.setMedicineId(1);
		medicineStock1.setMedicineName("Crosin");
		medicineStock1.setChemicalComposition("chemical names");
		medicineStock1.setDateOfExpiry(new Date());
		medicineStock1.setPharmacyName("Nagpur");
		medicineStock1.setTargetAilment("General");
		medicineStock1.setNumberOfTabletsInStock(200);
		
		assertEquals(medicineStock.toString(), medicineStock1.toString());
	}
	
	
}


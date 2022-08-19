package com.medicine.mfpe.medicine_stock.service;

import com.cognizant.mfpe.medicine_stock.MedicineStockApplication;
import com.cognizant.mfpe.medicine_stock.model.MedicineStock;
import com.cognizant.mfpe.medicine_stock.service.MedicineStockRepoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@SpringBootTest(classes = MedicineStockApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MedicineStockRepoServiceTest {
	
	@Autowired
	private MedicineStockRepoService medicineStockService;
	

	@Mock
	private MedicineStock medicineStock;
	
	@Test
	public void getMedicineStockInformationTest() {
		List<MedicineStock> medicineStockList = medicineStockService.getMedicineStockDetails();
		assertNotNull(medicineStockList);
	}
	
	@Test
	public void getStockByTargetAliment() {
		List<String> medicineStock = null;
		medicineStock = medicineStockService.getMedicineStockByAilment("General");
		assertNotNull(medicineStock);
	}
}

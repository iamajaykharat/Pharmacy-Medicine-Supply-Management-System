package com.medicine.mfpe.medicine_stock;

import com.cognizant.mfpe.medicine_stock.MedicineStockApplication;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MedicineStockApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
class MedicineStockApplicationTest {

	@Test
	void contextLoads() {
	}

}

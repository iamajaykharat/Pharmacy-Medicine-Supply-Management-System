package com.cognizant.mfpe.pharmacy_supply.service;

import com.cognizant.mfpe.pharmacy_supply.exception.TokenValidationFailedException;
import com.cognizant.mfpe.pharmacy_supply.feign.AuthenticationFeignClient;
import com.cognizant.mfpe.pharmacy_supply.feign.MedicineStockFeign;
import com.cognizant.mfpe.pharmacy_supply.model.ApiResponse;
import com.cognizant.mfpe.pharmacy_supply.model.MedicineDemand;
import com.cognizant.mfpe.pharmacy_supply.model.MedicineStock;
import com.cognizant.mfpe.pharmacy_supply.model.PharmacyMedicineSupply;
import com.cognizant.mfpe.pharmacy_supply.repository.PharmacistsRepository;
import feign.FeignException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PharmacySupplyServiceTest { 
	
	@Autowired
	private PharmacySupplyService pharmacySupplyService;

	@Mock
	private MedicineDemand medicineDemand;

	@Mock
	private PharmacyMedicineSupply pharmacyMedicineSupply;

	@Mock
	private MedicineStock medicineStock;

	@MockBean
	private AuthenticationFeignClient authFeignClient;
	
	@Mock
	private MedicineStockFeign medicineStockFeign;
	
	@Mock
	private PharmacistsRepository pharmacistsRepository;
		
	private List<MedicineDemand> medicineDemandList = new ArrayList<MedicineDemand>();
	
	@Before
	public void setup() { 
		medicineDemand = new MedicineDemand(1, "Crocin", 100);
		medicineDemandList.add(medicineDemand);
		pharmacyMedicineSupply = new PharmacyMedicineSupply(1, "Healthy Pharmacy", "Crocin", 200);
		medicineStock = new MedicineStock(1, "Crocin", "digoxin", "General",new java.util.Date(2022 - 9 - 12), 500, "Healthy Pharmacy");
	}
	
	
	@Test(expected = TokenValidationFailedException.class)
	public void testValidateTokenFail() {
		when(authFeignClient.validateToken("token"))
				.thenReturn(new ApiResponse(false,"Token Invalid"));
		pharmacySupplyService.validate("token");
	}
	
	@Test
	public void getStockDetailsTest() throws FeignException {
		FeignException thrown = assertThrows(FeignException.class,
				() -> medicineStockFeign.getMedicineStock());
		assertTrue(thrown.getMessage().contains("Medicine Stock not Found"));
	}
}

package com.medicine.mfpe.medicine_stock.controlller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.cognizant.mfpe.medicine_stock.MedicineStockApplication;
import com.cognizant.mfpe.medicine_stock.controller.MedicineStockController;
import com.cognizant.mfpe.medicine_stock.model.MedicineStock;
import com.medicine.mfpe.medicine_stock.service.MedicineStockRepoServiceTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = MedicineStockApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
@AutoConfigureMockMvc
public class MedicineStockControllerTest {

    @InjectMocks
    private MedicineStockController medicineStockController;

    @Mock
    private MedicineStock medicineStock;

    @Mock
    private List<MedicineStock> medicineStockList;

    @Mock
    private MedicineStockRepoServiceTest medicineStockService;

    @Test
    public void contextLoads() {
        assertNotNull(medicineStockController);
    }

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup() {
        medicineStock = new MedicineStock();
        medicineStock.setMedicineId(1);
        medicineStock.setPharmacyName("Crocin");
        medicineStock.setChemicalComposition("disaccharide gentiobiose, dicarboxylic acid crocetin");
        medicineStock.setTargetAilment("General");
        medicineStock.setPharmacyName("Healthy Pharmacy");
        medicineStock.setDateOfExpiry(new java.util.Date(2022 - 9 - 12));
        medicineStock.setNumberOfTabletsInStock(200);
    }

    @Test
    public void getMedicineStockInformationTest() throws Exception {
        this.mockMvc.perform(
                get("/medicine/stock")
        ).andExpect(
                status().isOk()
        );
    }

    @Test
    public void getMedicineByTreatingAilmentTest() throws Exception {
        this.mockMvc.perform(
                get("/medicineByAilment/General")
        ).andExpect(status().isOk());
    }


    @Test
    public void updateMedicineStockTest() throws Exception {
        this.mockMvc.perform(
                        post("/updateStock")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("[{\r\n"
                                        + "        \"medicineName\" : \"Crocin\",\r\n"
                                        + "        \"supplyCount\" : 2000\r\n"
                                        + "    }]"
                                )
                )
                .andExpect(status().isOk());
    }

    @Test
    public void invalidUpdateMedicineStockTest() throws Exception {
        this.mockMvc.perform(
                        put("/update-stock/Crosin/1000")
                )
                .andExpect(status().is(404));
    }
}


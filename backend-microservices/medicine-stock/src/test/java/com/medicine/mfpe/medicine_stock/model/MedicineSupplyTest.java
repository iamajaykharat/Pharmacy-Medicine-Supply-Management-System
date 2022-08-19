package com.medicine.mfpe.medicine_stock.model;

import com.cognizant.mfpe.medicine_stock.pojo.MedicineSupply;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MedicineSupplyTest {

    private MedicineSupply medicineSupply;

    @Before
    public void setStockUsingArgsConstructor() {
        medicineSupply =
                new MedicineSupply("Crocin", 200);
    }

    @Test
    public void medicineStockTest() throws Exception {
        assertNotNull(medicineSupply);
        assertEquals("Crocin", medicineSupply.getMedicineName());
        assertEquals(new Integer(200), medicineSupply.getSupplyCount());
    }


    @Test
    public void noArgsConstrutorTest() {
        MedicineSupply medicineSupply = new MedicineSupply();
        assertNotNull(medicineSupply);
        medicineSupply.setSupplyCount(100);
        medicineSupply.setMedicineName("Crocin");
    }
}

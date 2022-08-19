package com.cognizant.mfpe.pharmacy_supply.service;

import com.cognizant.mfpe.pharmacy_supply.exception.MedicineNotFoundException;
import com.cognizant.mfpe.pharmacy_supply.exception.TokenValidationFailedException;
import com.cognizant.mfpe.pharmacy_supply.feign.AuthenticationFeignClient;
import com.cognizant.mfpe.pharmacy_supply.feign.MedicineStockFeign;
import com.cognizant.mfpe.pharmacy_supply.model.ApiResponse;
import com.cognizant.mfpe.pharmacy_supply.model.MedicineDemand;
import com.cognizant.mfpe.pharmacy_supply.model.MedicineStock;
import com.cognizant.mfpe.pharmacy_supply.model.MedicineStockToUpdate;
import com.cognizant.mfpe.pharmacy_supply.model.Pharmacists;
import com.cognizant.mfpe.pharmacy_supply.model.PharmacyMedicineSupply;
import com.cognizant.mfpe.pharmacy_supply.repository.MedicineDemandRepository;
import com.cognizant.mfpe.pharmacy_supply.repository.PharmacistsRepository;
import com.cognizant.mfpe.pharmacy_supply.repository.PharmacyMedicineSupplyRepository;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PharmacySupplyService {

    @Autowired
    private AuthenticationFeignClient authFeign;

    @Autowired
    private MedicineStockFeign stockFeign;

    @Autowired
    private MedicineDemandRepository medicineDemandRepo;

    @Autowired
    private PharmacyMedicineSupplyRepository pharmacyMedicineSupplyRepo;

    @Autowired
    private PharmacistsRepository pharmasRepository;

    private List<MedicineStock> medicineStockInfo = new ArrayList<>();

    private List<MedicineStockToUpdate> updateMedicineStockInfo = new ArrayList<>();

    public ApiResponse validate(String token) {
        try {
            return authFeign.validateToken(token);
        } catch (FeignException e) {
            throw new TokenValidationFailedException("Invalid token");
        }
    }

    @Transactional
    public List<PharmacyMedicineSupply> getPharmacySupplyCount(List<MedicineDemand> medicineDemandList) throws MedicineNotFoundException {
        getStockDetails();
        List<PharmacyMedicineSupply> pharmacySupplyList = new ArrayList<>();
        for (MedicineDemand medicineDemand : medicineDemandList) {
            MedicineStock medicineStock = getStockByName(medicineDemand);
            pharmacySupplyList.addAll(
                    retrievePharmacists(
                            medicineDemand.getDemandCount(),
                            medicineDemand.getMedicineName(),
                            medicineStock.getNumberOfTabletsInStock()
                    )
            );
        }
        log.info("Pharmacy Supply List ----> ", pharmacySupplyList);
        pharmacyMedicineSupplyRepo.saveAll(pharmacySupplyList);
        try {
            stockFeign.updateMedicineStock(updateMedicineStockInfo);
        } catch (FeignException e) {
            log.error("Error while connecting to Medicine Stock Microservice");
        }
        return pharmacySupplyList;
    }


    public List<PharmacyMedicineSupply> retrievePharmacists(int demandCount, String medicineName, int stockCount) {
        List<PharmacyMedicineSupply> pharmacyMedicineSupply = new ArrayList<>();
        List<Pharmacists> pharmacists = pharmasRepository.findAll();
        int supplyCount;
        if (demandCount > stockCount) {
            supplyCount = stockCount;
        } else {
            supplyCount = demandCount;
        }
        int supplyCountForPharmacists = 0;
        int remainingSupplyCountForPharmacists = 0;
        int remainingDemand = supplyCount;
        if (pharmacists.size() <= demandCount) {
            supplyCount = supplyCount - (supplyCount % pharmacists.size());
            remainingDemand -= supplyCount;
            remainingSupplyCountForPharmacists = (int) Math.ceil((double) remainingDemand / pharmacists.size());
            supplyCountForPharmacists = supplyCount / pharmacists.size();
            System.out.println("remaining demand" + remainingDemand);
            System.out.println("remaining count" + remainingSupplyCountForPharmacists);
            updateMedicineStockInfo.add(new MedicineStockToUpdate(medicineName, stockCount - (supplyCount + remainingDemand)));
            medicineDemandRepo.save(new MedicineDemand(medicineName, demandCount, LocalDate.now(), supplyCount + remainingDemand));
        } else {
            supplyCount = supplyCount % pharmacists.size();
            supplyCountForPharmacists = (int) Math.ceil((double) supplyCount / pharmacists.size());
            updateMedicineStockInfo.add(new MedicineStockToUpdate(medicineName, stockCount - (supplyCount)));
            medicineDemandRepo.save(new MedicineDemand(medicineName, demandCount, LocalDate.now(), supplyCount));
        }
        for (Pharmacists pharmacist : pharmacists) {
            if (supplyCount == 0) {
                break;
            } else {
                pharmacyMedicineSupply.add(new PharmacyMedicineSupply(
                        pharmacist.getPharmacistsName(),
                        medicineName,
                        supplyCountForPharmacists + ((remainingDemand <= 0) ? 0 : remainingSupplyCountForPharmacists)
                ));
                remainingDemand -= remainingSupplyCountForPharmacists;
                System.out.println("remaining demand" + remainingDemand);
                supplyCount -= supplyCountForPharmacists;
            }
        }
        return pharmacyMedicineSupply;
    }


    public List<MedicineDemand> getMedicineDemand() {
        try {
            return medicineDemandRepo.findTop20ByOrderByIdDesc();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }


    public MedicineStock getStockByName(MedicineDemand medicineDemand) {
        for (MedicineStock medicineStock : medicineStockInfo) {
            System.out.println(medicineStock);
            if (medicineStock.getMedicineName().equalsIgnoreCase(medicineDemand.getMedicineName())) {
                return medicineStock;
            }
        }
        return null;
    }


    public void getStockDetails() throws MedicineNotFoundException {
        try {
            medicineStockInfo = stockFeign.getMedicineStock();
        } catch (FeignException e) {
            throw new MedicineNotFoundException("Medicine Stock not Found");
        }
    }

    public List<PharmacyMedicineSupply> getPharmacySupplyDetails() {
        return pharmacyMedicineSupplyRepo.findTop20ByOrderByIdDesc();
    }
}

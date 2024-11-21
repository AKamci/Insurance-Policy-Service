package PolicyProject.policyService.application.service.ModelFactory;

import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;

import static PolicyProject.policyService.application.service.ModelFactory.HouseModelFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class HouseModelFactoryTest {

    @Test
    void testCreateHouseModelWithHouseId() {
        HouseModel houseModel = createHouseModelWithHouseId(1L);
        assertEquals(1L, houseModel.id());
        assertEquals(null, houseModel.coverageCode());
        assertEquals(null, houseModel.number());
        assertEquals(null, houseModel.squareMeters());
        assertEquals(null, houseModel.customer());
        assertEquals(null, houseModel.building());
        assertEquals(null, houseModel.tckn());
        assertEquals(null, houseModel.Amount());
    }

    @Test
    void testCreateNewHouseModelFromExisting() {
        HouseModel existingHouseModel = new HouseModel(1L, 2, 3, 4, null, null, "tckn", 5L);
        HouseModel newHouseModel = createNewHouseModelFromExisting(existingHouseModel, 6L);
        assertEquals(existingHouseModel.id(), newHouseModel.id());
        assertEquals(existingHouseModel.coverageCode(), newHouseModel.coverageCode());
        assertEquals(existingHouseModel.number(), newHouseModel.number());
        assertEquals(existingHouseModel.squareMeters(), newHouseModel.squareMeters());
        assertEquals(existingHouseModel.customer(), newHouseModel.customer());
        assertEquals(existingHouseModel.building(), newHouseModel.building());
        assertEquals(existingHouseModel.tckn(), newHouseModel.tckn());
        assertEquals(6L, newHouseModel.Amount());
    }

    @Test
    void testCreateNewHouseModelFromExistingWithCoverageCode() {
        HouseModel existingHouseModel = new HouseModel(1L, 2, 3, 4, null, null, "tckn", 5L);
        HouseModel newHouseModel = createNewHouseModelFromExistingWithCoverageCode(existingHouseModel, 7);
        assertEquals(existingHouseModel.id(), newHouseModel.id());
        assertEquals(7, newHouseModel.coverageCode());
        assertEquals(existingHouseModel.number(), newHouseModel.number());
        assertEquals(existingHouseModel.squareMeters(), newHouseModel.squareMeters());
        assertEquals(existingHouseModel.customer(), newHouseModel.customer());
        assertEquals(existingHouseModel.building(), newHouseModel.building());
        assertEquals(existingHouseModel.tckn(), newHouseModel.tckn());
        assertEquals(existingHouseModel.Amount(), newHouseModel.Amount());
    }

}


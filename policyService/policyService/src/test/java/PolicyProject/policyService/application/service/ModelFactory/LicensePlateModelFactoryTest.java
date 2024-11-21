package PolicyProject.policyService.application.service.ModelFactory;

import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import org.springframework.stereotype.Service;
import org.junit.jupiter.api.Test;

import static PolicyProject.policyService.application.service.ModelFactory.LicensePlateModelFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Service
public class LicensePlateModelFactoryTest {


    @Test
    public void testCreateLicensePlateModelWithAmount() {
        LicensePlateModel model = new LicensePlateModel(
                1L, "ABC123", null, null, 100,
                null, null, 2000L
        );
        LicensePlateModel newModel = createLicensePlateModelWithAmount(model, 3000L);
        assertNotNull(newModel);
        assertEquals("ABC123", newModel.plate());
        assertEquals(3000L, newModel.amount());
    }

    @Test
    public void testCreateLicensePlateModelFromExisting() {
        LicensePlateModel model = new LicensePlateModel(
                1L, "ABC123", null, null, 100,
                null, null, 2000L
        );
        LicensePlateModel newModel = createLicensePlateModelFromExisting(model);
        assertNotNull(newModel);
        assertEquals("ABC123", newModel.plate());
        assertEquals(0L, newModel.amount());
    }

    @Test
    public void testCreateLicensePlateModelWithCoverageCode() {
        LicensePlateModel model = new LicensePlateModel(
                1L, "ABC123", null, null, 100,
                null, null, 2000L
        );
        LicensePlateModel newModel = createLicensePlateModelWithCoverageCode(model, 200);
        assertNotNull(newModel);
        assertEquals("ABC123", newModel.plate());
        assertEquals(200, newModel.coverageCode());
    }

}

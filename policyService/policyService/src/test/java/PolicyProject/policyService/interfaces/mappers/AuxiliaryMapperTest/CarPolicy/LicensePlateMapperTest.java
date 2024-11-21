package PolicyProject.policyService.interfaces.mappers.AuxiliaryMapperTest.CarPolicy;

import PolicyProject.policyService.domain.dto.request.LicensePlateRequest.GetPlateWithCustomerRequest;
import PolicyProject.policyService.domain.dto.response.LicensePlateResponse.GetPlateWithCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy.LicensePlateMapper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LicensePlateMapperTest {

    private final LicensePlateMapper mapper = LicensePlateMapper.INSTANCE;

    @Test
    void testGetPlateWithCustomerRequestToLicensePlateModel() {
        GetPlateWithCustomerRequest request = new GetPlateWithCustomerRequest("XYZ123", 1);

        LicensePlateModel model = mapper.getPlateWithCustomerRequestToLicensePlateModel(request);

        assertNotNull(model);
        assertEquals(request.plate(), model.plate());
        assertEquals(request.coverageCode(), model.coverageCode());
    }
    @Test
    void testLicensePlateModelToGetPlateWithCustomerResponse() {
        LicensePlateModel model = new LicensePlateModel(
                1L,
                "XYZ123",
                new Car(),
                new Customer(),
                1,
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                10000L
        );

        GetPlateWithCustomerResponse response = mapper.LicensePlateModelToGetPlateWithCustomerResponse(model);

        assertNotNull(response);
        assertEquals(model.id(), response.id());
        assertEquals(model.plate(), response.plate());
        assertEquals(model.coverageCode(), response.coverageCode());
        assertEquals(model.car(), response.car());
        assertEquals(model.customer(), response.customer());
        assertEquals(model.amount(), response.amount());
    }
    @Test
    void testLicensePlateModelToCustomerEntity() {
        LicensePlateModel model = new LicensePlateModel(
                1L,
                "XYZ123",
                new Car(),
                new Customer(),
                1,
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                10000L
        );

        LicensePlate entity = mapper.LicensePlateModelToCustomerEntity(model);

        assertNotNull(entity);
        assertEquals(model.id(), entity.getId());
        assertEquals(model.plate(), entity.getPlate());
        assertEquals(model.car(), entity.getCar());
        assertEquals(model.customer(), entity.getCustomer());
    }
    @Test
    void testLicensePlateEntityToLicensePlateModel() {
        Car car = new Car();
        Customer customer = new Customer();
        LicensePlate licensePlate = LicensePlate.builder()
                .id(1L)
                .plate("XYZ123")
                .car(car)
                .customer(customer)
                .build();

        LicensePlateModel model = mapper.licensePlateEntityToLicensePlateModel(licensePlate);

        assertNotNull(model);
        assertEquals(licensePlate.getId(), model.id());
        assertEquals(licensePlate.getPlate(), model.plate());
        assertEquals(licensePlate.getCar(), model.car());
        assertEquals(licensePlate.getCustomer(), model.customer());
    }
}

package PolicyProject.policyService.interfaces.mappers.AuxiliaryMapperTest.HealthPolicy;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.domain.dto.request.PersonalHealthRequest.GetPersonalHealthWithCustomerRequest;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.GetPersonalHealthWithCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.HealthPolicy.PersonalHealthMapper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import PolicyProject.policyService.domain.dto.request.PersonalHealthRequest.CreatePersonalHealthRequest;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.CreatePersonalHealthResponse;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import org.junit.jupiter.api.BeforeEach;

public class PersonalHealthMapperTest {

    private final PersonalHealthMapper mapper = PersonalHealthMapper.INSTANCE;



    @Test
    public void testGetPersonalHealthEntityToPersonalHealthModel_withCompleteEntity() {
        Customer customer = new Customer();
        customer.setTckn("12345678910");

        PersonalHealth entity = PersonalHealth.builder()
                .id(1L)
                .customer(customer)
                .height(170)
                .weight(70.0)
                .bmi(24.2)
                .bloodType(BloodType.A_POSITIVE)
                .build();

        PersonalHealthModel model = mapper.getPersonalHealthEntityToPersonalHealthModel(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.id());
        assertEquals(entity.getCustomer().getTckn(), model.tckn());
        assertEquals(entity.getHeight(), model.height());
        assertEquals(entity.getWeight(), model.weight());
        assertEquals(entity.getBmi(), model.bmi());
        assertEquals(entity.getBloodType(), model.bloodType());
    }
    @Test
    public void testGetPersonalHealthEntityToPersonalHealthModel_withNullCustomer() {
        PersonalHealth entity = PersonalHealth.builder()
                .id(1L)
                .height(170)
                .weight(70.0)
                .bmi(24.2)
                .bloodType(BloodType.A_POSITIVE)
                .build();

        PersonalHealthModel model = mapper.getPersonalHealthEntityToPersonalHealthModel(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.id());
        assertNull(model.tckn());
        assertEquals(entity.getHeight(), model.height());
        assertEquals(entity.getWeight(), model.weight());
        assertEquals(entity.getBmi(), model.bmi());
        assertEquals(entity.getBloodType(), model.bloodType());
    }
    @Test
    public void testGetPersonalHealthWithCustomerRequestToPersonalHealthModel() {
        GetPersonalHealthWithCustomerRequest request = new GetPersonalHealthWithCustomerRequest("12345678910", 100);
        PersonalHealthModel model = mapper.getGetPersonalHealthWithCustomerRequestToPersonalHealthModel(request);

        assertNotNull(model);
        assertEquals("12345678910", model.tckn());
        assertEquals(100, model.coverageCode());
    }
    @Test
    public void testCreatePersonalHealthRequestToPersonalHealthModel() {
        CreatePersonalHealthRequest request = new CreatePersonalHealthRequest(
                "12345678910",
                100,
                170,
                70.0,
                24.2,
                BloodType.A_POSITIVE,
                true,
                false,
                false,
                false,
                false
        );
        PersonalHealthModel model = mapper.createPersonalHealthRequestToPersonalHealthModel(request);

        assertNotNull(model);
        assertEquals("12345678910", model.tckn());
        assertEquals(100, model.coverageCode());
        assertEquals(170, model.height());
        assertEquals(Double.valueOf(70.0), model.weight());
        assertEquals(Double.valueOf(24.2), model.bmi());
        assertEquals(BloodType.A_POSITIVE, model.bloodType());
        assertEquals(true, model.alcoholConsumption());
        assertEquals(false, model.smokeConsumption());
        assertEquals(false, model.isPregnant());
        assertEquals(false, model.hasDisability());
        assertEquals(false, model.hasPreviousSurgeries());
    }
    @Test
    public void testGetPersonalHealthModelToGetPersonalHealthWithCustomerResponse() {
        PersonalHealthModel model = new PersonalHealthModel(
                1L,
                "12345678910",
                null,
                100,
                null,
                170,
                70.0,
                24.2,
                BloodType.A_POSITIVE,
                true,
                false,
                false,
                false,
                false,
                1000L
        );

        GetPersonalHealthWithCustomerResponse response = mapper.getPersonalHealthModelToGetPersonalHealthWithCustomerResponse(model);

        assertNotNull(response);
        assertEquals(model.id(), response.id());
        assertEquals(model.height(), response.height());
        assertEquals(model.weight(), response.weight());
        assertEquals(model.bmi(), response.bmi());
        assertEquals(model.bloodType().name(), response.bloodType());
        assertEquals(model.alcoholConsumption(), response.alcoholConsumption());
        assertEquals(model.smokeConsumption(), response.smokeConsumption());
        assertEquals(model.isPregnant(), response.isPregnant());
        assertEquals(model.hasDisability(), response.hasDisability());
        assertEquals(model.hasPreviousSurgeries(), response.hasPreviousSurgeries());
        assertEquals(model.Amount(), response.Amount());
    }
    @Test
    public void testCreatePersonalHealthModelToPersonalHealthResponse() {
        PersonalHealthModel model = new PersonalHealthModel(
                1L,
                "12345678910",
                null,
                100,
                null,
                170,
                70.0,
                24.2,
                BloodType.A_POSITIVE,
                true,
                false,
                false,
                false,
                false,
                1000L
        );

        CreatePersonalHealthResponse response = mapper.CreatePersonalHealthModelToPersonalHealthResponse(model);

        assertNotNull(response);
        assertEquals(model.tckn(), response.tckn());
        assertEquals(model.id(), response.id());
    }
    @Test
    public void testGetPersonalHealthModelToPersonalHealthEntity_withCompleteModel() {
        PersonalHealthModel model = new PersonalHealthModel(
                1L,
                "12345678910",
                null,
                100,
                null,
                170,
                70.0,
                24.2,
                BloodType.A_POSITIVE,
                true,
                false,
                false,
                false,
                false,
                1000L
        );

        PersonalHealth entity = mapper.getPersonalHealthModelToPersonalHealthEntity(model);

        assertNotNull(entity);
        assertEquals(model.id(), entity.getId());
        assertEquals(model.tckn(), entity.getCustomer().getTckn());
        assertEquals(model.height(), entity.getHeight());
        assertEquals(model.weight(), entity.getWeight());
        assertEquals(model.bmi(), entity.getBmi());
        assertEquals(model.bloodType(), entity.getBloodType());
    }
    @Test
    public void testGetPersonalHealthModelToPersonalHealthEntity_withNullCustomer() {
        PersonalHealthModel model = new PersonalHealthModel(
                1L,
                null,
                null,
                100,
                null,
                170,
                70.0,
                24.2,
                BloodType.A_POSITIVE,
                true,
                false,
                false,
                false,
                false,
                1000L
        );

        PersonalHealth entity = mapper.getPersonalHealthModelToPersonalHealthEntity(model);

        assertNotNull(entity);
        assertNull(entity.getCustomer().getTckn());

        assertEquals(model.id(), entity.getId());
        assertEquals(model.height(), entity.getHeight());
        assertEquals(model.weight(), entity.getWeight());
        assertEquals(model.bmi(), entity.getBmi());
        assertEquals(model.bloodType(), entity.getBloodType());
    }
    @Test
    public void testGetPersonalHealthEntityToPersonalHealthModel() {
        Customer customer = new Customer();
        customer.setTckn("12345678910");

        PersonalHealth entity = PersonalHealth.builder()
                .id(1L)
                .customer(customer)
                .height(170)
                .weight(70.0)
                .bmi(24.2)
                .bloodType(BloodType.A_POSITIVE)
                .build();

        PersonalHealthModel model = mapper.getPersonalHealthEntityToPersonalHealthModel(entity);

        assertNotNull(model);
        assertEquals(entity.getId(), model.id());
        assertEquals(entity.getCustomer().getTckn(), model.tckn());
        assertEquals(entity.getHeight(), model.height());
        assertEquals(entity.getWeight(), model.weight());
        assertEquals(entity.getBmi(), model.bmi());
        assertEquals(entity.getBloodType(), model.bloodType());
    }
}

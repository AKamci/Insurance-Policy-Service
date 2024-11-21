package PolicyProject.policyService.interfaces.mappers.PolicyMapperTest;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.request.CarPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.CarPolicyResponse.*;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.HealthPolicy.PersonalHealthMapper;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.CarPolicyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class CarPolicyMapperTest {

    private final CarPolicyMapper mapper = CarPolicyMapper.INSTANCE;

    @Test
    void testCartPolicyModelListToGetCarPolicyResponseList() {
        CarPolicyModel model1 = new CarPolicyModel(
                1L,
                LocalDate.now().minusDays(1),
                "Full Coverage",
                2,
                null,
                50000.0,
                1L,
                "AB12345",
                "12345678901",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                PolicyState.ACTIVE,
                10,
                1,
                null,
                null,
                null
        );

        CarPolicyModel model2 = new CarPolicyModel(
                2L,
                LocalDate.now(),
                "Basic Coverage",
                3,
                null,
                30000.0,
                2L,
                "CD67890",
                "98765432109",
                LocalDate.now().minusDays(5),
                LocalDate.now().plusDays(5),
                PolicyState.CANCELLED,
                5,
                2,
                null,
                null,
                null
        );

        List<CarPolicyModel> models = List.of(model1, model2);

        List<GetCarPolicyResponse> responses = mapper.cartPolicyModelListToGetCarPolicyResponseList(models);

        assertNotNull(responses);
        assertEquals(2, responses.size());
        assertEquals(model1.policyId(), responses.get(0).policyId());
        assertEquals(model2.policyId(), responses.get(1).policyId());
    }
    @Test
    void testCartPolicyModelListToGetCarPolicyResponseList_withNullList() {
        List<GetCarPolicyResponse> responses = mapper.cartPolicyModelListToGetCarPolicyResponseList(null);
        assertNull(responses);
    }
    @Test
    void testCartPolicyModelListToGetCarPolicyResponseList_withEmptyList() {
        List<GetCarPolicyResponse> responses = mapper.cartPolicyModelListToGetCarPolicyResponseList(List.of());
        assertNotNull(responses);
        assertTrue(responses.isEmpty());
    }
    @Test
    void testCreateCarPolicyRequestToCarPolicyModel() {
        CreateCarPolicyRequest request = new CreateCarPolicyRequest(
                LocalDate.now().plusDays(1),
                1,
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(3),
                "12345678901",
                10000L,
                "AB12345"
        );

        CarPolicyModel model = mapper.createCarPolicyRequestToCarPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyOfferDate(), model.policyOfferDate());
        assertEquals(request.coverageCode(), model.coverageCode());
        assertEquals(request.policyStartDate(), model.policyStartDate());
        assertEquals(request.policyEndDate(), model.policyEndDate());
        assertEquals(request.tckn(), model.tckn());
        assertEquals(request.policyAmount(), model.policyAmount());
        assertEquals(request.licensePlateNumber(), model.licensePlateNumber());
    }
    @Test
    void testCreateCarPolicyRequestToCarPolicyModel_withNullRequest() {
        CarPolicyModel model = mapper.createCarPolicyRequestToCarPolicyModel(null);
        assertNull(model);
    }
    @Test
    void testSetStateCarPolicyRequestToCarPolicyModel() {
        SetCarPolicyStatusRequest request = new SetCarPolicyStatusRequest(1L);

        CarPolicyModel model = mapper.setStateCarPolicyRequestToCarPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
    }
    @Test
    void testSetStateCarPolicyRequestToCarPolicyModel_withNullRequest() {
        CarPolicyModel model = mapper.setStateCarPolicyRequestToCarPolicyModel(null);
        assertNull(model);
    }
    @Test
    void testGetCarPolicyRequestWPlateToCarPolicyModel() {
        GetCarPolicyWPlateRequest request = new GetCarPolicyWPlateRequest("12345678901", "AB12345");

        CarPolicyModel model = mapper.getCarPolicyRequestWPlateTocarPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.tckn(), model.tckn());
        assertEquals(request.licensePlateNumber(), model.licensePlateNumber());
    }
    @Test
    void testGetCarPolicyRequestWPlateToCarPolicyModel_withNullRequest() {
        CarPolicyModel model = mapper.getCarPolicyRequestWPlateTocarPolicyModel(null);
        assertNull(model);
    }
    @Test
    void testUpdateCarPolicyRequestToCarPolicyModel() {
        UpdateCarPolicyRequest request = new UpdateCarPolicyRequest(
                1L,
                LocalDate.now().minusDays(1),
                "Full Coverage",
                PolicyState.ACTIVE,
                2,
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                50000.0
        );

        CarPolicyModel model = mapper.updateCarPolicyRequestToCarPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
        assertEquals(request.policyOfferDate(), model.policyOfferDate());
        assertEquals(request.policyDescription(), model.policyDescription());
        assertEquals(request.state(), model.state());
        assertEquals(request.coverage(), model.coverageCode());
        assertEquals(request.policyStartDate(), model.policyStartDate());
        assertEquals(request.policyEndDate(), model.policyEndDate());
        assertEquals(request.policyAmount(), model.policyAmount());
    }
    @Test
    void testUpdateCarPolicyRequestToCarPolicyModel_withNullRequest() {
        CarPolicyModel model = mapper.updateCarPolicyRequestToCarPolicyModel(null);
        assertNull(model);
    }
    @Test
    void testGetCarPolicyRequestToCarPolicyModel() {
        GetCarPolicyRequest request = new GetCarPolicyRequest(1L);

        CarPolicyModel model = mapper.getCarPolicyRequestTocarPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
    }
    @Test
    void testGetCarPolicyRequestToCarPolicyModel_withNullRequest() {
        CarPolicyModel model = mapper.getCarPolicyRequestTocarPolicyModel(null);
        assertNull(model);
    }
    @Test
    void testDeleteCarPolicyRequestToCarPolicyModel() {
        DeleteCarPolicyRequest request = new DeleteCarPolicyRequest(1L);

        CarPolicyModel model = mapper.deleteCarPolicyRequestToCarPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
    }
    @Test
    void testDeleteCarPolicyRequestToCarPolicyModel_withNullRequest() {
        CarPolicyModel model = mapper.deleteCarPolicyRequestToCarPolicyModel(null);
        assertNull(model);
    }
    @Test
    void testGetCustomerCarPoliciesToCarPolicyModel() {
        GetCustomerCarPoliciesRequest request = new GetCustomerCarPoliciesRequest("12345678901");

        CarPolicyModel model = mapper.getCustomerCarPoliciesToCarPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.tckn(), model.tckn());
    }
    @Test
    void testGetCustomerCarPoliciesToCarPolicyModel_withNullRequest() {
        CarPolicyModel model = mapper.getCustomerCarPoliciesToCarPolicyModel(null);
        assertNull(model);
    }
    @Test
    void testGetCustomerCarPoliciesBetweenDateToCarPolicyModel_withNullRequest() {
        CarPolicyModel model = mapper.getCustomerCarPoliciesBetweenDateToCarPolicyModel(null);
        assertNull(model);
    }
    @Test
    void testGetCarPoliciesToCarPolicyModel() {
        GetCarPolicyListRequest request = new GetCarPolicyListRequest(
                10,
                1,
                1L,
                LocalDate.now().plusDays(1),
                "Policy Description",
                2,
                PolicyState.ACTIVE,
                10000.0,
                1L,
                "AB12345",
                "12345678901",
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(3)
        );

        CarPolicyModel model = mapper.getCarPoliciesToCarPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
        assertEquals(request.policyOfferDate(), model.policyOfferDate());
        assertEquals(request.policyDescription(), model.policyDescription());
        assertEquals(request.coverageCode(), model.coverageCode());
        assertEquals(request.state(), model.state());
        assertEquals(request.policyAmount(), model.policyAmount());
        assertEquals(request.customerId(), model.customerId());
        assertEquals(request.licensePlateNumber(), model.licensePlateNumber());
        assertEquals(request.tckn(), model.tckn());
        assertEquals(request.policyStartDate(), model.policyStartDate());
        assertEquals(request.policyEndDate(), model.policyEndDate());
    }
    @Test
    void testGetCarPoliciesToCarPolicyModel_withNullRequest() {
        CarPolicyModel model = mapper.getCarPoliciesToCarPolicyModel(null);
        assertNull(model);
    }
    @Test
    void testCustomerModelToGetCarPoliciesByCustomer() {
        List<CarPolicyModel> modelList = new ArrayList<>();
        modelList.add(new CarPolicyModel(
                1L,
                LocalDate.now().minusDays(1),
                "Full Coverage",
                2,
                null,
                50000.0,
                1L,
                "AB12345",
                "12345678901",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                PolicyState.ACTIVE,
                10,
                1,
                null,
                null,
                null
        ));

        List<GetCustomerCarPoliciesResponse> responses = mapper.customerModelToGetCarPoliciesByCustomer(modelList);

        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals("Full Coverage", responses.get(0).policyDescription());
        assertEquals(50000.0, responses.get(0).policyAmount());
    }
    @Test
    void testCustomerModelToGetCarPoliciesByCustomer_withEmptyList() {
        List<GetCustomerCarPoliciesResponse> responses = mapper.customerModelToGetCarPoliciesByCustomer(new ArrayList<>());
        assertNotNull(responses);
        assertTrue(responses.isEmpty());
    }
    @Test
    void testCustomerModelToGetCarPoliciesByCustomer_withNullList() {
        List<GetCustomerCarPoliciesResponse> responses = mapper.customerModelToGetCarPoliciesByCustomer(null);
        assertNull(responses);
    }
    @Test
    void testCarPolicyModelToCreateCarPolicyResponse() {
        CarPolicyModel model = new CarPolicyModel(
                1L,
                LocalDate.now().minusDays(1),
                "Full Coverage",
                2,
                null,
                50000.0,
                1L,
                "AB12345",
                "12345678901",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                PolicyState.ACTIVE,
                10,
                1,
                null,
                null,
                null
        );

        CreateCarPolicyResponse response = mapper.carPolicyModelToCreateCarPolicyResponse(model);

        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    void testCarPolicyModelToUpdateCarPolicyResponse() {
        CarPolicyModel model = new CarPolicyModel(
                1L,
                LocalDate.now().minusDays(1),
                "Full Coverage",
                2,
                null,
                50000.0,
                1L,
                "AB12345",
                "12345678901",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                PolicyState.ACTIVE,
                10,
                1,
                null,
                null,
                null
        );

        UpdateCarPolicyResponse response = mapper.cartPolicyModelToUpdateCarPolicyResponse(model);

        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    void testCarPolicyModelToUpdateCarPolicyResponse_withNullModel() {
        UpdateCarPolicyResponse response = mapper.cartPolicyModelToUpdateCarPolicyResponse(null);
        assertNull(response);
    }
    @Test
    void testCarPolicyModelToCreateCarPolicyResponse_withNullModel() {
        CreateCarPolicyResponse response = mapper.carPolicyModelToCreateCarPolicyResponse(null);
        assertNull(response);
    }
    @Test
    void testCartPolicyModelToSetStateCarPolicyResponse() {
        CarPolicyModel model = new CarPolicyModel(
                1L,
                LocalDate.now().minusDays(1),
                "Full Coverage",
                2,
                null,
                50000.0,
                1L,
                "AB12345",
                "12345678901",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                PolicyState.ACTIVE,
                10,
                1,
                null,
                null,
                null
        );

        SetCarPolicyStatusResponse response = mapper.cartPolicyModelToSetStateCarPolicyResponse(model);

        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    void testCartPolicyModelToSetStateCarPolicyResponse_withNullModel() {
        SetCarPolicyStatusResponse response = mapper.cartPolicyModelToSetStateCarPolicyResponse(null);
        assertNull(response);
    }
    @Test
    void testCartPolicyModelToGetCarPolicyResponse() {
        CarPolicyModel model = new CarPolicyModel(
                1L,
                LocalDate.now().minusDays(1),
                "Full Coverage",
                2,
                null,
                50000.0,
                1L,
                "AB12345",
                "12345678901",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                PolicyState.ACTIVE,
                10,
                1,
                null,
                null,
                null
        );

        GetCarPolicyResponse response = mapper.cartPolicyModelToGetCarPolicyResponse(model);

        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
        assertEquals(model.policyOfferDate(), response.policyOfferDate());
        assertEquals(model.state(), response.state());
        assertEquals(model.policyDescription(), response.policyDescription());
        assertEquals(model.coverage(), response.coverage());
        assertEquals(model.policyAmount(), response.policyAmount());
        assertEquals(model.customerId(), response.customerId());
        assertEquals(model.policyStartDate(), response.policyStartDate());
        assertEquals(model.policyEndDate(), response.policyEndDate());
        assertEquals(model.licensePlateNumber(), response.licensePlateNumber());
        assertEquals(model.tckn(), response.tckn());
    }
    @Test
    void testCartPolicyModelToGetCarPolicyResponse_withNullModel() {
        GetCarPolicyResponse response = mapper.cartPolicyModelToGetCarPolicyResponse(null);
        assertNull(response);
    }
    @Test
    void testCartPolicyModelToDeleteCarPolicyResponse() {
        CarPolicyModel model = new CarPolicyModel(
                1L,
                LocalDate.now().minusDays(1),
                "Full Coverage",
                2,
                null,
                50000.0,
                1L,
                "AB12345",
                "12345678901",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                PolicyState.ACTIVE,
                10,
                1,
                null,
                null,
                null
        );

        DeleteCarPolicyResponse response = mapper.cartPolicyModelToDeleteCarPolicyResponse(model);

        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    void testCartPolicyModelToDeleteCarPolicyResponse_withNullModel() {
        DeleteCarPolicyResponse response = mapper.cartPolicyModelToDeleteCarPolicyResponse(null);
        assertNull(response);
    }
}



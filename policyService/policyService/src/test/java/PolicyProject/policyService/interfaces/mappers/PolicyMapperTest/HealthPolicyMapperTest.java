package PolicyProject.policyService.interfaces.mappers.PolicyMapperTest;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.request.HealthPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.*;
import PolicyProject.policyService.domain.model.PolicyModel.HealthPolicyModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.HealthPolicyMapper;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HealthPolicyMapperTest {

    private final HealthPolicyMapper mapper = Mappers.getMapper(HealthPolicyMapper.class);

    @Test
    void testHealthPolicyModelToGetHealthPolicyResponse() {
        HealthPolicyModel model = new HealthPolicyModel(
                1L,
                2L,
                3L,
                "12345678901",
                170.0,
                70.0,
                100,
                new Coverage(),
                24.22,
                BloodType.A_NEGATIVE,
                true,
                false,
                false,
                false,
                false,
                LocalDate.of(2023, 1, 1),
                "Test Policy",
                50000.0,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                PolicyState.ACTIVE,
                0,
                10
        );

        GetHealthPolicyResponse response = mapper.healthPolicyModelToGetHealthPolicyResponse(model);

        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    void testHealthPolicyModelToGetHealthPolicyResponseWithNullValues() {
        HealthPolicyModel model = new HealthPolicyModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                LocalDate.of(2023, 1, 1),
                null,
                null,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                null,
                0,
                10
        );

        GetHealthPolicyResponse response = mapper.healthPolicyModelToGetHealthPolicyResponse(model);

        assertNotNull(response);
        assertNull(response.policyId());
    }
    @Test
    void testHealthPolicyModelToSetStateHealthPolicyResponse() {
        HealthPolicyModel model = new HealthPolicyModel(
                1L,
                2L,
                3L,
                "12345678901",
                170.0,
                70.0,
                100,
                new Coverage(),
                24.22,
                BloodType.A_NEGATIVE,
                true,
                false,
                false,
                false,
                false,
                LocalDate.of(2023, 1, 1),
                "Test Policy",
                50000.0,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                PolicyState.ACTIVE,
                0,
                10
        );

        SetStateHealthPolicyResponse response = mapper.healthPolicyModelToSetStateHealthPolicyResponse(model);

        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    void testHealthPolicyModelToSetStateHealthPolicyResponseWithNullValues() {
        HealthPolicyModel model = new HealthPolicyModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                LocalDate.of(2023, 1, 1),
                null,
                null,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                null,
                0,
                10
        );

        SetStateHealthPolicyResponse response = mapper.healthPolicyModelToSetStateHealthPolicyResponse(model);

        assertNotNull(response);
        assertNull(response.policyId());
    }
    @Test
    void testHealthPolicyModelToSetStateHealthPolicyResponseWithCompleteModel() {
        HealthPolicyModel model = new HealthPolicyModel(
                1L,
                2L,
                3L,
                "12345678901",
                170.0,
                70.0,
                100,
                new Coverage(),
                24.22,
                BloodType.A_NEGATIVE,
                true,
                false,
                false,
                false,
                false,
                LocalDate.of(2023, 1, 1),
                "Complete Policy",
                50000.0,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                PolicyState.ACTIVE,
                0,
                10
        );

        SetStateHealthPolicyResponse response = mapper.healthPolicyModelToSetStateHealthPolicyResponse(model);

        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    void testCreateHealthPolicyRequestToHealthPolicyModel() {
        CreateHealthPolicyRequest request = new CreateHealthPolicyRequest(
                LocalDate.of(2023, 1, 1),
                1L,
                100,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                "12345678901",
                50000L
        );

        HealthPolicyModel model = mapper.createHealthPolicyRequestToHealthPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyOfferDate(), model.policyOfferDate());
        assertEquals(request.personalHealthId(), model.personalHealthId());
        assertEquals(request.coverageCode(), model.coverageCode());
        assertEquals(request.policyStartDate(), model.policyStartDate());
        assertEquals(request.policyEndDate(), model.policyEndDate());
        assertEquals(request.tckn(), model.tckn());
        assertEquals(request.policyAmount().doubleValue(), model.policyAmount().doubleValue());
    }
    @Test
    void testCreateHealthPolicyRequestToHealthPolicyModelWithNullValues() {
        CreateHealthPolicyRequest request = new CreateHealthPolicyRequest(
                LocalDate.of(2023, 1, 1),
                1L,
                null,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                null,
                null
        );

        HealthPolicyModel model = mapper.createHealthPolicyRequestToHealthPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyOfferDate(), model.policyOfferDate());
        assertEquals(request.personalHealthId(), model.personalHealthId());
        assertNull(model.coverageCode());
        assertEquals(request.policyStartDate(), model.policyStartDate());
        assertEquals(request.policyEndDate(), model.policyEndDate());
        assertNull(model.tckn());
        assertNull(model.policyAmount());
    }
    @Test
    void testCreateHealthPolicyRequestToHealthPolicyModelWithCoverageConversion() {
        CreateHealthPolicyRequest request = new CreateHealthPolicyRequest(
                LocalDate.of(2023, 1, 1),
                1L,
                100,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                "12345678901",
                50000L
        );

        HealthPolicyModel model = mapper.createHealthPolicyRequestToHealthPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.coverageCode(), model.coverageCode());

        Coverage coverage = mapper.mapIntegerToCoverage(model.coverageCode());

        assertNotNull(coverage);
        assertEquals(100L, coverage.getId());
    }
    @Test
    void testHealthPolicyModelToCreateHealthPolicyResponse() {
        HealthPolicyModel model = new HealthPolicyModel(
                1L,
                2L,
                3L,
                "12345678901",
                170.0,
                70.0,
                100,
                new Coverage(),
                24.22,
                BloodType.A_NEGATIVE,
                true,
                false,
                false,
                false,
                false,
                LocalDate.of(2023, 1, 1),
                "Test Policy",
                50000.0,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                PolicyState.ACTIVE,
                0,
                10
        );

        CreateHealthPolicyResponse response = mapper.healthPolicyModelToCreateHealthPolicyResponse(model);

        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    void testHealthPolicyModelToCreateHealthPolicyResponseWithNullValues() {
        HealthPolicyModel model = new HealthPolicyModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                LocalDate.of(2023, 1, 1),
                null,
                null,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                null,
                0,
                10
        );

        CreateHealthPolicyResponse response = mapper.healthPolicyModelToCreateHealthPolicyResponse(model);

        assertNotNull(response);
        assertNull(response.policyId());
    }
    @Test
    void testUpdateHealthPolicyRequestToHealthPolicyModel() {
        UpdateHealthPolicyRequest request = new UpdateHealthPolicyRequest(
                1L,
                LocalDate.of(2023, 1, 1),
                "Test Policy",
                PolicyState.ACTIVE,
                100,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                2000.0
        );

        HealthPolicyModel model = mapper.updateHealthPolicyRequestToHealthPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
        assertEquals(request.policyOfferDate(), model.policyOfferDate());
        assertEquals(request.policyDescription(), model.policyDescription());
        assertEquals(request.state(), model.state());
        assertEquals(request.coverageCode(), model.coverageCode());
        assertEquals(request.policyStartDate(), model.policyStartDate());
        assertEquals(request.policyEndDate(), model.policyEndDate());
        assertEquals(request.policyAmount(), model.policyAmount());
    }
    @Test
    void testUpdateHealthPolicyRequestToHealthPolicyModelWithNullValues() {
        UpdateHealthPolicyRequest request = new UpdateHealthPolicyRequest(
                null,
                LocalDate.of(2023, 1, 1),
                "Test Policy",
                PolicyState.ACTIVE,
                null,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                null
        );

        HealthPolicyModel model = mapper.updateHealthPolicyRequestToHealthPolicyModel(request);

        assertNotNull(model);
        assertNull(model.policyId());
        assertEquals(request.policyOfferDate(), model.policyOfferDate());
        assertEquals(request.policyDescription(), model.policyDescription());
        assertEquals(request.state(), model.state());
        assertNull(model.coverageCode());
        assertEquals(request.policyStartDate(), model.policyStartDate());
        assertEquals(request.policyEndDate(), model.policyEndDate());
        assertNull(model.policyAmount());
    }
    @Test
    void testSetStateHealthPolicyRequestToHealthPolicyModel() {
        SetStateHealthPolicyRequest request = new SetStateHealthPolicyRequest(1L);

        HealthPolicyModel model = mapper.setStateHealthPolicyRequestToHealthPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
    }
    @Test
    void testSetStateHealthPolicyRequestToHealthPolicyModelWithNullValues() {
        SetStateHealthPolicyRequest request = new SetStateHealthPolicyRequest(null);

        HealthPolicyModel model = mapper.setStateHealthPolicyRequestToHealthPolicyModel(request);

        assertNotNull(model);
        assertNull(model.policyId());
    }
    @Test
    void testGetHealthPolicyRequestToHealthPolicyModel() {
        GetHealthPolicyRequest request = new GetHealthPolicyRequest(1L);

        HealthPolicyModel model = mapper.getHealthPolicyRequestToHealthPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
    }
    @Test
    void testGetHealthPolicyRequestToHealthPolicyModelWithNullValues() {
        GetHealthPolicyRequest request = new GetHealthPolicyRequest(null);

        HealthPolicyModel model = mapper.getHealthPolicyRequestToHealthPolicyModel(request);

        assertNotNull(model);
        assertNull(model.policyId());
    }
    @Test
    void testDeleteHealthPolicyRequestToHealthPolicyModel() {
        DeleteHealthPolicyRequest request = new DeleteHealthPolicyRequest(
                1L
        );

        HealthPolicyModel model = mapper.deleteHealthPolicyRequestToHealthPolicyModel(request);

        assertNotNull(model);
        assertEquals(request.policyId(), model.policyId());
    }
    @Test
    void testDeleteHealthPolicyRequestToHealthPolicyModelWithNullValues() {
        DeleteHealthPolicyRequest request = new DeleteHealthPolicyRequest(
                null
        );

        HealthPolicyModel model = mapper.deleteHealthPolicyRequestToHealthPolicyModel(request);

        assertNotNull(model);
        assertNull(model.policyId());
    }
    @Test
    void testHealthPolicyModelToUpdateHealthPolicyResponse() {
        HealthPolicyModel model = new HealthPolicyModel(
                1L,
                2L,
                3L,
                "12345678901",
                170.0,
                70.0,
                100,
                new Coverage(),
                24.22,
                BloodType.A_NEGATIVE,
                true,
                false,
                false,
                false,
                false,
                LocalDate.of(2023, 1, 1),
                "Test Policy",
                50000.0,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                PolicyState.ACTIVE,
                0,
                10
        );

        UpdateHealthPolicyResponse response = mapper.healthPolicyModelToUpdateHealthPolicyResponse(model);

        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    void testHealthPolicyModelToUpdateHealthPolicyResponseWithNullValues() {
        HealthPolicyModel model = new HealthPolicyModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                LocalDate.of(2023, 1, 1),
                null,
                null,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                null,
                0,
                10
        );

        UpdateHealthPolicyResponse response = mapper.healthPolicyModelToUpdateHealthPolicyResponse(model);

        assertNotNull(response);
        assertNull(response.policyId());
    }
    @Test
    void testHealthPolicyModelListToGetHealthPolicyResponseList() {
        HealthPolicyModel model1 = new HealthPolicyModel(
                1L,
                2L,
                3L,
                "12345678901",
                170.0,
                70.0,
                100,
                new Coverage(),
                24.22,
                BloodType.A_NEGATIVE,
                true,
                false,
                false,
                false,
                false,
                LocalDate.of(2023, 1, 1),
                "Policy 1",
                50000.0,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                PolicyState.ACTIVE,
                0,
                10
        );

        HealthPolicyModel model2 = new HealthPolicyModel(
                4L,
                5L,
                6L,
                "12345678902",
                175.0,
                75.0,
                200,
                new Coverage(),
                25.22,
                BloodType.B_POSITIVE,
                false,
                true,
                true,
                true,
                true,
                LocalDate.of(2023, 3, 1),
                "Policy 2",
                100000.0,
                LocalDate.of(2023, 4, 1),
                LocalDate.of(2024, 4, 1),
                PolicyState.CANCELLED,
                1,
                10
        );

        List<HealthPolicyModel> modelList = List.of(model1, model2);

        List<GetHealthPolicyResponse> responseList = mapper.healthPolicyModelListToGetHealthPolicyResponseList(modelList);

        assertNotNull(responseList);
        assertEquals(modelList.size(), responseList.size());

        GetHealthPolicyResponse response1 = responseList.get(0);
        GetHealthPolicyResponse response2 = responseList.get(1);

        assertEquals(model1.policyId(), response1.policyId());
        assertEquals(model2.policyId(), response2.policyId());
    }
    @Test
    void testHealthPolicyModelToDeleteHealthPolicyResponse() {
        HealthPolicyModel model = new HealthPolicyModel(
                1L,
                2L,
                3L,
                "12345678901",
                170.0,
                70.0,
                100,
                new Coverage(),
                24.22,
                BloodType.A_NEGATIVE,
                true,
                false,
                false,
                false,
                false,
                LocalDate.of(2023, 1, 1),
                "Test Policy",
                50000.0,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                PolicyState.ACTIVE,
                0,
                10
        );

        DeleteHealthPolicyResponse response = mapper.healthPolicyModelToDeleteHealthPolicyResponse(model);

        assertNotNull(response);
        assertEquals(model.policyId(), response.policyId());
    }
    @Test
    void testHealthPolicyModelToDeleteHealthPolicyResponseWithNullValues() {
        HealthPolicyModel model = new HealthPolicyModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                LocalDate.of(2023, 1, 1),
                null,
                null,
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2024, 2, 1),
                null,
                0,
                10
        );

        DeleteHealthPolicyResponse response = mapper.healthPolicyModelToDeleteHealthPolicyResponse(model);

        assertNotNull(response);
        assertNull(response.policyId());
    }
    @Test
    void testHealthPolicyModelListToGetHealthPolicyResponseListWithEmptyList() {
        List<HealthPolicyModel> modelList = Collections.emptyList();

        List<GetHealthPolicyResponse> responseList = mapper.healthPolicyModelListToGetHealthPolicyResponseList(modelList);

        assertNotNull(responseList);
        assertTrue(responseList.isEmpty());
    }
    @Test
    void testHealthPolicyModelListToGetHealthPolicyResponseListWithNullValues() {
        HealthPolicyModel model = new HealthPolicyModel(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                LocalDate.of(2023, 1, 1),
                null,
                0,
                0
        );

        List<HealthPolicyModel> modelList = Collections.singletonList(model);

        List<GetHealthPolicyResponse> responseList = mapper.healthPolicyModelListToGetHealthPolicyResponseList(modelList);

        assertNotNull(responseList);
        assertEquals(1, responseList.size());

        GetHealthPolicyResponse response = responseList.get(0);

        assertNull(response.policyId());
    }
}



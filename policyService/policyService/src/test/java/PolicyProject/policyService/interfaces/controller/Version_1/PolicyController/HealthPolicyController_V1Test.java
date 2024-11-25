package PolicyProject.policyService.interfaces.controller.Version_1.PolicyController;

import PolicyProject.policyService.application.service.Service.PolicyService.HealthPolicyService;
import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.request.HealthPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.*;
import PolicyProject.policyService.domain.dto.request.HealthPolicyRequest.GetListHealthPolicyRequest;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.GetHealthPolicyResponse;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.interfaces.controller.Version_1.PolicyController.HealthPolicyController_V1;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.HealthPolicyMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import PolicyProject.policyService.domain.dto.request.HealthPolicyRequest.CreateHealthPolicyRequest;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.CreateHealthPolicyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@WebMvcTest(HealthPolicyController_V1.class)
public class HealthPolicyController_V1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HealthPolicyService healthPolicyService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getTotalRecord_success() throws Exception {
        when(healthPolicyService.getTotalRecord()).thenReturn(10);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/healthPolicy/totalRecord")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(10));
    }

    @Test
    void getTotalRecord_empty() throws Exception {
        when(healthPolicyService.getTotalRecord()).thenReturn(0);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/healthPolicy/totalRecord")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    void getTotalRecord_serverError() throws Exception {
        when(healthPolicyService.getTotalRecord()).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/healthPolicy/totalRecord")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }


    @Test
    void deletePolicy_success() throws Exception {
        DeleteHealthPolicyRequest request = new DeleteHealthPolicyRequest(1L);
        DeleteHealthPolicyResponse response = new DeleteHealthPolicyResponse(1L);

        when(healthPolicyService.delete(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("policyId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }

    @Test
    void deletePolicy_notFoundError() throws Exception {
        DeleteHealthPolicyRequest request = new DeleteHealthPolicyRequest(999L);

        when(healthPolicyService.delete(ArgumentMatchers.any())).thenThrow(new EntityNotFoundException(999L, "Entity Not Found"));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("policyId", "999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletePolicy_validationError() throws Exception {
        DeleteHealthPolicyRequest request = new DeleteHealthPolicyRequest(null);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("policyId", (String) null))
                .andExpect(status().isBadRequest());
    }



    @Test
    void createPolicy_success() throws Exception {
        CreateHealthPolicyRequest request = new CreateHealthPolicyRequest(
                LocalDate.now(), 1L, 1, LocalDate.now(), LocalDate.now().plusDays(10),
                "12345678901", 1000L
        );

        CreateHealthPolicyResponse response = new CreateHealthPolicyResponse(1L);

        when(healthPolicyService.create(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.policyId").value(1L));
    }

    @Test
    void rejectPolicy_success() throws Exception {
        SetStateHealthPolicyRequest request = new SetStateHealthPolicyRequest(1L);
        SetStateHealthPolicyResponse response = new SetStateHealthPolicyResponse(1L);

        when(healthPolicyService.reject(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(put("/api/v1/healthPolicy/rejected")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }

    @Test
    void rejectPolicy_notFoundError() throws Exception {
        SetStateHealthPolicyRequest request = new SetStateHealthPolicyRequest(999L);

        when(healthPolicyService.reject(ArgumentMatchers.any())).thenThrow(new EntityNotFoundException(999L, "Entity Not Found"));

        mockMvc.perform(put("/api/v1/healthPolicy/rejected")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void rejectPolicy_validationError() throws Exception {
        SetStateHealthPolicyRequest request = new SetStateHealthPolicyRequest(null);

        mockMvc.perform(put("/api/v1/healthPolicy/rejected")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createPolicy_validationError() throws Exception {
        CreateHealthPolicyRequest request = new CreateHealthPolicyRequest(
                null, null, null, null, null, null, null
        );

        mockMvc.perform(post("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getPolicy_success() throws Exception {
        GetHealthPolicyRequest request = new GetHealthPolicyRequest(1L);
        GetHealthPolicyResponse response = new GetHealthPolicyResponse(
                1L, LocalDate.now(), PolicyState.ACTIVE, "Policy Description",
                new Coverage(), 5000.0, 1L, LocalDate.now(), LocalDate.now().plusDays(10),
                "12345678901", BloodType.O_POSITIVE
        );

        when(healthPolicyService.get(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("policyId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }

    @Test
    void getPolicy_notFoundError() throws Exception {
        GetHealthPolicyRequest request = new GetHealthPolicyRequest(999L);

        when(healthPolicyService.get(ArgumentMatchers.any())).thenThrow(new EntityNotFoundException(999L, "Entity Not Found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("policyId", "999"))
                .andExpect(status().isNotFound());
    }


    @Test
    void updatePolicy_success() throws Exception {
        UpdateHealthPolicyRequest request = new UpdateHealthPolicyRequest(
                1L, LocalDate.now(), "Policy Description", PolicyState.ACTIVE,
                1, LocalDate.now(), LocalDate.now().plusDays(10), 5000.0
        );

        UpdateHealthPolicyResponse response = new UpdateHealthPolicyResponse(1L);

        when(healthPolicyService.update(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(put("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }

    @Test
    void updatePolicy_validationError() throws Exception {
        UpdateHealthPolicyRequest request = new UpdateHealthPolicyRequest(
                null, null, null, null, null, null, null, null
        );

        mockMvc.perform(put("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatePolicy_notFoundError() throws Exception {
        UpdateHealthPolicyRequest request = new UpdateHealthPolicyRequest(
                999L, LocalDate.now(), "Policy Description", null,
                1, LocalDate.now(), LocalDate.now().plusDays(10), 5000.0
        );
        when(healthPolicyService.update(ArgumentMatchers.any())).thenThrow(new EntityNotFoundException(999L, "Entity Not Found"));

        mockMvc.perform(put("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void acceptPolicy_success() throws Exception {
        SetStateHealthPolicyRequest request = new SetStateHealthPolicyRequest(1L);
        SetStateHealthPolicyResponse response = new SetStateHealthPolicyResponse(1L);

        when(healthPolicyService.accept(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(put("/api/v1/healthPolicy/accepted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }

    @Test
    void acceptPolicy_notFoundError() throws Exception {
        SetStateHealthPolicyRequest request = new SetStateHealthPolicyRequest(999L);

        when(healthPolicyService.accept(ArgumentMatchers.any())).thenThrow(new EntityNotFoundException(999L, "Entity Not Found"));

        mockMvc.perform(put("/api/v1/healthPolicy/accepted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void acceptPolicy_validationError() throws Exception {
        SetStateHealthPolicyRequest request = new SetStateHealthPolicyRequest(null);

        mockMvc.perform(put("/api/v1/healthPolicy/accepted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void getPolicies_success() throws Exception {
        GetListHealthPolicyRequest request = new GetListHealthPolicyRequest(
                1L, 1L, "12345678901", 180.0, 75.0, 100, 23.1, BloodType.O_NEGATIVE,
                true, false, false, false, false, LocalDate.now(),
                "Policy1", 10000.0, LocalDate.now(), LocalDate.now().plusDays(30),
                PolicyState.ACTIVE, 0, 10
        );

        List<GetHealthPolicyResponse> response = List.of(
                new GetHealthPolicyResponse(
                        1L, LocalDate.now(), PolicyState.ACTIVE, "Policy1",
                        new Coverage(), 10000.0, 1L, LocalDate.now(), LocalDate.now().plusDays(30),
                        "12345678901", BloodType.O_NEGATIVE
                )
        );

        when(healthPolicyService.getList(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/healthPolicy/list")
                        .param("policyId", "1")
                        .param("personalHealthId", "1")
                        .param("tckn", "12345678901")
                        .param("height", "180.0")
                        .param("weight", "75.0")
                        .param("coverageCode", "100")
                        .param("bmi", "23.1")
                        .param("bloodType", "O_NEGATIVE")
                        .param("alcoholConsumption", "true")
                        .param("smokeConsumption", "false")
                        .param("isPregnant", "false")
                        .param("hasDisability", "false")
                        .param("hasPreviousSurgeries", "false")
                        .param("policyOfferDate", LocalDate.now().toString())
                        .param("policyDescription", "Policy1")
                        .param("policyAmount", "10000.0")
                        .param("policyStartDate", LocalDate.now().toString())
                        .param("policyEndDate", LocalDate.now().plusDays(30).toString())
                        .param("state", PolicyState.ACTIVE.toString())
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].policyId").value(1L));
    }

    @Test
    void getPolicies_validationError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/healthPolicy/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getPolicies_notFoundError() throws Exception {
        GetListHealthPolicyRequest request = new GetListHealthPolicyRequest(
                999L, null, null, null, null, null, null, null,
                null, null, null, null, null, null,
                null, null, null, null, null, 0, 10
        );

        when(healthPolicyService.getList(ArgumentMatchers.any())).thenThrow(new EntityNotFoundException(999L, "Entity Not Found"));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/healthPolicy/list")
                        .param("policyId", "999")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
















}




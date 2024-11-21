package PolicyProject.policyService.interfaces.controller.Version_1.PolicyController;

import PolicyProject.policyService.application.service.Service.PolicyService.HealthPolicyService;
import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.request.HealthPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.*;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.interfaces.controller.Version_1.PolicyController.HealthPolicyController_V1;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.HealthPolicyMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import PolicyProject.policyService.domain.dto.request.HealthPolicyRequest.CreateHealthPolicyRequest;
import PolicyProject.policyService.domain.dto.response.HealthPolicyResponse.CreateHealthPolicyResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(HealthPolicyController_V1.class)
public class HealthPolicyController_V1Test {

    @Test
    void testGetPolicySuccess() throws Exception {
        GetHealthPolicyRequest request = new GetHealthPolicyRequest(1L);
        GetHealthPolicyResponse response = new GetHealthPolicyResponse(
                1L,
                LocalDate.now(),
                PolicyState.ACTIVE,
                "Description",
                new Coverage(),
                100.0,
                1L,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                "12345678901",
                BloodType.A_POSITIVE
        );

        when(healthPolicyService.get(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(get("/api/v1/healthPolicy")
                        .param("policyId", String.valueOf(request.policyId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }

    @Test
    void testGetPolicyValidationError() throws Exception {
        GetHealthPolicyRequest invalidRequest = new GetHealthPolicyRequest(null);

        mockMvc.perform(get("/api/v1/healthPolicy")
                        .param("policyId", String.valueOf(invalidRequest.policyId()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCreatePolicySuccess() throws Exception {
        CreateHealthPolicyRequest request = new CreateHealthPolicyRequest(
                LocalDate.now(),
                1L,
                100,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                "12345678901",
                50000L
        );
        CreateHealthPolicyResponse response = new CreateHealthPolicyResponse(1L);

        when(healthPolicyService.create(ArgumentMatchers.any())).thenReturn(response);
        when(healthPolicyMapper.createHealthPolicyRequestToHealthPolicyModel(ArgumentMatchers.any())).thenReturn(null); // mock appropriately

        mockMvc.perform(post("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.policyId").value(1L));
    }

    @Test
    void testCreatePolicyValidationError() throws Exception {
        CreateHealthPolicyRequest invalidRequest = new CreateHealthPolicyRequest(
                null,
                1L,
                100,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(2),
                "12345678901",
                50000L
        );

        mockMvc.perform(post("/api/v1/healthPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HealthPolicyService healthPolicyService;

    @Autowired
    private HealthPolicyMapper healthPolicyMapper;







}


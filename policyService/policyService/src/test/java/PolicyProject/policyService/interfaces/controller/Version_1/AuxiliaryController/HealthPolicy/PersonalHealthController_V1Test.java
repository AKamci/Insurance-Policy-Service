package PolicyProject.policyService.interfaces.controller.Version_1.AuxiliaryController.HealthPolicy;

import PolicyProject.policyService.application.service.Service.AuxiliaryService.HealthPolicy.PersonalHealthService;
import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.BloodType;
import PolicyProject.policyService.domain.dto.request.PersonalHealthRequest.CreatePersonalHealthRequest;
import PolicyProject.policyService.domain.dto.request.PersonalHealthRequest.GetPersonalHealthWithCustomerRequest;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.CreatePersonalHealthResponse;
import PolicyProject.policyService.domain.dto.response.PersonalHealthResponse.GetPersonalHealthWithCustomerResponse;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.HealthPolicy.PersonalHealthMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(PersonalHealthController_V1.class)
public class PersonalHealthController_V1Test {

    @Test
    void testCreatePersonalHealth_Success_WithBloodTypeANegative() throws Exception {
        // Mock request
        CreatePersonalHealthRequest mockRequest = new CreatePersonalHealthRequest(
                "12345678901", 200, 180, 75.0, 23.1, BloodType.A_NEGATIVE, false, false, false, false, false
        );

        // Mock response
        CreatePersonalHealthResponse mockResponse = new CreatePersonalHealthResponse(
                mockRequest.tckn(), 1L
        );

        Mockito.when(personalHealthService.create(any())).thenReturn(mockResponse);

        // Perform request
        MvcResult result = mockMvc.perform(post("/api/v1/personalHealth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.tckn", is("12345678901")))
                .andReturn();

        // Verify
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }



    @Test
    void testCreatePersonalHealth_Success() throws Exception {
        // Mock request
        CreatePersonalHealthRequest mockRequest = new CreatePersonalHealthRequest(
                "12345678901", 200, 180, 75.0, 23.1, BloodType.A_NEGATIVE, false, false, false, false, false
        );

        // Mock response
        CreatePersonalHealthResponse mockResponse = new CreatePersonalHealthResponse(
                mockRequest.tckn(), 1L
        );

        Mockito.when(personalHealthService.create(any())).thenReturn(mockResponse);

        // Perform request
        MvcResult result = mockMvc.perform(post("/api/v1/personalHealth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.tckn", is("12345678901")))
                .andReturn();

        // Verify
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testCreatePersonalHealth_BadRequest() throws Exception {
        // Mock request with invalid data
        CreatePersonalHealthRequest mockRequest = new CreatePersonalHealthRequest(
                "12345", 50, 180, 75.0, 23.1, BloodType.A_NEGATIVE, false, false, false, false, false
        );

        // Perform request
        mockMvc.perform(post("/api/v1/personalHealth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockRequest)))
                .andExpect(status().isBadRequest());
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonalHealthService personalHealthService;

    @Test
    void testGetWCustomer_Success() throws Exception {
        // Mock response
        GetPersonalHealthWithCustomerResponse mockResponse = new GetPersonalHealthWithCustomerResponse(
                1L, 180, 75.0, 23.1, "O+", false, false, false, false, false, LocalDateTime.now(), null, 10000L
        );

        Mockito.when(personalHealthService.getWCustomer(any())).thenReturn(mockResponse);

        // Perform request
        MvcResult result = mockMvc.perform(get("/api/v1/personalHealth/WCustomer")
                        .param("tckn", "12345678901")
                        .param("coverageCode", "200")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.height", is(180)))
                .andExpect(jsonPath("$.weight", is(75.0)))
                .andExpect(jsonPath("$.bmi", is(23.1)))
                .andExpect(jsonPath("$.bloodType", is("O+")))
                .andReturn();

        // Verify
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void testGetWCustomer_BadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/personalHealth/WCustomer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}

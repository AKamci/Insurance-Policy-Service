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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PersonalHealthController_V1.class)
public class PersonalHealthController_V1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonalHealthService personalHealthService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetWCustomer_ReturnsPersonalHealthWithCustomerResponse() throws Exception {
        // Arrange
        GetPersonalHealthWithCustomerResponse mockResponse = new GetPersonalHealthWithCustomerResponse(
                1L, 175, 70.0, 22.9, "O+", true, false, false, false, false,
                null, null, 1000L);
        Mockito.when(personalHealthService.getWCustomer(any())).thenReturn(mockResponse);

        // Act
        MvcResult result = mockMvc.perform(get("/api/v1/personalHealth/WCustomer")
                        .param("tckn", "12345678901")
                        .param("coverageCode", "123")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String jsonResponse = result.getResponse().getContentAsString();
        GetPersonalHealthWithCustomerResponse actualResponse = objectMapper.readValue(jsonResponse, GetPersonalHealthWithCustomerResponse.class);
        assertThat(actualResponse).isEqualTo(mockResponse);
    }

    @Test
    public void testGetWCustomer_InvalidInput_ReturnsBadRequest() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/v1/personalHealth/WCustomer")
                        .param("tckn", "")
                        .param("coverageCode", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreatePersonalHealth_ReturnsCreatedResponse() throws Exception {
        // Arrange
        CreatePersonalHealthRequest mockRequest = new CreatePersonalHealthRequest(
                "12345678901", 123, 175, 70.0, 22.9, BloodType.A_NEGATIVE, true, false, false, false, false);
        CreatePersonalHealthResponse mockResponse = new CreatePersonalHealthResponse("12345678901", 1L);
        Mockito.when(personalHealthService.create(any())).thenReturn(mockResponse);

        // Act
        MvcResult result = mockMvc.perform(post("/api/v1/personalHealth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String jsonResponse = result.getResponse().getContentAsString();
        CreatePersonalHealthResponse actualResponse = objectMapper.readValue(jsonResponse, CreatePersonalHealthResponse.class);
        assertThat(actualResponse).isEqualTo(mockResponse);
    }

    @Test
    public void testCreatePersonalHealth_InvalidInput_ReturnsBadRequest() throws Exception {
        // Arrange
        CreatePersonalHealthRequest mockRequest = new CreatePersonalHealthRequest(
                "", 123, 175, 70.0, 22.9, BloodType.AB_NEGATIVE, true, false, false, false, false);

        // Act & Assert
        mockMvc.perform(post("/api/v1/personalHealth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockRequest))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
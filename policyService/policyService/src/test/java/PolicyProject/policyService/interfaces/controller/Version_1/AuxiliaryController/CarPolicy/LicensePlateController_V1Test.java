package PolicyProject.policyService.interfaces.controller.Version_1.AuxiliaryController.CarPolicy;

import PolicyProject.policyService.application.service.Service.AuxiliaryService.CarPolicy.LicensePlateService;
import PolicyProject.policyService.domain.dto.request.LicensePlateRequest.GetPlateWithCustomerRequest;
import PolicyProject.policyService.domain.dto.response.LicensePlateResponse.GetPlateWithCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.Car;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy.LicensePlateMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(LicensePlateController_V1.class)
public class LicensePlateController_V1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LicensePlateService licensePlateService;

    @Mock
    private LicensePlateModel licensePlateModel;

    private GetPlateWithCustomerRequest validRequest;
    private GetPlateWithCustomerRequest invalidRequest;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validRequest = new GetPlateWithCustomerRequest("ABC123", 1);
        invalidRequest = new GetPlateWithCustomerRequest("", 1);
        
    }

    @Test
    @DisplayName("Test getWCustomer - Valid Request")
    public void testGetWCustomer_ValidRequest() throws Exception {
        GetPlateWithCustomerRequest request = validRequest;
        GetPlateWithCustomerResponse response = new GetPlateWithCustomerResponse(1L, 1, "ABC123", new Car(), new Customer(), 1000L);

        when(licensePlateService.getWCustomer
                (LicensePlateMapper.INSTANCE.getPlateWithCustomerRequestToLicensePlateModel(request)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/licensePlate/WCustomer")
                        .param("plate", "ABC123")
                        .param("coverageCode", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.coverageCode").value(1))
                .andExpect(jsonPath("$.plate").value("ABC123"))
                .andExpect(jsonPath("$.amount").value(1000L));
    }

    @Test
    @DisplayName("Test getWCustomer - Invalid Request")
    public void testGetWCustomer_InvalidRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/licensePlate/WCustomer")
                        .param("plate", "")
                        .param("coverageCode", "1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}
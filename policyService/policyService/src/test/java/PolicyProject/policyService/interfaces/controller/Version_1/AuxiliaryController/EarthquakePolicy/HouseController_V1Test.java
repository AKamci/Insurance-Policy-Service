package PolicyProject.policyService.interfaces.controller.Version_1.AuxiliaryController.EarthquakePolicy;

import PolicyProject.policyService.application.service.Service.AuxiliaryService.EarthquakePolicy.HouseService;
import PolicyProject.policyService.domain.dto.request.HouseRequest.GetHouseWCustomerRequest;
import PolicyProject.policyService.domain.dto.response.HouseResponse.GetHouseWCustomerResponse;
import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.interfaces.controller.Version_1.AuxiliaryController.CarPolicy.LicensePlateController_V1;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.CarPolicy.LicensePlateMapper;
import PolicyProject.policyService.interfaces.mappers.AuxiliaryMapper.EarthquakePolicy.HouseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.Valid;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HouseController_V1Test.class)
public class HouseController_V1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HouseService houseService;

    @Mock
    private HouseModel houseModel;

    @InjectMocks
    private HouseController_V1 houseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetWCustomerSuccess() throws Exception {
        GetHouseWCustomerRequest request = new GetHouseWCustomerRequest(12, 34, "City", "District", "Neighborhood", 100, "12345678901");
        GetHouseWCustomerResponse response = new GetHouseWCustomerResponse(1L, 12, 120, null, null, "12345678901", 10000L);

        when(houseService.getWCustomer(HouseMapper.INSTANCE.getHouseWithCustomerRequestToHouseModel(request)))
                .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/house/WCustomer")
                        .param("number", "12")
                        .param("apartmentNumber", "34")
                        .param("city", "City")
                        .param("district", "District")
                        .param("neighborhood", "Neighborhood")
                        .param("coverageCode", "100")
                        .param("tckn", "12345678901")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.number").value(12))
                .andExpect(jsonPath("$.squareMeters").value(120))
                .andExpect(jsonPath("$.coverageCode").value(100))
                .andExpect(jsonPath("$.tckn").value("12345678901"));
    }

    @Test
    void testGetWCustomerValidationError() throws Exception {
        GetHouseWCustomerRequest invalidRequest = new GetHouseWCustomerRequest(12,
                34, "", null, "Neighborhood", 100, "12345678901");

        when(houseService.getWCustomer(HouseMapper.INSTANCE.getHouseWithCustomerRequestToHouseModel(invalidRequest)))
                .thenThrow(new IllegalArgumentException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/house/WCustomer")
                        .param("number", "12")
                        .param("apartmentNumber", "34")
                        .param("city", "")
                        .param("district", "null")
                        .param("neighborhood", "Neighborhood")
                        .param("coverageCode", "100")
                        .param("tckn", "12345678901")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
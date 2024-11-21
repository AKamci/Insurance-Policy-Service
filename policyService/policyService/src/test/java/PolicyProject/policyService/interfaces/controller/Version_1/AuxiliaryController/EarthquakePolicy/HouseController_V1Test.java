package PolicyProject.policyService.interfaces.controller.Version_1.AuxiliaryController.EarthquakePolicy;

import PolicyProject.policyService.application.service.Service.AuxiliaryService.EarthquakePolicy.HouseService;
import PolicyProject.policyService.domain.dto.request.HouseRequest.GetHouseWCustomerRequest;
import PolicyProject.policyService.domain.dto.response.HouseResponse.GetHouseWCustomerResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
public class HouseController_V1Test {

    @Autowired
    private Validator validator;

    @MockBean
    private HouseService houseService;

    @InjectMocks
    private HouseController_V1 houseController_v1;

    private MockMvc mockMvc;

    HouseController_V1Test() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(houseController_v1)
                .setValidator(new LocalValidatorFactoryBean())
                .build();
    }

    @Test
    public void testGetWCustomer_Success() throws Exception {
        GetHouseWCustomerRequest request = new GetHouseWCustomerRequest(123, 45, "CityName", "DistrictName", "NeighborhoodName", 678, "12345678901");
        GetHouseWCustomerResponse response = new GetHouseWCustomerResponse(1L, 123, 200, null, null, "12345678901", 100000L);

        Mockito.when(houseService.getWCustomer(Mockito.any())).thenReturn(response);

        mockMvc.perform(get("/api/v1/house/WCustomer")
                        .param("number", "123")
                        .param("apartmentNumber", "45")
                        .param("city", "CityName")
                        .param("district", "DistrictName")
                        .param("neighborhood", "NeighborhoodName")
                        .param("coverageCode", "678")
                        .param("tckn", "12345678901")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.number").value(response.number()))
                .andExpect(jsonPath("$.squareMeters").value(response.squareMeters()))
                .andExpect(jsonPath("$.tckn").value(response.tckn()))
                .andExpect(jsonPath("$.Amount").value(response.Amount()));
    }

    @Test
    public void testGetWCustomer_InvalidRequest() throws Exception {
        GetHouseWCustomerRequest request = new GetHouseWCustomerRequest(null, 45, "CityName", "DistrictName", "NeighborhoodName", 678, "12345678901");

        mockMvc.perform(get("/api/v1/house/WCustomer")
                        .param("apartmentNumber", "45")
                        .param("city", "CityName")
                        .param("district", "DistrictName")
                        .param("neighborhood", "NeighborhoodName")
                        .param("coverageCode", "678")
                        .param("tckn", "12345678901")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
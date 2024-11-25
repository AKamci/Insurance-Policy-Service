package PolicyProject.policyService.interfaces.controller.Version_1.WeightsController;


import PolicyProject.policyService.application.service.Service.WeightsService.CarPolicyWeightService;
import PolicyProject.policyService.application.service.Service.WeightsService.EarthQuakePolicyWeightService;
import PolicyProject.policyService.domain.dto.request.WeightRequest.CreateWeightRequest;
import PolicyProject.policyService.domain.dto.request.WeightRequest.DeleteWeightRequest;
import PolicyProject.policyService.domain.dto.request.WeightRequest.GetWeightRequest;
import PolicyProject.policyService.domain.dto.request.WeightRequest.UpdateWeightRequest;
import PolicyProject.policyService.domain.dto.response.WeightResponse.CreateWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.DeleteWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.GetWeightResponse;
import PolicyProject.policyService.domain.dto.response.WeightResponse.UpdateWeightResponse;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.CarPolicyWeightsMapper;
import PolicyProject.policyService.interfaces.mappers.WeightsMapper.EarthQuakeWeightMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EarthQuakePolicyWeightController_V1.class)
public class EarthQuakePolicyWeightController_V1Test {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EarthQuakePolicyWeightService carPolicyWeightService;

    @Mock
    private EarthQuakeWeightMapper carPolicyWeightsMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new EarthQuakePolicyWeightController_V1(carPolicyWeightService)).build();
    }

    @Test
    void createWeightTest() throws Exception {
        CreateWeightRequest createWeightRequest = new CreateWeightRequest("exampleKey", new BigDecimal("10.0"), new BigDecimal("5.0"), new BigDecimal("15.0"), "type");
        CreateWeightResponse createWeightResponse = new CreateWeightResponse("exampleKey");

        when(carPolicyWeightService.create(any())).thenReturn(createWeightResponse);

        mockMvc.perform(post("/api/v1/earthquakePolicyWeight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createWeightRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.key").value("exampleKey"));
    }

    @Test
    void getWeightTest() throws Exception {
        GetWeightRequest getWeightRequest = new GetWeightRequest("exampleKey");
        GetWeightResponse getWeightResponse = new GetWeightResponse(1L, "exampleKey", new BigDecimal("10.0"), new BigDecimal("5.0"), new BigDecimal("15.0"), "type");

        when(carPolicyWeightService.get(any())).thenReturn(getWeightResponse);

        mockMvc.perform(get("/api/v1/earthquakePolicyWeight")
                        .param("key", "exampleKey")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("exampleKey"))
                .andExpect(jsonPath("$.weight").value(10.0))
                .andExpect(jsonPath("$.minValue").value(5.0))
                .andExpect(jsonPath("$.maxValue").value(15.0))
                .andExpect(jsonPath("$.type").value("type"));
    }

    @Test
    void updateWeightTest() throws Exception {
        UpdateWeightRequest updateWeightRequest = new UpdateWeightRequest(1L, "exampleKey", new BigDecimal("10.0"), new BigDecimal("5.0"), new BigDecimal("15.0"), "type");
        UpdateWeightResponse updateWeightResponse = new UpdateWeightResponse("exampleKey");

        when(carPolicyWeightService.update(any())).thenReturn(updateWeightResponse);

        mockMvc.perform(put("/api/v1/earthquakePolicyWeight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateWeightRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("exampleKey"));
    }

    @Test
    void deleteWeightTest() throws Exception {
        DeleteWeightRequest deleteWeightRequest = new DeleteWeightRequest("exampleKey");
        DeleteWeightResponse deleteWeightResponse = new DeleteWeightResponse("exampleKey");

        when(carPolicyWeightService.delete(any())).thenReturn(deleteWeightResponse);

        mockMvc.perform(delete("/api/v1/earthquakePolicyWeight")
                        .param("key", "exampleKey")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("exampleKey"));
    }

    @Test
    void getWeightsTest() throws Exception {
        List<GetWeightResponse> getWeightResponseList = List.of(
                new GetWeightResponse(1L, "exampleKey1", new BigDecimal("10.0"), new BigDecimal("5.0"), new BigDecimal("15.0"), "type1"),
                new GetWeightResponse(2L, "exampleKey2", new BigDecimal("20.0"), new BigDecimal("10.0"), new BigDecimal("30.0"), "type2")
        );

        when(carPolicyWeightService.getList()).thenReturn(getWeightResponseList);

        mockMvc.perform(get("/api/v1/earthquakePolicyWeight/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].key").value("exampleKey1"))
                .andExpect(jsonPath("$[0].weight").value(10.0))
                .andExpect(jsonPath("$[0].minValue").value(5.0))
                .andExpect(jsonPath("$[0].maxValue").value(15.0))
                .andExpect(jsonPath("$[0].type").value("type1"))
                .andExpect(jsonPath("$[1].key").value("exampleKey2"))
                .andExpect(jsonPath("$[1].weight").value(20.0))
                .andExpect(jsonPath("$[1].minValue").value(10.0))
                .andExpect(jsonPath("$[1].maxValue").value(30.0))
                .andExpect(jsonPath("$[1].type").value("type2"));
    }

    @Test
    void updateWeightsAllTest() throws Exception {
        List<UpdateWeightRequest> updateWeightRequestList = List.of(
                new UpdateWeightRequest(1L, "exampleKey1", new BigDecimal("10.0"), new BigDecimal("5.0"), new BigDecimal("15.0"), "type1"),
                new UpdateWeightRequest(2L, "exampleKey2", new BigDecimal("20.0"), new BigDecimal("10.0"), new BigDecimal("30.0"), "type2")
        );

        List<UpdateWeightResponse> updateWeightResponseList = List.of(
                new UpdateWeightResponse("exampleKey1"),
                new UpdateWeightResponse("exampleKey2")
        );

        when(carPolicyWeightService.updateList(any())).thenReturn(updateWeightResponseList);

        mockMvc.perform(put("/api/v1/earthquakePolicyWeight/saveAll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateWeightRequestList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].key").value("exampleKey1"))
                .andExpect(jsonPath("$[1].key").value("exampleKey2"));
    }

    @Test
    void getPriceTest() throws Exception {
        GetWeightRequest getWeightRequest = new GetWeightRequest("exampleKey");
        GetWeightResponse getWeightResponse = new GetWeightResponse(1L, "exampleKey", new BigDecimal("10.0"), new BigDecimal("5.0"), new BigDecimal("15.0"), "type");

        when(carPolicyWeightService.get(any())).thenReturn(getWeightResponse);

        mockMvc.perform(get("/api/v1/earthquakePolicyWeight/getPrice")
                        .param("key", "exampleKey")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.key").value("exampleKey"))
                .andExpect(jsonPath("$.weight").value(10.0))
                .andExpect(jsonPath("$.minValue").value(5.0))
                .andExpect(jsonPath("$.maxValue").value(15.0))
                .andExpect(jsonPath("$.type").value("type"));
    }

}

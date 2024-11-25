package PolicyProject.policyService.interfaces.controller.Version_1.PolicyController;

import PolicyProject.policyService.application.service.Service.PolicyService.EarthQuakeService;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.request.EarthQuakeRequest.*;
import PolicyProject.policyService.domain.dto.response.EarthQuakeResponse.*;
import PolicyProject.policyService.infrastructure.persistence.entity.Coverage;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.EarthQuakeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;


@WebMvcTest(EarthQuakePolicyController_V1.class)
public class EarthQuakePolicyController_V1Test {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EarthQuakeService earthQuakeService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void deletePolicy_ShouldReturn200_WhenRequestIsValid() throws Exception {
        DeleteEarthQuakeRequest request = new DeleteEarthQuakeRequest(1L);

        DeleteEarthQuakeResponse response = new DeleteEarthQuakeResponse(1L);

        Mockito.when(earthQuakeService.delete(Mockito.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/earthQuake")
                        .param("policyId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
    @Test
    public void deletePolicy_ShouldReturn400_WhenRequestIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/earthQuake")
                        .param("policyId", (String) null)) // Invalid: policyId is null
                .andExpect(status().isBadRequest());
    }
    @Test
    public void createPolicy_ShouldReturn201_WhenRequestIsValid() throws Exception {
        CreateEarthQuakeRequest request = new CreateEarthQuakeRequest(
                LocalDate.now(),
                1L,
                100,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(365),
                "12345678901",
                50000L
        );

        CreateEarthQuakeResponse response = new CreateEarthQuakeResponse(1L);

        Mockito.when(earthQuakeService.create(Mockito.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/earthQuake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
    @Test
    public void getPolicy_ShouldReturn200_WhenPolicyExists() throws Exception {
        GetEarthQuakeRequest request = new GetEarthQuakeRequest(1L);

        GetEarthQuakeResponse response = new GetEarthQuakeResponse(
                1L,
                LocalDate.now(),
                PolicyState.ACTIVE,
                "Earthquake policy description",
                Coverage.builder().build(),
                1000.0,
                1L,
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(365),
                "12345678901"
        );

        Mockito.when(earthQuakeService.get(Mockito.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/earthQuake")
                        .param("policyId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
    @Test
    public void updatePolicy_ShouldReturn200_WhenRequestIsValid() throws Exception {
        UpdateEarthQuakeRequest request = new UpdateEarthQuakeRequest(
                1L,
                LocalDate.now(),
                "Updated earthquake policy description",
                PolicyState.ACTIVE,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(365),
                1000.0
        );

        UpdateEarthQuakeResponse response = new UpdateEarthQuakeResponse(1L);

        Mockito.when(earthQuakeService.update(Mockito.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/earthQuake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
    @Test
    public void updatePolicy_ShouldReturn400_WhenRequestIsInvalid() throws Exception {
        UpdateEarthQuakeRequest request = new UpdateEarthQuakeRequest(
                null,                       // Invalid: policyId is null
                LocalDate.now(),
                "",                         // Invalid: policyDescription is blank
                PolicyState.ACTIVE,
                1,
                LocalDate.now(),
                LocalDate.now().plusDays(365),
                -1000.0                     // Invalid: policyAmount is negative
        );

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/earthQuake")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void acceptPolicy_ShouldReturn200_WhenRequestIsValid() throws Exception {
        SetStateEarthQuakeRequest request = new SetStateEarthQuakeRequest(1L);

        SetStateEarthQuakeResponse response = new SetStateEarthQuakeResponse(1L);

        Mockito.when(earthQuakeService.accept(Mockito.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/earthQuake/accepted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
    @Test
    public void acceptPolicy_ShouldReturn400_WhenRequestIsInvalid() throws Exception {
        SetStateEarthQuakeRequest request = new SetStateEarthQuakeRequest(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/earthQuake/accepted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void rejectPolicy_ShouldReturn200_WhenRequestIsValid() throws Exception {
        SetStateEarthQuakeRequest request = new SetStateEarthQuakeRequest(1L);

        SetStateEarthQuakeResponse response = new SetStateEarthQuakeResponse(1L);

        Mockito.when(earthQuakeService.reject(Mockito.any())).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/earthQuake/rejected")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
    @Test
    public void rejectPolicy_ShouldReturn400_WhenRequestIsInvalid() throws Exception {
        SetStateEarthQuakeRequest request = new SetStateEarthQuakeRequest(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/earthQuake/rejected")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

    }
    @Test
    public void getPolicies_ShouldReturn200_WhenPoliciesExist() throws Exception {
        GetListEarthQuakeRequest request = new GetListEarthQuakeRequest(
                10, 0, 1L, LocalDate.now(), "Description", 1, PolicyState.ACTIVE, 100.0,
                1L, "12345678901", LocalDate.now(), LocalDate.now().plusDays(365),
                1, 1, "City", "District", "Neighborhood");

        List<GetEarthQuakeResponse> responseList = List.of(
                new GetEarthQuakeResponse(
                        1L,
                        LocalDate.now(),
                        PolicyState.ACTIVE,
                        "Earthquake policy description",
                        Coverage.builder().build(),
                        1000.0,
                        1L,
                        LocalDate.now().plusDays(1),
                        LocalDate.now().plusDays(365),
                        "12345678901"
                )
        );

        Mockito.when(earthQuakeService.getList(any())).thenReturn(responseList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/earthQuake/list")
                        .param("size", "10")
                        .param("page", "0")
                        .param("policyId", "1")
                        .param("policyOfferDate", LocalDate.now().toString())
                        .param("policyDescription", "Description"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseList)));
    }
    @Test
    void testGetTotalRecord_ShouldReturn200_WhenCalled() throws Exception {
        Mockito.when(earthQuakeService.getTotalRecord()).thenReturn(10);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/earthQuake/totalRecord"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }
    @Test
    void testGetTotalRecord_ShouldReturnNonZeroCount_WhenPoliciesExist() throws Exception {
        Mockito.when(earthQuakeService.getTotalRecord()).thenReturn(10);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/earthQuake/totalRecord"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }
    @Test
    public void getPolicies_ShouldReturn400_WhenRequestIsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/earthQuake/list")
                        .param("size", "-1")
                        .param("page", "0"))
                .andExpect(status().isBadRequest());
    }
}



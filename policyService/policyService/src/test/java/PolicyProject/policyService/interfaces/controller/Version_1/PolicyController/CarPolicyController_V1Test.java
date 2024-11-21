package PolicyProject.policyService.interfaces.controller.Version_1.PolicyController;

import PolicyProject.policyService.application.service.Service.PolicyService.CarPolicyService;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.domain.dto.request.CarPolicyRequest.*;
import PolicyProject.policyService.domain.dto.response.CarPolicyResponse.*;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.interfaces.mappers.PolicyMapper.CarPolicyMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import PolicyProject.policyService.domain.dto.response.CarPolicyResponse.GetCarPolicyResponse;
import PolicyProject.policyService.domain.dto.request.CarPolicyRequest.GetCarPolicyListRequest;
import PolicyProject.policyService.domain.dto.request.CarPolicyRequest.GetCarPolicyBetweenDateRequest;
import PolicyProject.policyService.domain.dto.response.CarPolicyResponse.GetCustomerCarPoliciesResponse;

@WebMvcTest(CarPolicyController_V1.class)
public class CarPolicyController_V1Test {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CarPolicyService carPolicyService;
    @MockBean
    private CarPolicyMapper carPolicyMapper;


    @Test
    public void createPolicy_ValidRequest_ReturnsCreated() throws Exception {
        // Arrange
        CreateCarPolicyRequest createCarPolicyRequest = new CreateCarPolicyRequest(
                LocalDate.now().plusDays(1),
                1,
                LocalDate.now().plusDays(2),
                LocalDate.now().plusDays(3),
                "12345678901",
                2000L,
                "34XYZ34"
        );

        CreateCarPolicyResponse createCarPolicyResponse = new CreateCarPolicyResponse(1L);

        Mockito.when(carPolicyMapper.createCarPolicyRequestToCarPolicyModel(createCarPolicyRequest))
                .thenReturn(new CarPolicyModel(null, null,null,
                        null, null,null,null, null,null
                        ,null, null,null,0, 0,null,null, null));
        Mockito.when(carPolicyService.create(Mockito.any(CarPolicyModel.class)))
                .thenReturn(createCarPolicyResponse);

        // Act & Assert
        mockMvc.perform(post("/api/v1/carPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"policyOfferDate\": \"" + LocalDate.now().plusDays(1).toString() + "\", " +
                                "\"coverageCode\": 1, " +
                                "\"policyStartDate\": \"" + LocalDate.now().plusDays(2).toString() + "\", " +
                                "\"policyEndDate\": \"" + LocalDate.now().plusDays(3).toString() + "\", " +
                                "\"tckn\": \"12345678901\", " +
                                "\"policyAmount\": 2000, " +
                                "\"licensePlateNumber\": \"34XYZ34\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.policyId").value(1L));
    }
    @Test
    public void getTotalRecord_ReturnsTotalRecord() throws Exception {
        // Arrange
        int totalRecord = 100;
        when(carPolicyService.getTotalRecord()).thenReturn(totalRecord);

        // Act & Assert
        mockMvc.perform(get("/api/v1/carPolicy/totalRecord"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(totalRecord));
    }
    @Test
    public void getTotalRecord_InternalServerError() throws Exception {
        // Arrange
        when(carPolicyService.getTotalRecord()).thenThrow(new RuntimeException("Internal Server Error"));

        // Act & Assert
        mockMvc.perform(get("/api/v1/carPolicy/totalRecord"))
                .andExpect(status().isInternalServerError());
    }
    @Test
    public void rejectCarPolicy_ValidRequest_ReturnsOk() throws Exception {
        // Arrange
        SetCarPolicyStatusRequest setCarPolicyStatusRequest = new SetCarPolicyStatusRequest(1L);
        SetCarPolicyStatusResponse setCarPolicyStatusResponse = new SetCarPolicyStatusResponse(1L);

        Mockito.when(carPolicyMapper.setStateCarPolicyRequestToCarPolicyModel(setCarPolicyStatusRequest))
                .thenReturn(new CarPolicyModel(null, null,null,
                        null, null,null,null, null,null
                        ,null, null,null,0, 0,null,null, null));
        Mockito.when(carPolicyService.rejectCarPolicy(Mockito.any(CarPolicyModel.class)))
                .thenReturn(setCarPolicyStatusResponse);

        // Act & Assert
        mockMvc.perform(put("/api/v1/carPolicy/rejected")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"policyId\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }
    @Test
    public void rejectCarPolicy_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Invalid Request - Empty Body
        mockMvc.perform(put("/api/v1/carPolicy/rejected")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void acceptCarPolicy_ValidRequest_ReturnsAccepted() throws Exception {
        // Arrange
        SetCarPolicyStatusRequest setCarPolicyStatusRequest = new SetCarPolicyStatusRequest(1L);
        SetCarPolicyStatusResponse setCarPolicyStatusResponse = new SetCarPolicyStatusResponse(1L);

        Mockito.when(carPolicyMapper.setStateCarPolicyRequestToCarPolicyModel(setCarPolicyStatusRequest))
                .thenReturn(new CarPolicyModel(null, null,null,
                        null, null,null,null, null,null
                        ,null, null,null,0, 0,null,null, null));
        Mockito.when(carPolicyService.acceptCarPolicy(Mockito.any(CarPolicyModel.class)))
                .thenReturn(setCarPolicyStatusResponse);

        // Act & Assert
        mockMvc.perform(put("/api/v1/carPolicy/accepted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"policyId\": 1 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }
    @Test
    public void acceptCarPolicy_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Invalid Request - Empty Body
        mockMvc.perform(put("/api/v1/carPolicy/accepted")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void createPolicy_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Invalid Request - Empty Body
        mockMvc.perform(post("/api/v1/carPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void getPolicy_ValidRequest_ReturnsPolicy() throws Exception {
        // Arrange
        GetCarPolicyRequest getCarPolicyRequest = new GetCarPolicyRequest(1L);
        GetCarPolicyResponse getCarPolicyResponse = new GetCarPolicyResponse(
                1L,
                LocalDate.now(),
                null,
                "Policy Description",
                null,
                1500.0,
                1L,
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                "34XYZ34",
                "12345678901"
        );

        Mockito.when(carPolicyMapper.getCarPolicyRequestTocarPolicyModel(getCarPolicyRequest))
                .thenReturn(new CarPolicyModel(null, null,null,
                        null, null,null,null, null,null
                        ,null, null,null,0, 0,null,null, null));
        Mockito.when(carPolicyService.get(Mockito.any(CarPolicyModel.class)))
                .thenReturn(getCarPolicyResponse);

        // Act & Assert
        mockMvc.perform(get("/api/v1/carPolicy")
                        .param("policyId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }
    @Test
    public void getPolicy_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Invalid Request - Missing policyId
        mockMvc.perform(get("/api/v1/carPolicy")
                        .param("policyId", ""))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void getPolicyByPlate_ValidRequest_ReturnsPolicies() throws Exception {
        // Arrange
        GetCarPolicyWPlateRequest getCarPolicyWPlateRequest = new GetCarPolicyWPlateRequest("12345678901", "34XYZ34");
        GetCarPolicyResponse getCarPolicyResponse = new GetCarPolicyResponse(
                1L,
                LocalDate.now(),
                null,
                "Policy Description",
                null,
                1500.0,
                1L,
                LocalDate.now(),
                LocalDate.now().plusYears(1),
                "34XYZ34",
                "12345678901"
        );

        Mockito.when(carPolicyMapper.getCarPolicyRequestWPlateTocarPolicyModel(getCarPolicyWPlateRequest))
                .thenReturn(new CarPolicyModel(null, null,null,
                        null, null,null,null, null,null
                        ,null, null,null,0, 0,null,null, null));
        Mockito.when(carPolicyService.getByPlate(Mockito.any(CarPolicyModel.class)))
                .thenReturn(List.of(getCarPolicyResponse));

        // Act & Assert
        mockMvc.perform(get("/api/v1/carPolicy/byPlate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"tckn\": \"12345678901\", \"licensePlateNumber\": \"34XYZ34\" }"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].policyId").value(1L));
    }
    @Test
    public void getPolicyByPlate_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Invalid Request - Empty Body
        mockMvc.perform(get("/api/v1/carPolicy/byPlate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void updateCarPolicy_ValidRequest_ReturnsUpdated() throws Exception {
        // Arrange
        UpdateCarPolicyRequest updateCarPolicyRequest = new UpdateCarPolicyRequest(
                1L,
                LocalDate.now().minusDays(1),
                "Updated description",
                PolicyState.ACTIVE,
                2,
                LocalDate.now().minusDays(3),
                LocalDate.now().plusYears(1),
                3000.0
        );

        UpdateCarPolicyResponse updateCarPolicyResponse = new UpdateCarPolicyResponse(1L);

        Mockito.when(carPolicyMapper.updateCarPolicyRequestToCarPolicyModel(updateCarPolicyRequest))
                .thenReturn(new CarPolicyModel(null, null,null,
                        null, null,null,null, null,null
                        ,null, null,null,0, 0,null,null, null));
        Mockito.when(carPolicyService.update(Mockito.any(CarPolicyModel.class)))
                .thenReturn(updateCarPolicyResponse);

        // Act & Assert
        mockMvc.perform(put("/api/v1/carPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"policyId\": 1, " +
                                "\"policyOfferDate\": \"" + LocalDate.now().minusDays(1).toString() + "\", " +
                                "\"policyDescription\": \"Updated description\", " +
                                "\"state\": \"ACTIVE\", " +
                                "\"coverage\": 2, " +
                                "\"policyStartDate\": \"" + LocalDate.now().minusDays(3).toString() + "\", " +
                                "\"policyEndDate\": \"" + LocalDate.now().plusYears(1).toString() + "\", " +
                                "\"policyAmount\": 3000.0 }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }
    @Test
    public void updateCarPolicy_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Invalid Request - Empty Body
        mockMvc.perform(put("/api/v1/carPolicy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void getPolicies_ValidRequest_ReturnsPolicies() throws Exception {
        // Arrange
        GetCarPolicyListRequest getCarPolicyListRequest = new GetCarPolicyListRequest(
                10, 0, 1L, LocalDate.now(), "Description", 1, PolicyState.ACTIVE, 2000.0, 1L, "34XYZ34", "12345678901", LocalDate.now(), LocalDate.now().plusYears(1)
        );
        GetCarPolicyResponse getCarPolicyResponse = new GetCarPolicyResponse(
                1L, LocalDate.now(), null, "Description", null, 2000.0, 1L, LocalDate.now(), LocalDate.now().plusYears(1), "34XYZ34", "12345678901");

        Mockito.when(carPolicyMapper.getCarPoliciesToCarPolicyModel(getCarPolicyListRequest))
                .thenReturn(new CarPolicyModel(null, null,null,
                        null, null,null,null, null,null
                        ,null, null,null,0, 0,null,null, null));
        Mockito.when(carPolicyService.getList(new CarPolicyModel(null, null,null,
                        null, null,null,null, null,null
                        ,null, null,null,0, 0,null,null, null)))
                .thenReturn(List.of(getCarPolicyResponse));

        // Act & Assert
        mockMvc.perform(get("/api/v1/carPolicy/list")
                        .param("size", "10")
                        .param("page", "0")
                        .param("policyId", "1")
                        .param("policyOfferDate", LocalDate.now().toString())
                        .param("policyDescription", "Description")
                        .param("coverageCode", "1")
                        .param("state", "ACTIVE")
                        .param("policyAmount", "2000.0")
                        .param("customerId", "1")
                        .param("licensePlateNumber", "34XYZ34")
                        .param("tckn", "12345678901")
                        .param("policyStartDate", LocalDate.now().toString())
                        .param("policyEndDate", LocalDate.now().plusYears(1).toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].policyId").value(1L));
    }
    @Test
    public void getPolicies_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Invalid Request - Missing required parameters
        mockMvc.perform(get("/api/v1/carPolicy/list")
                .param("size", "10")).andExpect(status().isBadRequest());
    }
    @Test
    public void deletePolicy_ValidRequest_ReturnsOk() throws Exception {
        // Arrange
        DeleteCarPolicyRequest deleteCarPolicyRequest = new DeleteCarPolicyRequest(1L);
        DeleteCarPolicyResponse deleteCarPolicyResponse = new DeleteCarPolicyResponse(1L);

        Mockito.when(carPolicyMapper.deleteCarPolicyRequestToCarPolicyModel(deleteCarPolicyRequest))
                .thenReturn(new CarPolicyModel(null, null,null,
                        null, null,null,null, null,null
                        ,null, null,null,0, 0,null,null, null));
        Mockito.when(carPolicyService.delete(Mockito.any(CarPolicyModel.class)))
                .thenReturn(deleteCarPolicyResponse);

        // Act & Assert
        mockMvc.perform(delete("/api/v1/carPolicy")
                        .param("policyId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyId").value(1L));
    }
    @Test
    public void deletePolicy_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Invalid Request - Missing policyId
        mockMvc.perform(delete("/api/v1/carPolicy")
                        .param("policyId", ""))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void customerPolicies_ValidRequest_ReturnsPolicies() throws Exception {
        // Arrange
        GetCustomerCarPoliciesRequest getCustomerCarPoliciesRequest = new GetCustomerCarPoliciesRequest("12345678901");
        GetCustomerCarPoliciesResponse getCustomerCarPoliciesResponse = new GetCustomerCarPoliciesResponse(
                "1L",
                null,
                0,
                null,
                1500.0
        );

        Mockito.when(carPolicyMapper.getCustomerCarPoliciesToCarPolicyModel(getCustomerCarPoliciesRequest))
                .thenReturn(new CarPolicyModel(null, null,null,
                        null, null,null,null, null,null
                        ,null, null,null,0, 0,null,null, null));
        Mockito.when(carPolicyService.get_wPolicy(Mockito.any(CarPolicyModel.class)))
                .thenReturn(List.of(getCustomerCarPoliciesResponse));

        // Act & Assert
        mockMvc.perform(get("/api/v1/carPolicy/customerPolicies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"tckn\": \"12345678901\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].policyId").value(1L));
    }
    @Test
    public void customerPolicies_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Invalid Request - Empty Body
        mockMvc.perform(get("/api/v1/carPolicy/customerPolicies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void customerPoliciesBetweenDate_ValidRequest_ReturnsPolicies() throws Exception {
        // Arrange
        GetCarPolicyBetweenDateRequest getCarPolicyBetweenDateRequest = new GetCarPolicyBetweenDateRequest(
                Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(1)));
        GetCustomerCarPoliciesResponse getCustomerCarPoliciesResponse = new GetCustomerCarPoliciesResponse(
                "1L", null, 0, null, 1500.0);

        Mockito.when(carPolicyMapper.getCustomerCarPoliciesBetweenDateToCarPolicyModel(getCarPolicyBetweenDateRequest))
                .thenReturn(new CarPolicyModel(null, null,null,
                        null, null,null,null, null,null
                ,null, null,null,0, 0,null,null, null));
        Mockito.when(carPolicyService.get_Policies_BetweenDate(Mockito.any(CarPolicyModel.class)))
                .thenReturn(List.of(getCustomerCarPoliciesResponse));

        // Act & Assert
        mockMvc.perform(get("/api/v1/carPolicy/customerPoliciesBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"startDate\": \"" + LocalDate.now().toString() + "\", " +
                                "\"endDate\": \"" + LocalDate.now().plusDays(1).toString() + "\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].policyId").value("1L"));
    }
    @Test
    public void customerPoliciesBetweenDate_InvalidRequest_ReturnsBadRequest() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/v1/carPolicy/customerPoliciesBetweenDate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}


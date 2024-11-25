package PolicyProject.policyService.interfaces.controller.Version_1;


import PolicyProject.policyService.application.service.Service.CustomerService;
import PolicyProject.policyService.domain.dto.request.CustomerRequest.*;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.CreateCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.DeleteCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.GetCustomerResponse;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.UpdateCustomerResponse;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController_V1.class)
public class CustomerController_V1Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getTotalRecord_validRequest_success() throws Exception {
        when(customerService.getTotalRecord()).thenReturn(10);

        mockMvc.perform(get("/api/v1/customer/totalRecord")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(10));
    }

    @Test
    void getTotalRecord_noRecords_success() throws Exception {
        when(customerService.getTotalRecord()).thenReturn(0);

        mockMvc.perform(get("/api/v1/customer/totalRecord")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(0));
    }

    @Test
    void getTotalRecord_serverError() throws Exception {
        when(customerService.getTotalRecord()).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR));

        mockMvc.perform(get("/api/v1/customer/totalRecord")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void deleteCustomer_validRequest_success() throws Exception {
        DeleteCustomerRequest request = new DeleteCustomerRequest("12345678901");
        DeleteCustomerResponse response = new DeleteCustomerResponse("12345678901");

        when(customerService.delete(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(delete("/api/v1/customer")
                        .param("tckn", "12345678901")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tckn").value("12345678901"));
    }

    @Test
    void deleteCustomer_notFoundError() throws Exception {
        when(customerService.delete(ArgumentMatchers.any())).thenThrow(new EntityNotFoundException(999L, "Entity Not Found"));

        mockMvc.perform(delete("/api/v1/customer")
                        .param("tckn", "99999999999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteCustomer_validationError() throws Exception {
        mockMvc.perform(delete("/api/v1/customer")
                        .param("tckn", (String) null)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createCustomer_validRequest_success() throws Exception {
        CreateCustomerRequest request = new CreateCustomerRequest(
                "John Doe",
                "12345678901",
                "123 Street",
                "5316414895",
                "john.doe@example.com",
                LocalDate.now().minusYears(25),
                1
        );

        CreateCustomerResponse response = new CreateCustomerResponse("12345678901", 1L);

        when(customerService.create(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.tckn").value("12345678901"));
    }

    @Test
    void getCustomer_validRequest_success() throws Exception {
        GetCustomerRequest request = new GetCustomerRequest("12345678901");

        GetCustomerResponse response = new GetCustomerResponse(
                1L, "John Doe", "12345678901", "123 Street", "+123456789",
                "john.doe@example.com", LocalDate.now().minusYears(25), 1
        );

        when(customerService.get(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(get("/api/v1/customer")
                        .param("tckn", "12345678901")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.tckn").value("12345678901"));
    }

    @Test
    void getCustomer_notFoundError() throws Exception {
        when(customerService.get(ArgumentMatchers.any())).thenThrow(new EntityNotFoundException(999L, "Entity Not Found"));

        mockMvc.perform(get("/api/v1/customer")
                        .param("tckn", "99999999933")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateCustomer_validRequest_success() throws Exception {
        UpdateCustomerRequest request = new UpdateCustomerRequest(
                "12345678901", "John Doe", "123 Street", "5214147898", "john.doe@example.com",
                LocalDate.now().minusYears(25), 1
        );

        UpdateCustomerResponse response = new UpdateCustomerResponse( "12345678901");

        when(customerService.update(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(put("/api/v1/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tckn").value("12345678901"));
    }

    @Test
    void getCustomers_validRequest_success() throws Exception {
        GetCustomerListRequest request = new GetCustomerListRequest(
                1L, "John Doe", "12345678901", "123 Street", "5314148995", "john.doe@example.com",
                "password123", LocalDate.now().minusYears(25), 1, 0, 10
        );

        List<GetCustomerResponse> response = List.of(
                new GetCustomerResponse(
                        1L, "John Doe", "12345678901", "123 Street", "+123456789",
                        "john.doe@example.com", LocalDate.now().minusYears(25), 1
                )
        );

        when(customerService.getList(ArgumentMatchers.any())).thenReturn(response);

        mockMvc.perform(get("/api/v1/customer/list")
                        .param("id", "1")
                        .param("name", "John Doe")
                        .param("tckn", "12345678901")
                        .param("address", "123 Street")
                        .param("phone", "5314147895")
                        .param("email", "john.doe@example.com")
                        .param("password", "password123")
                        .param("birthDay", LocalDate.now().minusYears(25).toString())
                        .param("gender", "1")
                        .param("page", "0")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id").value(1L))
                .andExpect(jsonPath("$.[0].tckn").value("12345678901"));
    }

    @Test
    void getCustomers_invalidRequest_missingPage() throws Exception {
        mockMvc.perform(get("/api/v1/customer/list")
                        .param("size", "10")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}
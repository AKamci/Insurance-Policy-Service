package PolicyProject.policyService.infrastructure.exception.ExceptionHandler;

import PolicyProject.policyService.infrastructure.exception.AlreadyExistsException.HealthPolicyAlreadyExistsException;
import PolicyProject.policyService.infrastructure.exception.DuplicateException.DuplicateTcknException;
import PolicyProject.policyService.infrastructure.exception.DuplicateException.DuplicateWeightKeyException;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.exception.ValidationException.CustomerValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import PolicyProject.policyService.infrastructure.exception.ValidationException.CarPolicyValidationException;
import PolicyProject.policyService.infrastructure.exception.ExpiredException.ExpiredMedicalReportException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(GlobalExceptionHandler.class)
@AutoConfigureMockMvc
@Import(GlobalExceptionHandler.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarPolicyValidationException carPolicyValidationException;

    @MockBean
    private HealthPolicyAlreadyExistsException healthPolicyAlreadyExistsException;

    @MockBean
    private CustomerValidationException customerValidationException;

    @MockBean
    private EntityNotFoundException entityNotFoundException;

    @MockBean
    private DuplicateTcknException duplicateTcknException;

    @MockBean
    private DuplicateWeightKeyException duplicateWeightKeyException;

    @MockBean
    private ExpiredMedicalReportException expiredMedicalReportException;


    @Test
    void shouldHandleCarPolicyValidationException() throws Exception {
        String errorMessage = "CarPolicy validation failed";

        Mockito.when(carPolicyValidationException.getMessage()).thenThrow(CarPolicyValidationException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/someEndpoint")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void shouldHandleDuplicateTcknException() throws Exception {
        String errorMessage = "TCKN already exists";

        DuplicateTcknException duplicateTcknException = new DuplicateTcknException(errorMessage, "12345678901");
        Mockito.when(duplicateTcknException.getMessage()).thenReturn(errorMessage);

        mockMvc.perform(MockMvcRequestBuilders.get("/duplicateTcknEndpoint")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CONFLICT.value()));
    }

    @Test
    void shouldHandleEntityNotFoundValidationException() throws Exception {
        String errorMessage = "Entity not found";

        Mockito.when(entityNotFoundException.getMessage()).thenReturn(errorMessage);

        mockMvc.perform(MockMvcRequestBuilders.get("/entityEndpoint")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void shouldHandleCustomerValidationException() throws Exception {
        String errorMessage = "Customer validation failed";

        Mockito.when(customerValidationException.getMessage()).thenReturn(errorMessage);

        mockMvc.perform(MockMvcRequestBuilders.get("/someOtherEndpoint")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    void shouldHandleDuplicateWeightKeyException() throws Exception {
        String errorMessage = "Weight key already exists";
        String key = "weight123";

        Mockito.when(duplicateWeightKeyException.getMessage()).thenReturn(errorMessage);

        mockMvc.perform(MockMvcRequestBuilders.get("/duplicateWeightKeyEndpoint")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CONFLICT.value()));
    }

    @Test
    void shouldHandleExpiredMedicalReportException() throws Exception {
        String errorMessage = "Medical report has expired";
        ExpiredMedicalReportException expiredMedicalReportException = new ExpiredMedicalReportException(123L, errorMessage);
        Mockito.when(expiredMedicalReportException.getMessage()).thenReturn(errorMessage);

        mockMvc.perform(MockMvcRequestBuilders.get("/expiredMedicalReportEndpoint")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.FORBIDDEN.value()));
    }

    @Test
    void shouldHandleValidationExceptions() throws Exception {
        String fieldName = "name";
        String fieldMessage = "must not be null";

        MethodArgumentNotValidException methodArgumentNotValidException = Mockito.mock(MethodArgumentNotValidException.class);
        Mockito.when(methodArgumentNotValidException.getBindingResult())
                .thenReturn(new org.springframework.validation.BindException(new Object(), "target"));

        mockMvc.perform(MockMvcRequestBuilders.get("/validationEndpoint")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value(fieldMessage));
    }


    @Test
    void shouldHandleHealthPolicyAlreadyExistsException() throws Exception {
        String errorMessage = "Health policy already exists";

        HealthPolicyAlreadyExistsException healthPolicyAlreadyExistsException = new HealthPolicyAlreadyExistsException(errorMessage,1L);
        Mockito.when(healthPolicyAlreadyExistsException.getMessage()).thenReturn(errorMessage);

        mockMvc.perform(MockMvcRequestBuilders.get("/healthPolicyExistsEndpoint")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.CONFLICT.value()));
    }
}




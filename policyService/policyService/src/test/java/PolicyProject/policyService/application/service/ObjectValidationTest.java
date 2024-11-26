package PolicyProject.policyService.application.service;

import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ObjectValidationTest {

    @InjectMocks
    private ObjectValidation objectValidation;

    @Test
    void testValidateModelWithNonNullModel() {
        CustomerModel customer = new CustomerModel(null,null,null,null,null,null,
                null,0,null,0,0,null);
        assertDoesNotThrow(() -> objectValidation.validateModel(customer, "CustomerModel"));
    }

    @Test
    void testValidateModelWithNullModel() {
        assertThrows(IllegalArgumentException.class, () -> objectValidation.validateModel(null, "CustomerModel"));
    }

    @Test
    void testValidateModelListWithNonNullElements() {
        List<CustomerModel> customers = List.of(new CustomerModel(null, null, null, null, null, null, null, 0, null, 0, 0, null));
        assertDoesNotThrow(() -> objectValidation.validateModelList(customers, "CustomerModelList"));
    }

    @Test
    void testValidateModelListWithNullElements() {
        List<CustomerModel> customers = new ArrayList<>();
        customers.add(null);
        customers.add(new CustomerModel(null, null, null, null, null, null, null, 0, null, 0, 0, null));
        assertThrows(IllegalArgumentException.class, () -> objectValidation.validateModelList(customers, "CustomerModelList"));
    }

    @Test
    void testValidateModelListWithNullList() {
        assertThrows(IllegalArgumentException.class, () -> objectValidation.validateModelList(null, "CustomerModelList"));
    }

}
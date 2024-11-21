package PolicyProject.policyService.application.service;

import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ObjectValidationTest {

    @Autowired
    private ObjectValidation objectValidation;

    @Test
    void testValidateModelWithNonNullModel() {
        ObjectValidation validator = new ObjectValidation();
        CustomerModel customer = new CustomerModel(null,null,null,null,null,null,
                null,0,null,0,0,null);
        assertDoesNotThrow(() -> validator.validateModel(customer, "CustomerModel"));
    }

    @Test
    void testValidateModelWithNullModel() {
        ObjectValidation validator = new ObjectValidation();
        assertThrows(IllegalArgumentException.class, () -> validator.validateModel(null, "CustomerModel"));
    }

}
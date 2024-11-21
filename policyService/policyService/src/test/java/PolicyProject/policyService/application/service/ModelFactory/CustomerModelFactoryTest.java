package PolicyProject.policyService.application.service.ModelFactory;

import PolicyProject.policyService.domain.model.CustomerModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerModelFactoryTest {

    @Test
    public void testCreateCustomerModelWithTckn() {
        String tckn = "12345678901";
        CustomerModel customerModel = CustomerModelFactory.createCustomerModelWithTckn(tckn);
        assertEquals(tckn, customerModel.tckn(), "TCKN mismatch");
    }
}
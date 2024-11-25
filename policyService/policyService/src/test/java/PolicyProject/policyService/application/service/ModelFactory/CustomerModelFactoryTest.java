package PolicyProject.policyService.application.service.ModelFactory;

import PolicyProject.policyService.domain.model.CustomerModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.function.Executable;

public class CustomerModelFactoryTest {

    @Test
    public void testCreateCustomerModelWithTckn() {
        String tckn = "12345678901";
        CustomerModel customerModel = CustomerModelFactory.createCustomerModelWithTckn(tckn);
        assertEquals(tckn, customerModel.tckn(), "TCKN mismatch");
    }

    @Test
    public void testCreateCustomerModelWithNullTckn() {
        String tckn = null;
        Executable executable = () -> CustomerModelFactory.createCustomerModelWithTckn(tckn);
        assertThrows(IllegalArgumentException.class, executable, "Expected exception for null TCKN");
    }

    @Test
    public void testCreateCustomerModelWithEmptyTckn() {
        String tckn = "";
        CustomerModel customerModel = CustomerModelFactory.createCustomerModelWithTckn(tckn);
        assertEquals(tckn, customerModel.tckn(), "TCKN mismatch");
    }

    @Test
    public void testCreateCustomerModelWithTcknInvalidLength() {
        String tckn = "123";
        CustomerModel customerModel = CustomerModelFactory.createCustomerModelWithTckn(tckn);
        assertEquals(tckn, customerModel.tckn(), "TCKN mismatch");
    }


}
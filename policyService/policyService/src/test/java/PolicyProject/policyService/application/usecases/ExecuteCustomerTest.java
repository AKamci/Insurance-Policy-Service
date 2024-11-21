package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.CustomerSpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExecuteCustomerTest {

    @Mock
    private CustomerGateway customerGateway;

    @Mock
    private CustomerSpecificationBuild customerSpecificationBuild;

    @InjectMocks
    private ExecuteCustomer executeCustomer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteUpdate() {
        // Arrange
        CustomerModel customerModel = new CustomerModel(1L, null,null, null,null, null,
                null,0,0, 0,0, null
        );
        Customer customer = new Customer();
        when(customerGateway.update(any(Customer.class))).thenReturn(customer);
        when(CustomerMapper.INSTANCE.customerModelToCustomerEntity(any(CustomerModel.class))).thenReturn(customer);
        when(CustomerMapper.INSTANCE.customerEntityToCustomerModel(any(Customer.class))).thenReturn(customerModel);

        // Act
        CustomerModel result = executeCustomer.executeUpdate(customerModel);

        // Assert
        assertNotNull(result);
        assertEquals(customerModel, result);
    }

    @Test
    void testExecuteCreate() {
        // Arrange
        CustomerModel customerModel = new CustomerModel(null, "null","12345678901", "null","null", "null",
                null,0,0, 0,0, null
        );
        Customer customer = new Customer();
        when(customerGateway.create(any(Customer.class))).thenReturn(customer);
        when(CustomerMapper.INSTANCE.customerModelToCustomerEntity(any(CustomerModel.class))).thenReturn(customer);
        when(CustomerMapper.INSTANCE.customerEntityToCustomerModel(any(Customer.class))).thenReturn(customerModel);

        // Act
        CustomerModel result = executeCustomer.executeCreate(customerModel);

        // Assert
        assertNotNull(result);
        assertEquals(customerModel, result);
    }

    @Test
    void testExecuteGet() {
        // Arrange
        CustomerModel customerModel = new CustomerModel(null, null,"12345678901", null,null, null,
                null,0,0, 0,0, null
        );
        Customer customer = new Customer();
        customer.setTckn("12345678901");
        when(customerGateway.get(any(Customer.class))).thenReturn(customer);
        when(CustomerMapper.INSTANCE.customerModelToCustomerEntity(any(CustomerModel.class))).thenReturn(customer);
        when(CustomerMapper.INSTANCE.customerEntityToCustomerModel(any(Customer.class))).thenReturn(customerModel);

        // Act
        CustomerModel result = executeCustomer.executeGet(customerModel);

        // Assert
        assertNotNull(result);
        assertEquals(customerModel, result);
    }

    @Test
    void testExecuteDelete() {
        // Arrange
        CustomerModel customerModel = new CustomerModel(1L, null,null, null,null, null,
                null,0,0, 0,0, null
        );
        Customer customer = new Customer();
        when(customerGateway.delete(any(Customer.class))).thenReturn(customer);
        when(CustomerMapper.INSTANCE.customerModelToCustomerEntity(any(CustomerModel.class))).thenReturn(customer);
        when(CustomerMapper.INSTANCE.customerEntityToCustomerModel(any(Customer.class))).thenReturn(customerModel);

        // Act
        CustomerModel result = executeCustomer.executeDelete(customerModel);

        // Assert
        assertNotNull(result);
        assertEquals(customerModel, result);
    }

    @Test
    void testExecuteGetList() {
        // Arrange
        CustomerModel customerModel = new CustomerModel(null, null,"12345678901", null,null, null,
                null,0,0, 0,0, null
        );
        List<Customer> customerList = List.of(new Customer());
        when(customerSpecificationBuild.CustomerBuild(any(Customer.class))).thenReturn(mock(Specification.class));
        when(customerGateway.getList(any(Specification.class), anyInt(), anyInt())).thenReturn(customerList);
        when(CustomerMapper.INSTANCE.CustomerEntityListToCustomerModelList(any(List.class))).thenReturn(List.of(customerModel));

        // Act
        List<CustomerModel> result = executeCustomer.executeGetList(customerModel);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(customerModel, result.get(0));
    }

    @Test
    void testExecuteGetTotalRecord() {
        // Arrange
        when(customerGateway.getTotal()).thenReturn(10);

        // Act
        int result = executeCustomer.executeGetTotalRecord();

        // Assert
        assertEquals(10, result);
    }
}
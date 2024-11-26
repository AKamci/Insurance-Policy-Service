package PolicyProject.policyService.application.usecases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.CustomerSpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public class ExecuteCustomerTest {

    @Mock
    private CustomerGateway customerGateway;

    @Mock
    private CustomerSpecificationBuild customerSpecificationBuild;

    @InjectMocks
    private ExecuteCustomer executeCustomer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeUpdate_success() {
        CustomerModel customerModel = new CustomerModel(1L, null, null,null, null
        ,null, null,0, null,0, 0,null);

        Customer customerEntity = new Customer();
        customerEntity.setId(1L);

        when(customerGateway.update(any(Customer.class))).thenReturn(customerEntity);


        CustomerModel result = executeCustomer.executeUpdate(customerModel);

        assertNotNull(result);
        assertEquals(customerModel.id(), result.id());
    }

    @Test
    void executeUpdate_entityNotFound() {
        CustomerModel customerModel = new CustomerModel(1L, null, null,null, null
                ,null, null,0, null,0, 0,null);

        when(customerGateway.update(any(Customer.class))).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            executeCustomer.executeUpdate(customerModel);
        });
    }

    @Test
    void executeCreate_success() {
        CustomerModel customerModel = new CustomerModel(1L, null, null,null, null
                ,null, null,0, null,0, 0,null);

        Customer customerEntity = new Customer();
        customerEntity.setId(1L);

        when(customerGateway.create(any(Customer.class))).thenReturn(customerEntity);

        CustomerModel result = executeCustomer.executeCreate(customerModel);

        assertNotNull(result);
        assertEquals(customerModel.id(), result.id());
    }

    @Test
    void executeGet_success() {
        CustomerModel customerModel = new CustomerModel(1L, null, "12345678998",null, null
                ,null, null,0, null,0, 0,null);

        Customer customerEntity = new Customer();
        customerEntity.setId(1L);
        customerEntity.setTckn("12345678998");

        when(customerGateway.get(any(Customer.class))).thenReturn(customerEntity);

        CustomerModel result = executeCustomer.executeGet(customerModel);

        assertNotNull(result);
        assertEquals(customerModel.tckn(), result.tckn());
    }

    @Test
    void executeGet_entityNotFound() {
        CustomerModel customerModel = new CustomerModel(1L, null, "12345678998",null, null
                ,null, null,0, null,0, 0,null);;

        when(customerGateway.get(any(Customer.class))).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            executeCustomer.executeGet(customerModel);
        });
    }

    @Test
    void executeDelete_success() {
        CustomerModel customerModel = new CustomerModel(1L, null, "12345678998",null, null
                ,null, null,0, null,0, 0,null);

        Customer customerEntity = new Customer();
        customerEntity.setId(1L);

        when(customerGateway.delete(any(Customer.class))).thenReturn(customerEntity);


        CustomerModel result = executeCustomer.executeDelete(customerModel);

        assertNotNull(result);
        assertEquals(customerModel.id(), result.id());
    }

    @Test
    void executeDelete_entityNotFound() {
        CustomerModel customerModel = new CustomerModel(1L, null, "12345678998",null, null
                ,null, null,0, null,0, 0,null);

        when(customerGateway.delete(any(Customer.class))).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            executeCustomer.executeDelete(customerModel);
        });
    }

    @Test
    void executeGetList_success() {
        CustomerModel customerModel = new CustomerModel(1L, null, "12345678998",null, null
                ,null, null,0, null,0, 10,null);

        Specification<Customer> specification = mock(Specification.class);
        List<Customer> customerList = List.of(new Customer());

        when(customerSpecificationBuild.CustomerBuild(any(Customer.class))).thenReturn(specification);
        when(customerGateway.getList(any(), anyInt(), anyInt())).thenReturn(customerList);


        List<CustomerModel> result = executeCustomer.executeGetList(customerModel);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void executeGetList_entityNotFound() {
        CustomerModel customerModel = new CustomerModel(1L, null, "12345678998",null, null
                ,null, null,0, null,0, 10,null);

        Specification<Customer> specification = mock(Specification.class);

        when(customerSpecificationBuild.CustomerBuild(any(Customer.class))).thenReturn(specification);
        when(customerGateway.getList(any(), anyInt(), anyInt())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> {
            executeCustomer.executeGetList(customerModel);
        });
    }

    @Test
    void executeGetTotalRecord_success() {
        int totalRecords = 5;

        when(customerGateway.getTotal()).thenReturn(totalRecords);

        int result = executeCustomer.executeGetTotalRecord();

        assertEquals(totalRecords, result);
    }
}
package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private ExecuteCustomer executeCustomer;

    @Mock
    private ObjectValidation objectValidation;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private CustomerModel customerModel;

    @Mock
    private CustomerModel customerModel1;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGet_ValidCustomerModel_ReturnsGetCustomerResponse() {
        when(executeCustomer.executeGet(customerModel)).thenReturn(customerModel);
        doNothing().when(objectValidation).validateModel(customerModel, "customerModel");

        customerService.get(customerModel);

        verify(executeCustomer, times(1)).executeGet(customerModel);
        verify(objectValidation, times(1)).validateModel(any(CustomerModel.class), any(String.class));
    }

    @Test
    void testGetList_ValidCustomerModel_ReturnsListGetCustomerResponse() {
        when(executeCustomer.executeGetList(customerModel)).thenReturn(List.of(customerModel, customerModel1));
        doNothing().when(objectValidation).validateModel(customerModel, "customerModel");

        customerService.getList(customerModel);

        verify(executeCustomer, times(1)).executeGetList(customerModel);
        verify(objectValidation, times(1)).validateModel(any(CustomerModel.class), any(String.class));
    }

    @Test
    void testDelete_ValidCustomerModel_ReturnsDeleteCustomerResponse() {
        when(executeCustomer.executeDelete(customerModel)).thenReturn(customerModel);
        doNothing().when(objectValidation).validateModel(customerModel, "customerModel");

        customerService.delete(customerModel);

        verify(executeCustomer, times(1)).executeDelete(customerModel);
        verify(objectValidation, times(1)).validateModel(any(CustomerModel.class), any(String.class));
    }

    @Test
    void testUpdate_ValidCustomerModel_ReturnsUpdateCustomerResponse() {
        when(executeCustomer.executeUpdate(customerModel)).thenReturn(customerModel);
        doNothing().when(objectValidation).validateModel(customerModel, "customerModel");

        customerService.update(customerModel);

        verify(executeCustomer, times(1)).executeUpdate(customerModel);
        verify(objectValidation, times(1)).validateModel(any(CustomerModel.class), any(String.class));
    }

    @Test
    void testCreate_ValidCustomerModel_ReturnsCreateCustomerResponse() {
        when(executeCustomer.executeCreate(customerModel)).thenReturn(customerModel);
        doNothing().when(objectValidation).validateModel(customerModel, "customerModel");

        customerService.create(customerModel);

        verify(executeCustomer, times(1)).executeCreate(customerModel);
        verify(objectValidation, times(1)).validateModel(any(CustomerModel.class), any(String.class));
    }

    @Test
    void testGetTotalRecord_ReturnsTotalRecordCount() {
        when(executeCustomer.executeGetTotalRecord()).thenReturn(10);

        int totalRecord = customerService.getTotalRecord();

        assertEquals(10, totalRecord);
        verify(executeCustomer, times(1)).executeGetTotalRecord();
    }
}
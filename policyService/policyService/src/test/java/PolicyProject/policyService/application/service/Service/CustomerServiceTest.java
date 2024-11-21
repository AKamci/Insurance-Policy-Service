package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;


import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

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
        objectValidation.validateModel(customerModel, "customerModel");
        customerMapper.customerModelToGetCustomerResponse(customerModel);
        executeCustomer.executeGet(customerModel);

        verify(executeCustomer,times(1)).executeGet(customerModel);
        verify(customerMapper,times(1)).customerModelToGetCustomerResponse(customerModel);
        verify(objectValidation, times(1)).validateModel(any(CustomerModel.class), any(String.class));
    }

    @Test
    void testGetList_ValidCustomerModel_ReturnsListGetCustomerResponse() {
        objectValidation.validateModel(customerModel, "customerModel");
        customerMapper.customersModelToGetCustomerResponse(List.of(customerModel, customerModel1));
        executeCustomer.executeGetList(customerModel);

        verify(executeCustomer,times(1)).executeGetList(customerModel);
        verify(objectValidation, times(1)).validateModel(any(CustomerModel.class), any(String.class));
        verify(customerMapper,times(1)).customersModelToGetCustomerResponse(List.of(customerModel, customerModel1));
    }

    @Test
    void testDelete_ValidCustomerModel_ReturnsDeleteCustomerResponse() {
        objectValidation.validateModel(customerModel, "customerModel");
        customerMapper.customerModelToDeleteCustomerResponse(customerModel);
        executeCustomer.executeDelete(customerModel);

        verify(executeCustomer,times(1)).executeDelete(customerModel);
        verify(customerMapper,times(1)).customerModelToDeleteCustomerResponse(customerModel);
        verify(objectValidation, times(1)).validateModel(any(CustomerModel.class), any(String.class));
    }

    @Test
    void testUpdate_ValidCustomerModel_ReturnsUpdateCustomerResponse() {
        objectValidation.validateModel(customerModel, "customerModel");
        customerMapper.customerModelToUpdateCustomerResponse(customerModel);
        executeCustomer.executeUpdate(customerModel);

        verify(executeCustomer,times(1)).executeUpdate(customerModel);
        verify(customerMapper,times(1)).customerModelToUpdateCustomerResponse(customerModel);
        verify(objectValidation, times(1)).validateModel(any(CustomerModel.class), any(String.class));
    }

    @Test
    void testCreate_ValidCustomerModel_ReturnsCreateCustomerResponse() {
        objectValidation.validateModel(customerModel, "customerModel");
        customerMapper.customerModelToCreateCustomerResponse(customerModel);
        executeCustomer.executeCreate(customerModel);

        verify(executeCustomer,times(1)).executeCreate(customerModel);
        verify(customerMapper,times(1)).customerModelToCreateCustomerResponse(customerModel);
        verify(objectValidation, times(1)).validateModel(any(CustomerModel.class), any(String.class));
    }




}
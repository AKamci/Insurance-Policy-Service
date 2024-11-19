package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.IService.ICustomerService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {


    private final ExecuteCustomer executeCustomer;
    private final ObjectValidation objectValidation;

    @Override
    public CreateCustomerResponse create(CustomerModel customerModel) {

        objectValidation.validateModel(customerModel, "customerModel");
        return CustomerMapper.INSTANCE.customerModelToCreateCustomerResponse
                (executeCustomer.executeCreate(customerModel));
    }

    @Override
    public UpdateCustomerResponse update(CustomerModel customerModel) {
        objectValidation.validateModel(customerModel, "customerModel");
        return CustomerMapper.INSTANCE.customerModelToUpdateCustomerResponse
                (executeCustomer.executeUpdate(customerModel));
    }

    @Override
    public DeleteCustomerResponse delete(CustomerModel customerModel) {
        objectValidation.validateModel(customerModel, "customerModel");
        return CustomerMapper.INSTANCE.customerModelToDeleteCustomerResponse
                (executeCustomer.executeDelete(customerModel));
    }

    @Override
    public List<GetCustomerResponse> getList(CustomerModel customerModel) {
        objectValidation.validateModel(customerModel, "customerModel");
        return CustomerMapper.INSTANCE.customersModelToGetCustomerResponse
                (executeCustomer.executeGetList(customerModel));
    }

    @Override
    public GetCustomerResponse get(CustomerModel customerModel) {
        objectValidation.validateModel(customerModel, "customerModel");
        return CustomerMapper.INSTANCE.customerModelToGetCustomerResponse
                (executeCustomer.executeGet(customerModel));
    }
    public int getTotalRecord() {
        return executeCustomer.executeGetTotalRecord();
    }

}
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

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ExecuteCustomer executeCustomer;
    private final ObjectValidation objectValidation;

    @Override
    @Async
    public CompletableFuture<CreateCustomerResponse> create(CustomerModel customerModel) {
        objectValidation.CustomerModelValidations(customerModel);
        CompletableFuture<CustomerModel> createdCustomerModelFuture = executeCustomer.executeCreate(customerModel);
        return createdCustomerModelFuture.thenApply(createdModel ->
                CustomerMapper.INSTANCE.customerModelToCreateCustomerResponse(customerModel));
    }

    @Override
    @Async
    public CompletableFuture<UpdateCustomerResponse> update(CustomerModel customerModel) {
        objectValidation.CustomerModelValidations(customerModel);
        CompletableFuture<CustomerModel> updatedCustomerModelFuture = executeCustomer.executeUpdate(customerModel);
        return updatedCustomerModelFuture.thenApply(updatedModel ->
                CustomerMapper.INSTANCE.customerModelToUpdateCustomerResponse(customerModel));
    }

    @Override
    @Async
    public CompletableFuture<DeleteCustomerResponse> delete(CustomerModel customerModel) {
        objectValidation.CustomerModelValidations(customerModel);
        CompletableFuture<CustomerModel> deletedCustomerModelFuture = executeCustomer.executeDelete(customerModel);
        return deletedCustomerModelFuture.thenApply(deletedModel ->
                CustomerMapper.INSTANCE.customerModelToDeleteCustomerResponse(customerModel));
    }

    @Override
    @Async
    public CompletableFuture<List<GetCustomerResponse>> getList(CustomerModel customerModel) {
        CompletableFuture<List<CustomerModel>> listCustomerModelFuture = executeCustomer.executeGetList(customerModel);
        return listCustomerModelFuture.thenApply(CustomerMapper.INSTANCE::customersModelToGetCustomerResponse);
    }

    @Override
    @Async
    public CompletableFuture<GetCustomerResponse> get(CustomerModel customerModel) {
        objectValidation.CustomerModelValidations(customerModel);
        CompletableFuture<CustomerModel> getCustomerModelFuture = executeCustomer.executeGet(customerModel);
        return getCustomerModelFuture.thenApply(deletedModel ->
                CustomerMapper.INSTANCE.customerModelToGetCustomerResponse(customerModel));
    }

    @Async
    public int getTotalRecord() {

        return executeCustomer.executeGetTotalRecord();
    }


    @Override
    public CreateCustomerResponse create(CompletableFuture<CustomerModel> entity) {
        return null;
    }
}

package PolicyProject.policyService.application.service.Service;

import PolicyProject.policyService.application.service.IService.ICustomerService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


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
        return executeCustomer.executeGetList(customerModel)
                .thenCompose(customerModels -> {
                    List<CompletableFuture<GetCustomerResponse>> futures = customerModels.stream()
                            .map(customerModelItem ->
                                    CompletableFuture.supplyAsync(() ->
                                            CustomerMapper.INSTANCE.customerModelToGetCustomerResponse(customerModelItem)
                                    )
                            )
                            .collect(Collectors.toList());
                    return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                            .thenApply(v -> futures.stream()
                                    .map(CompletableFuture::join)
                                    .collect(Collectors.toList()));
                });
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
    public CompletableFuture<Integer> getTotalRecord() {
        return CompletableFuture.supplyAsync(() -> executeCustomer.executeGetTotalRecord());
    }

}

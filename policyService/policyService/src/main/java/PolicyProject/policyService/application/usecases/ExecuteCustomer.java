package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.exception.DuplicateTcknException;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.CustomerSpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.interfaces.mappers.CarPolicyMapper;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class ExecuteCustomer {


    private final CustomerGateway customerGateway;
    private final CustomerSpecificationBuild customerSpecificationBuild;

    public CompletableFuture<CustomerModel> executeUpdate(CustomerModel customerModel)
    {
        return CompletableFuture.supplyAsync(() ->
                    CustomerMapper.INSTANCE.customerModelToCustomerEntity(customerModel))
                .thenCompose(entity ->  (Optional.ofNullable(customerGateway.update(entity))).orElseThrow(() ->
                        new EntityNotFoundException(customerModel.id(),"Entity not found"))
                        .thenApply(CustomerMapper.INSTANCE::customerEntityToCustomerModel));
    }

    @Async
    public CompletableFuture<CustomerModel> executeCreate(CustomerModel customerModel) {
        return CompletableFuture.supplyAsync(() ->
                        CustomerMapper.INSTANCE.customerModelToCustomerEntity(customerModel))
                .thenCompose(customerGateway::create)
                .thenApply(CustomerMapper.INSTANCE::customerEntityToCustomerModel)
                .exceptionally(e -> {
                    throw new RuntimeException(e);
                });
    }


    public CompletableFuture<CustomerModel> executeGet(CustomerModel customerModel)
    {
        return CompletableFuture.supplyAsync(() ->
                        CustomerMapper.INSTANCE.customerModelToCustomerEntity(customerModel))
                .thenCompose(entity ->  (Optional.ofNullable(customerGateway.get(entity))).orElseThrow(() ->
                                new EntityNotFoundException(customerModel.id(),"Entity not found"))
                        .thenApply(CustomerMapper.INSTANCE::customerEntityToCustomerModel));
    }

    public CompletableFuture<CustomerModel> executeDelete(CustomerModel customerModel)
    {
        return CompletableFuture.supplyAsync(() ->
                        CustomerMapper.INSTANCE.customerModelToCustomerEntity(customerModel))
                .thenCompose(entity ->  (Optional.ofNullable(customerGateway.delete(entity))).orElseThrow(() ->
                                new EntityNotFoundException(customerModel.id(),"Entity not found"))
                        .thenApply(CustomerMapper.INSTANCE::customerEntityToCustomerModel));
    }

    public CompletableFuture<List<CustomerModel>> executeGetList(CustomerModel customerModel)
    {
       int page = customerModel.page();
       int size = customerModel.size();
        return CompletableFuture.supplyAsync(() ->
                        CustomerMapper.INSTANCE.customerModelToCustomerEntity(customerModel))
                .thenApply(customerSpecificationBuild::CustomerBuild)
                .thenCompose((Specification<Customer> specification) -> customerGateway.getList(specification, page, size ))
                .thenApply(CustomerMapper.INSTANCE::CustomerEntityListToCustomerModelList);
    }

    public int executeGetTotalRecord() {
        return customerGateway.getTotal();
    }

}

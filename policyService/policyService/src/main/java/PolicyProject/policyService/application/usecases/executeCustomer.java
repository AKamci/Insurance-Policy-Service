package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.application.gateways.carPolicyGateway;
import PolicyProject.policyService.domain.dto.request.CustomerRequest.GetCustomerRequest;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.exception.CustomerValidationException;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.persistence.entity.Calculator;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class executeCustomer {


    private final CustomerGateway customerGateway ;

    public CustomerModel executeUpdate(CustomerModel CustomerModel)
    {
        Optional<Customer> optionalEntity = Optional.ofNullable
                (customerGateway.update(CustomerMapper.INSTANCE.customerModelToCustomerEntity(CustomerModel)));
        Customer customerEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(CustomerModel.id(),"Entity not found"));
        return CustomerMapper.INSTANCE.customerEntityToCustomerModel(customerEntity);
    }

    public CustomerModel executeCreate(CustomerModel CustomerModel)
    {
        Customer EnityObject = customerGateway.create(CustomerMapper.INSTANCE.customerModelToCustomerEntity(CustomerModel));
        return CustomerMapper.INSTANCE.customerEntityToCustomerModel(EnityObject);
    }

    public CustomerModel executeGet(CustomerModel CustomerModel)
    {
        Optional<Customer> optionalEntity = Optional.ofNullable
                (customerGateway.get(CustomerMapper.INSTANCE.customerModelToCustomerEntity(CustomerModel)));

        Customer customerEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(CustomerModel.id(),"Entity not found"));
        return CustomerMapper.INSTANCE.customerEntityToCustomerModel(customerEntity);
    }

    public CustomerModel executeDelete(CustomerModel CustomerModel)
    {
        Optional<Customer> optionalEntity = Optional.ofNullable
                (customerGateway.delete(CustomerMapper.INSTANCE.customerModelToCustomerEntity(CustomerModel)));
        Customer customerEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(CustomerModel.id(),"Entity not found"));
        return CustomerMapper.INSTANCE.customerEntityToCustomerModel(customerEntity);
    }

    public List<CustomerModel> executeGetList()
    {
        var CustomerList = customerGateway.getList();
        return CustomerMapper.INSTANCE.CustomerEntityListToCustomerModelList(CustomerList);
    }



}

package PolicyProject.policyService.application.usecases;

import PolicyProject.policyService.application.gateways.CustomerGateway;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.exception.EntityNotFoundException;
import PolicyProject.policyService.infrastructure.gateways.SpecificationsBuild.CustomerSpecificationBuild;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ExecuteCustomer {


    private final CustomerGateway customerGateway;
    private final CustomerSpecificationBuild customerSpecificationBuild;

    public CustomerModel executeUpdate(CustomerModel CustomerModel)
    {
        Optional<Customer> optionalEntity = Optional.ofNullable
                (customerGateway.update(CustomerMapper.INSTANCE.customerModelToCustomerEntity(CustomerModel)));
        Customer customerEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(CustomerModel.id(),"Entity not found"));
        return CustomerMapper.INSTANCE.customerEntityToCustomerModel(customerEntity);
    }

    public CustomerModel executeCreate(CustomerModel customerModel)
    {
        Customer EnityObject = customerGateway.create(CustomerMapper.INSTANCE.customerModelToCustomerEntity(customerModel));

        return CustomerMapper.INSTANCE.customerEntityToCustomerModel(EnityObject);
    }

    public CustomerModel executeGet(CustomerModel customerModel)
    {
        Optional<Customer> optionalEntity = Optional.ofNullable
                (customerGateway.get(CustomerMapper.INSTANCE.customerModelToCustomerEntity(customerModel)));

        Customer customerEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(Long.parseLong(customerModel.tckn()),"Entity not found"));
        return CustomerMapper.INSTANCE.customerEntityToCustomerModel(customerEntity);
    }

    public CustomerModel executeDelete(CustomerModel CustomerModel)
    {
        Optional<Customer> optionalEntity = Optional.ofNullable
                (customerGateway.delete(CustomerMapper.INSTANCE.customerModelToCustomerEntity(CustomerModel)));
        Customer customerEntity = optionalEntity.orElseThrow(() -> new EntityNotFoundException(CustomerModel.id(),"Entity not found"));
        return CustomerMapper.INSTANCE.customerEntityToCustomerModel(customerEntity);
    }

    public List<CustomerModel> executeGetList(CustomerModel customerModel)
    {
        Specification<Customer> specification = customerSpecificationBuild.CustomerBuild(CustomerMapper.INSTANCE.customerModelToCustomerEntity(customerModel));

        int page = customerModel.page();
        int size = customerModel.size();

        List<Customer> customerList =
                Optional.ofNullable(customerGateway.getList(specification, page, size))
                        .orElseThrow(() -> new EntityNotFoundException(customerModel.id(), "Entity not found"));

        return CustomerMapper.INSTANCE.CustomerEntityListToCustomerModelList(customerList);
    }

    public int executeGetTotalRecord()
    {
        return customerGateway.getTotal();
    }



}
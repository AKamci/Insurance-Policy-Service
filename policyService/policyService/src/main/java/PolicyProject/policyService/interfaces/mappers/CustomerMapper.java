package PolicyProject.policyService.interfaces.mappers;

import PolicyProject.policyService.domain.dto.request.CustomerRequest.*;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper()
public interface CustomerMapper{

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerModel createCustomerRequestToCustomerModel(CreateCustomerRequest createCustomerRequest);
    CustomerModel updateCustomerRequestToCustomerModel(UpdateCustomerRequest updateCustomerRequest);
    CustomerModel getCustomerRequestToCustomerModel(GetCustomerRequest getCustomerRequest);
    CustomerModel deleteCustomerRequestToCustomerModel(DeleteCustomerRequest deleteCustomerRequest);
    CustomerModel getCustomerListRequestToCustomerModel(GetCustomerListRequest getCustomerListRequest);

    CreateCustomerResponse customerModelToCreateCustomerResponse(CustomerModel customerModel);
    UpdateCustomerResponse customerModelToUpdateCustomerResponse(CustomerModel customerModel);
    GetCustomerResponse customerModelToGetCustomerResponse(CustomerModel customerModel);
    DeleteCustomerResponse customerModelToDeleteCustomerResponse(CustomerModel customerModel);
    List<GetCustomerResponse> customersModelToGetCustomerResponse(List<CustomerModel> customerModel);

    List<CustomerModel> CustomerEntityListToCustomerModelList(List<Customer> customerList);
    Customer customerModelToCustomerEntity(CustomerModel customerModel);
    CustomerModel customerEntityToCustomerModel(Customer customer);
}

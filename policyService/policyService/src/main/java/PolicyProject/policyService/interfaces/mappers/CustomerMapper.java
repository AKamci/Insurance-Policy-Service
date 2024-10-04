package PolicyProject.policyService.interfaces.mappers;

import PolicyProject.policyService.domain.dto.request.CustomerRequest.*;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@org.mapstruct.Mapper(componentModel = "spring")
public interface CustomerMapper extends Mapper{

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerModel createCustomerRequestToCustomerModel(CreateCustomerRequest createCustomerRequest);
    CustomerModel updateCustomerRequestToCustomerModel(UpdateCustomerRequest updateCustomerRequest);
    CustomerModel getCustomerRequestToCustomerModel(GetCustomerRequest getCustomerRequest);
    CustomerModel deleteCustomerRequestToCustomerModel(DeleteCustomerRequest deleteCustomerRequest);

    CreateCustomerResponse customerModelToCreateCustomerResponse(CustomerModel customerModel);
    UpdateCustomerResponse customerModelToUpdateCustomerResponse(CustomerModel customerModel);
    GetCustomerResponse customerModelToGetCustomerResponse(CustomerModel customerModel);
    DeleteCustomerResponse customerModelToDeleteCustomerResponse(CustomerModel customerModel);

    GetCustomerResponse getCustomerResponse(CustomerModel customerModel);

    Customer customerModelToCustomerEntity(CustomerModel customerModel);

    CustomerModel customerEntityToCustomerModel(Optional<Customer> customer);

}

package PolicyProject.policyService.application.service.Service;
import PolicyProject.policyService.application.service.IService.ICustomerService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.executeCustomer;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {


    private final executeCustomer executeCustomer;
    private final ObjectValidation objectValidation;

    @Override
    public CreateCustomerResponse create(CustomerModel CustomerModel) {

        objectValidation.CustomerModelValidations(CustomerModel);
        return CustomerMapper.INSTANCE.customerModelToCreateCustomerResponse
                (executeCustomer.executeCreate(CustomerModel));
    }

    @Override
    public UpdateCustomerResponse update(CustomerModel CustomerModel) {
        objectValidation.CustomerModelValidations(CustomerModel);
        return CustomerMapper.INSTANCE.customerModelToUpdateCustomerResponse
                (executeCustomer.executeUpdate(CustomerModel));
    }

    @Override
    public DeleteCustomerResponse delete(CustomerModel CustomerModel) {
        objectValidation.CustomerModelValidations(CustomerModel);
        return CustomerMapper.INSTANCE.customerModelToDeleteCustomerResponse
                (executeCustomer.executeDelete(CustomerModel));
    }

    /*
        @Override
        public Iterable<GetCustomerResponse> getList() {

            var ResponseList = CustomerMapper.INSTANCE.customersModelToGetCustomerResponse(executeCustomer.executeGetList());

            return ResponseList;
        }
    */
    @Override
    public GetCustomerResponse get(CustomerModel CustomerModel) {
        objectValidation.CustomerModelValidations(CustomerModel);
        return CustomerMapper.INSTANCE.customerModelToGetCustomerResponse
                (executeCustomer.executeGet(CustomerModel));
    }


}

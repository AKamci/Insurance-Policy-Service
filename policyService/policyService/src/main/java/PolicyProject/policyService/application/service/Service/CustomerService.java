package PolicyProject.policyService.application.service.Service;
import PolicyProject.policyService.application.service.IService.ICustomerService;
import PolicyProject.policyService.application.service.ObjectValidation;
import PolicyProject.policyService.application.usecases.ExecuteCustomer;
import PolicyProject.policyService.domain.dto.response.CustomerResponse.*;
import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.interfaces.mappers.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {


    private final ExecuteCustomer executeCustomer;
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

    @Override
    public List<GetCustomerResponse> getList(CustomerModel customerModel) {
        return CustomerMapper.INSTANCE.customersModelToGetCustomerResponse
                (executeCustomer.executeGetList(customerModel));
    }

    @Override
    public GetCustomerResponse get(CustomerModel CustomerModel) {
        objectValidation.CustomerModelValidations(CustomerModel);
        return CustomerMapper.INSTANCE.customerModelToGetCustomerResponse
                (executeCustomer.executeGet(CustomerModel));
    }


    public int getTotalRecord() {
        return executeCustomer.executeGetTotalRecord();
    }

}

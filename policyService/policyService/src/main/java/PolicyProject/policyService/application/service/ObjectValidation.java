package PolicyProject.policyService.application.service;

import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import PolicyProject.policyService.infrastructure.exception.CustomerValidationException;
import PolicyProject.policyService.infrastructure.exception.CarPolicyValidationException;
import org.springframework.stereotype.Service;

@Service
public class ObjectValidation
{

public boolean carPolicyModelValidations(CarPolicyModel carPolicyModel)
{
    if (carPolicyModel.id() == null)
        throw new CarPolicyValidationException(" id is null or empty");
    else if (carPolicyModel.id() == 0)
        throw new CarPolicyValidationException("id is 0");
    else
        return true;
}

public boolean CustomerModelValidations(CustomerModel customerModel)
{
    if (customerModel.tckn() == null)
        throw new CustomerValidationException("id is null or empty");
    else
        return true;
}

}

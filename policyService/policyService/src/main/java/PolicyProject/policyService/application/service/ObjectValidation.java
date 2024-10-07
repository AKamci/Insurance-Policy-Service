package PolicyProject.policyService.application.service;

import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.carPolicyModel;
import PolicyProject.policyService.infrastructure.exception.CustomerValidationException;
import PolicyProject.policyService.infrastructure.exception.carPolicyValidationException;
import org.springframework.stereotype.Service;

@Service
public class ObjectValidation
{

public boolean carPolicyModelValidations(carPolicyModel carPolicyModel)
{
    if (carPolicyModel.id() == null)
        throw new carPolicyValidationException(" id is null or empty");
    else if (carPolicyModel.id() == 0)
        throw new carPolicyValidationException("id is 0");
    else
        return true;
}

public boolean CustomerModelValidations(CustomerModel customerModel)
{
    if (customerModel.id() == null)
        throw new CustomerValidationException("id is null or empty");
    else if (customerModel.id() == 0)
        throw new CustomerValidationException("id is 0");
    else
        return true;
}

}

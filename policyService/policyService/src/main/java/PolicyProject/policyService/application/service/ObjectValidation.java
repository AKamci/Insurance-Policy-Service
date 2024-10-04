package PolicyProject.policyService.application.service;

import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.carPolicyModel;
import org.springframework.stereotype.Service;

@Service
public class ObjectValidation
{

public boolean carPolicyModelValidations(carPolicyModel carPolicyModel)
{
    if (carPolicyModel.id() == null)
        return false;
    if (carPolicyModel.id() == 0)
        return false;

    return true;
}

public boolean CustomerModelValidations(CustomerModel customerModel)
{
    return false;
}

}

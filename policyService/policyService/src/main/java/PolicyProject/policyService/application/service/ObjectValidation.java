package PolicyProject.policyService.application.service;

import PolicyProject.policyService.domain.model.CustomerModel;
import PolicyProject.policyService.domain.model.CarPolicyModel;
import org.springframework.stereotype.Service;

@Service
public class ObjectValidation {

    public <T> void validateModel(T model, String modelName) {
        if (model == null) {
            throw new IllegalArgumentException(modelName + " is null");
        }
    }
}


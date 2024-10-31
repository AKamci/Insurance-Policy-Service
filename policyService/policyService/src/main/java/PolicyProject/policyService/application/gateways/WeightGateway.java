package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.Weights;

public interface WeightGateway {

    Weights getWeights(String key);
}

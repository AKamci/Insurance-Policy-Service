package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.Weights;

public interface WeightGateway {

    Weights get(String key);
    Weights create(Weights weights);
    Weights update(Weights weights);
    void delete(String key);
    Iterable<Weights> list();

}

package PolicyProject.policyService.application.gateways.WeightsGateway;

import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;

import java.util.List;

public interface CarPolicyWeightGateway {

    Weights get(String key);
    Weights create(Weights weights);
    Weights update(Weights weights);
    Weights delete(String key);
    List<Weights> list();
    List<Weights> listFilter();
    List<Weights> updateOrSave(List<Weights> weights);

}

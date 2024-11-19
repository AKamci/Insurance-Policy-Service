package PolicyProject.policyService.application.gateways.WeightsGateway;

import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;

import java.util.List;

public interface HealthPolicyWeightGateway {
    HealthPolicyWeight get(String key);
    HealthPolicyWeight create(HealthPolicyWeight weights);
    HealthPolicyWeight update(HealthPolicyWeight weights);
    HealthPolicyWeight delete(String key);
    List<HealthPolicyWeight> list();
    List<HealthPolicyWeight> listFilter();
    List<HealthPolicyWeight> updateOrSave(List<HealthPolicyWeight> weights);
}


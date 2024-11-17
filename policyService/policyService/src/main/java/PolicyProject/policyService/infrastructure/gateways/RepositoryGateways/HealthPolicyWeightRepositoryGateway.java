package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.HealthPolicyWeightGateway;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.EarthQuakeWeightsRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository.HealthPolicyWeightRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class HealthPolicyWeightRepositoryGateway implements HealthPolicyWeightGateway {

    private final HealthPolicyWeightRepository healthPolicyWeightRepository;

    @Override
    public HealthPolicyWeight get(String key) {
        return null;
    }

    @Override
    public HealthPolicyWeight create(HealthPolicyWeight weights) {
        return null;
    }

    @Override
    public HealthPolicyWeight update(HealthPolicyWeight weights) {
        return null;
    }

    @Override
    public HealthPolicyWeight delete(String key) {
        return null;
    }

    @Override
    public List<HealthPolicyWeight> list() {
        return List.of();
    }

    @Override
    public List<HealthPolicyWeight> listFilter() {
        return List.of();
    }

    @Override
    public List<HealthPolicyWeight> updateOrSave(List<HealthPolicyWeight> weights) {
        return List.of();
    }
}

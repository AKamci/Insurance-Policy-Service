package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;


import PolicyProject.policyService.application.gateways.WeightGateway;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WeightRepositoryGateway implements WeightGateway {

    WeightsRepository weightsRepository;

    @Override
    public Weights getWeights(String key) {
        return null;
    }
}

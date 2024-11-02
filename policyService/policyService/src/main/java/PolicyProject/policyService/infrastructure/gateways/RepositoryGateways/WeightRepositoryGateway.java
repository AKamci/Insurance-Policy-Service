package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;


import PolicyProject.policyService.application.gateways.WeightGateway;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;
import PolicyProject.policyService.infrastructure.persistence.repository.WeightsRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WeightRepositoryGateway implements WeightGateway {

   private final WeightsRepository weightsRepository;

    @Override
    public Weights get(String key) {
        return weightsRepository.findByKey(key);
    }

    @Override
    public Weights create(Weights weights) {
        return weightsRepository.save(weights);
    }

    @Override
    public Weights update(Weights weights) {
        return weightsRepository.save(weights);
    }

    @Override
    public void delete(String key) {
        Weights weights = get(key);
        weightsRepository.delete(weights);
    }

    @Override
    public Iterable<Weights> list() {
        return weightsRepository.findAll();
    }
}

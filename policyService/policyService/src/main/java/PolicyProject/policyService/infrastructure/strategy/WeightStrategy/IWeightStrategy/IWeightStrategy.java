package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy;

import PolicyProject.policyService.infrastructure.persistence.entity.Weights;

public interface IWeightStrategy {

    Weights getWeights(String key);
}

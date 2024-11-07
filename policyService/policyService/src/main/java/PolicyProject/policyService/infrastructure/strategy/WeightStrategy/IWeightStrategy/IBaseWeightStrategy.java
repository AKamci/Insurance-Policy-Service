package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy;

import PolicyProject.policyService.infrastructure.persistence.entity.Weights;

import java.math.BigDecimal;

public interface IBaseWeightStrategy {
    BigDecimal calculate(Weights parameter);
}
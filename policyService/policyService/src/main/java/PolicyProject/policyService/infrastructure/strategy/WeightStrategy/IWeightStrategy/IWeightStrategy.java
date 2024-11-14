package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy;

import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;

import java.math.BigDecimal;

public interface IWeightStrategy<T, U> {
    BigDecimal calculate(T model, U parameter);
    BigDecimal getValue(T model);
}
package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy;

import java.math.BigDecimal;

public interface IWeightStrategy<T, U> {
    BigDecimal calculate(T model, U parameter);
    BigDecimal getValue(T model);
}
package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.ConstantStrategy;

import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;

import java.math.BigDecimal;

public class ConstantStrategy implements IWeightStrategy {
    @Override
    public BigDecimal calculate(LicensePlateModel model, Weights parameter) {
        return parameter.getWeight().multiply(getValue(model));
    }

    @Override
    public BigDecimal getValue(LicensePlateModel model) {
        return BigDecimal.valueOf(1);
    }
}

package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.PolicyTypeStrategy;

import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;

import java.math.BigDecimal;

public class PolicyTypeStrategy   implements IWeightStrategy {
    @Override
    public BigDecimal calculate(LicensePlateModel model, Weights parameter) {
        return parameter.getWeight();
    }

    @Override
    public BigDecimal getValue(LicensePlateModel model) {
        return BigDecimal.valueOf(model.policyType());
    }
}

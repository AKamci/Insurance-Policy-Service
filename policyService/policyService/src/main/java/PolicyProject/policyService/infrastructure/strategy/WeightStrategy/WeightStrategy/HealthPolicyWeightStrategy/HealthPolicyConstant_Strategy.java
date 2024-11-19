package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IHealthPolicyWeightStrategy;

import java.math.BigDecimal;

public class HealthPolicyConstant_Strategy implements IHealthPolicyWeightStrategy {
    @Override
    public BigDecimal calculate(PersonalHealthModel model, HealthPolicyWeight parameter) {
        return parameter.getWeight().multiply(getValue(model));
    }

    public BigDecimal getValue(PersonalHealthModel model) {
        return BigDecimal.valueOf(1);
    }

}
package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.CarPolicy.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.ICarPolicyWeightStrategy;

import java.math.BigDecimal;

public class PolicyTypeStrategy implements ICarPolicyWeightStrategy {
    @Override
    public BigDecimal calculate(LicensePlateModel model, Weights parameter) {
        return parameter.getWeight();
    }

    @Override
    public BigDecimal getValue(LicensePlateModel model) {
        return BigDecimal.valueOf(model.coverageCode());
    }
}

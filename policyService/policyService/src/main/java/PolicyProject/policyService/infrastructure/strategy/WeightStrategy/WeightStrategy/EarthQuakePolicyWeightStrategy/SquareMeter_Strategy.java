package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy;

import PolicyProject.policyService.domain.model.HouseModel;
import PolicyProject.policyService.infrastructure.persistence.entity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.persistence.entity.House;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IEarthQuakeWeightStrategy;

import java.math.BigDecimal;

public class SquareMeter_Strategy implements IEarthQuakeWeightStrategy {
    @Override
    public BigDecimal calculate(HouseModel model, EarthQaukeWeights parameter) {
        return parameter.getWeight().multiply(getValue(model));
    }

    @Override
    public BigDecimal getValue(HouseModel model) {
        return BigDecimal.valueOf(model.squareMeters());
    }
}

package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy;

import PolicyProject.policyService.domain.model.AuxiliaryModel.EarthquakePolicy.HouseModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IEarthQuakeWeightStrategy;

import java.math.BigDecimal;

public class EarthQuakeRisk_Strategy implements IEarthQuakeWeightStrategy {
    @Override
    public BigDecimal calculate(HouseModel model, EarthQaukeWeights parameter) {
        if (model == null || parameter == null || parameter.getWeight() == null) {
            return BigDecimal.ZERO;
        }
        return parameter.getWeight().multiply(getValue(model));
    }

    @Override
    public BigDecimal getValue(HouseModel model) {
        return BigDecimal.valueOf(1);
    }
}

package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy;

import PolicyProject.policyService.domain.model.HouseModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IEarthQuakeWeightStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class ConstructionYear_Strategy implements IEarthQuakeWeightStrategy {
    @Override
    public BigDecimal calculate(HouseModel model, EarthQaukeWeights parameter) {
        return parameter.getWeight().multiply(getValue(model));
    }

    @Override
    public BigDecimal getValue(HouseModel model) {
        Integer year = model.building().getConstructionYear();
        LocalDate currentDate = LocalDate.now();

        if (year != null) {
            LocalDate birthDate = LocalDate.of(year, 1, 1);
            int age = Period.between(birthDate, currentDate).getYears();
            return BigDecimal.valueOf(age);
        } else {
            return BigDecimal.ZERO;
        }
    }
    }


package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.HealthPolicyBooleanStrategyType;
import PolicyProject.policyService.domain.model.AuxiliaryModel.HealthPolicy.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IHealthPolicyWeightStrategy;

import java.math.BigDecimal;

public class HealtPolicyBoolen_Strategy implements IHealthPolicyWeightStrategy {

    @Override
    public BigDecimal getValue(PersonalHealthModel model) {
        return BigDecimal.ZERO;
    }


    @Override
    public BigDecimal calculate(PersonalHealthModel model, HealthPolicyWeight parameter) {
        String key = parameter.getKey();

        switch (key) {
            case "ALCOHOL":
                return getAlcoholConsumption(model, parameter);
            case "SMOKE":
                return getSmokeConsumption(model, parameter);
            case "PREGNANT":
                return getIsPregnant(model, parameter);
            case "DISABILITY":
                return getHasDisability(model, parameter);
            case "SURGERIES":
                return getHasPreviousSurgeries(model, parameter);
            default:
                return BigDecimal.ZERO;
        }

    }

    private BigDecimal getAlcoholConsumption(PersonalHealthModel model, HealthPolicyWeight parameter) {
        int intValue = model.alcoholConsumption() ? 1 : 0;
        return parameter.getWeight().multiply(BigDecimal.valueOf(intValue));
    }

    public BigDecimal getSmokeConsumption(PersonalHealthModel model, HealthPolicyWeight parameter) {
        int intValue = model.smokeConsumption() ? 1 : 0;
        return parameter.getWeight().multiply(BigDecimal.valueOf(intValue));
    }
    public BigDecimal getIsPregnant(PersonalHealthModel model, HealthPolicyWeight parameter) {
        int intValue = model.isPregnant() ? 1 : 0;
        return parameter.getWeight().multiply(BigDecimal.valueOf(intValue));
    }

    public BigDecimal getHasDisability(PersonalHealthModel model, HealthPolicyWeight parameter) {
        int intValue = model.hasDisability() ? 1 : 0;
        return parameter.getWeight().multiply(BigDecimal.valueOf(intValue));
    }
    public BigDecimal getHasPreviousSurgeries(PersonalHealthModel model, HealthPolicyWeight parameter) {
        int intValue = model.hasPreviousSurgeries() ? 1 : 0;
        return parameter.getWeight().multiply(BigDecimal.valueOf(intValue));
    }

}



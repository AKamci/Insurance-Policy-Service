package PolicyProject.policyService.domain.Enums.IEnums;

import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;

public interface IWeightsEnum {

    IWeightStrategy getWeightStrategy();

}

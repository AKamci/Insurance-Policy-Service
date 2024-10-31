package PolicyProject.policyService.application.service.StrategyFactory;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;

public class WeightStrategyFactory {

    public static IWeightStrategy getWeightStrategy(IWeightsEnum weightsEnum) {
        return weightsEnum.getWeightStrategy();
    }
}

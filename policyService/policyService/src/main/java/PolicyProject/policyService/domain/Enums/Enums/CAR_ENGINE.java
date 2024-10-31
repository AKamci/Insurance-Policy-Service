package PolicyProject.policyService.domain.Enums.Enums;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EngineClassStrategy.*;

public enum CAR_ENGINE implements IWeightsEnum
{
    ENGINE_0_1300(new Engine_0_1300_Strategy()),
    ENGINE_1301_1600(new Engine_1301_1600_Strategy()),
    ENGINE_1601_1800(new Engine_1601_1800_Strategy()),
    ENGINE_1801_2000(new Engine_1801_2000_Strategy()),
    ENGINE_2001_2500(new Engine_2001_2500_Strategy()),
    ENGINE_2501_3000(new Engine_2501_3000_Strategy()),
    ENGINE_3001_4000(new Engine_3001_4000_Strategy()),
    ENGINE_4001_UP(new Engine_4001_UP_Strategy()),;

    private final IWeightStrategy weightStrategy;
    CAR_ENGINE(IWeightStrategy strategy)
    {
        this.weightStrategy = strategy;
    }

    public IWeightStrategy getWeightStrategy()
    {
        return weightStrategy;
    }

};

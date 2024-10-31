package PolicyProject.policyService.domain.Enums.Enums;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.ConstantStrategy.Base_Strategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.ConstantStrategy.Euro_Strategy;

public enum CONSTANT implements IWeightsEnum
{
    BASE(new Base_Strategy()),
    EURO(new Euro_Strategy()),;


    private final IWeightStrategy weightStrategy;
    CONSTANT(IWeightStrategy strategy)
    {
        this.weightStrategy = strategy;
    }

    public IWeightStrategy getWeightStrategy()
    {
        return weightStrategy;
    }
};
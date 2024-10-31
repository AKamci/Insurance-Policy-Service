package PolicyProject.policyService.domain.Enums.Enums;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.GenderStrategy.Gender_Female_Strategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.GenderStrategy.Gender_Male_Strategy;

public enum GENDER implements IWeightsEnum
{
    MALE(new Gender_Male_Strategy()),
    FEMALE(new Gender_Female_Strategy()),;

    private final IWeightStrategy weightStrategy;
    GENDER(IWeightStrategy strategy)
    {
        this.weightStrategy = strategy;
    }

    public IWeightStrategy getWeightStrategy()
    {
        return weightStrategy;
    }
};

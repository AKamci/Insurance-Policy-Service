package PolicyProject.policyService.domain.Enums.Enums;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.PolicyTypeStrategy.Kasko_Strategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.PolicyTypeStrategy.Trafik_Strategy;

public enum POLICY_TYPE implements IWeightsEnum
{
    KASKO(new Kasko_Strategy()),
    TRAFİK(new Trafik_Strategy());

    private final IWeightStrategy weightStrategy;
    POLICY_TYPE(IWeightStrategy strategy)
    {
        this.weightStrategy = strategy;
    }

    public IWeightStrategy getWeightStrategy()
    {
        return weightStrategy;
    }
};
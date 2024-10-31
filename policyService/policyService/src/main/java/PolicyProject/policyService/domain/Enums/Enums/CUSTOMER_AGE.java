package PolicyProject.policyService.domain.Enums.Enums;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CustomerAgeStrategy.CustomerAge_18_25_Strategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CustomerAgeStrategy.Customer_Age_25_65_Strategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CustomerAgeStrategy.Customer_Age_65_Up_Strategy;

public enum CUSTOMER_AGE implements IWeightsEnum
{
    AGE_18_25(new CustomerAge_18_25_Strategy()),
    AGE_25_65(new Customer_Age_25_65_Strategy()),
    AGE_65_UP(new Customer_Age_65_Up_Strategy()),;

    private final IWeightStrategy weightStrategy;
    CUSTOMER_AGE(IWeightStrategy strategy)
    {
        this.weightStrategy = strategy;
    }

    public IWeightStrategy getWeightStrategy()
    {
        return weightStrategy;
    }
};


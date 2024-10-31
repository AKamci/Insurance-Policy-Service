package PolicyProject.policyService.domain.Enums.Enums;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarAgeStrategy.*;

public enum CAR_AGE implements IWeightsEnum
{
    CAR_AGE_0_1(new CarAge_0_1_Strategy()),
    CAR_AGE_2_5(new CarAge_2_5_Strategy()),
    CAR_AGE_6_10(new CarAge_6_10_Strategy()),
    CAR_AGE_11_15(new CarAge_11_15_Strategy()),
    CAR_AGE_16_20(new CarAge_16_20_Strategy()),
    CAR_AGE_20_UP(new CarAge_20_Up_Strategy()),;


    private final IWeightStrategy weightStrategy;
    CAR_AGE(IWeightStrategy strategy)
    {
        this.weightStrategy = strategy;
    }

    public IWeightStrategy getWeightStrategy()
    {
        return weightStrategy;
    }
};

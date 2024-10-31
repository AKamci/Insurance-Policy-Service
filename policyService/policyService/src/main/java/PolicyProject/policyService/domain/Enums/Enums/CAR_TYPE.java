package PolicyProject.policyService.domain.Enums.Enums;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarTypeStrategy.*;

public enum CAR_TYPE implements IWeightsEnum
{
    CAR_M1_CLASS(new Car_M1_Strategy()),
    CAR_M2_CLASS(new Car_M2_Strategy()),
    CAR_M3_CLASS(new Car_M3_Strategy()),

    CAR_N1_CLASS(new Car_N1_Strategy()),
    CAR_N2_CLASS(new Car_N2_Strategy()),
    CAR_N3_CLASS(new Car_N3_Strategy()),

    CAR_O1_CLASS(new Car_O1_Strategy()),
    CAR_O2_CLASS(new Car_O2_Strategy()),
    CAR_O3_CLASS(new Car_O3_Strategy()),
    CAR_O4_CLASS(new Car_O4_Strategy());


    private final IWeightStrategy weightStrategy;
    CAR_TYPE(IWeightStrategy strategy)
    {
        this.weightStrategy = strategy;
    }

    public IWeightStrategy getWeightStrategy()
    {
        return weightStrategy;
    }
};
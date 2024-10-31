package PolicyProject.policyService.domain.Enums.Enums;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPriceStrategy.*;

public enum CAR_PRICE implements IWeightsEnum
{
    PRICE_0_500000(new CarPrice_0_500K_Strategy()),
    PRICE_500001_1000000(new CarPrice_500K_1M_Strategy()),
    PRICE_1000001_2000000(new CarPrice_1M_2M_Strategy()),
    PRICE_2000001_3000000(new CarPrice_2M_3M_Strategy()),
    PRICE_3000001_4000000(new CarPrice_3M_4M_Strategy()),
    PRICE_4000001_UP(new CarPrice_4M_Up_Strategy());

    private final IWeightStrategy weightStrategy;
    CAR_PRICE(IWeightStrategy strategy)
    {
        this.weightStrategy = strategy;
    }

    public IWeightStrategy getWeightStrategy()
    {
        return weightStrategy;
    }
};

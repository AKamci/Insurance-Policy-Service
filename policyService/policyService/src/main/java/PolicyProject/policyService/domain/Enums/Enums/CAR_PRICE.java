package PolicyProject.policyService.domain.Enums.Enums;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPriceStrategy.*;

public enum CAR_PRICE implements IWeightsEnum
{
    PRICE_0_500000(new CarPrice_0_500K_Strategy(), 0,500000 ),
    PRICE_500001_1000000(new CarPrice_500K_1M_Strategy(), 500_001, 1_000_000),
    PRICE_1000001_2000000(new CarPrice_1M_2M_Strategy(), 1_000_001, 2_000_000),
    PRICE_2000001_3000000(new CarPrice_2M_3M_Strategy(), 2_000_001, 3_000_000),
    PRICE_3000001_4000000(new CarPrice_3M_4M_Strategy(), 3_000_001, 4_000_000),
    PRICE_4000001_UP(new CarPrice_4M_Up_Strategy(), 4_000_001, Integer.MAX_VALUE),;

    private final IWeightStrategy weightStrategy;
    private final int startValue;
    CAR_PRICE(IWeightStrategy strategy, int start, int end)
    {
        this.weightStrategy = strategy;
        this.startValue = start;

    }

    public IWeightStrategy getWeightStrategy()
    {
        return weightStrategy;
    }

    public int getStartValue()
    {
        return startValue;
    }
};

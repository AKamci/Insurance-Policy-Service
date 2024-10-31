package PolicyProject.policyService.domain.Enums.Enums;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CustomerGradeStrategy.*;

public enum CUSTOMER_GRADE implements IWeightsEnum
{
    GRADE_1(new Customer_Grade1_Strategy()),
    GRADE_2(new Customer_Grade2_Strategy()),
    GRADE_3(new Customer_Grade3_Strategy()),
    GRADE_4(new Customer_Grade4_Strategy()),
    GRADE_5(new Customer_Grade5_Strategy()),
    GRADE_6(new Customer_Grade6_Strategy()),
    GRADE_7(new Customer_Grade7_Strategy()),
    GRADE_8(new Customer_Grade8_Strategy()),;

    private final IWeightStrategy weightStrategy;
    CUSTOMER_GRADE(IWeightStrategy strategy)
    {
        this.weightStrategy = strategy;
    }

    public IWeightStrategy getWeightStrategy()
    {
        return weightStrategy;
    }
};

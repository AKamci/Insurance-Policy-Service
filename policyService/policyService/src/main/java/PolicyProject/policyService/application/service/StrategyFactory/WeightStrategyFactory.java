package PolicyProject.policyService.application.service.StrategyFactory;

import PolicyProject.policyService.domain.Enums.Enums.StrategyType;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.CarAgeStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.CarPriceStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.CarTypeStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.ConstantStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.CustomerAgeStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.CustomerGradeStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.EngineClassStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.GenderStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.PolicyTypeStrategy;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeightStrategyFactory {

    private final Map<String, IWeightStrategy> strategies = new HashMap<>();

    public WeightStrategyFactory() {
        strategies.put(StrategyType.CUSTOMER_AGE.name(), new CustomerAgeStrategy());
        strategies.put(StrategyType.CAR_AGE.name(), new CarAgeStrategy());
        strategies.put(StrategyType.CAR_PRICE.name(), new CarPriceStrategy());

        strategies.put(StrategyType.CAR_TYPE.name(), new CarTypeStrategy());
        strategies.put(StrategyType.CONSTANT.name(), new ConstantStrategy());
        strategies.put(StrategyType.CUSTOMER_GRADE.name(), new CustomerGradeStrategy());

        strategies.put(StrategyType.ENGINE.name(), new EngineClassStrategy());
        strategies.put(StrategyType.GENDER.name(), new GenderStrategy());
        strategies.put(StrategyType.POLICY_TYPE.name(), new PolicyTypeStrategy());
    }

    public IWeightStrategy getStrategy(String type) {
        return strategies.getOrDefault(type, null);
    }
}

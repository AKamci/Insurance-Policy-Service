package PolicyProject.policyService.application.service.StrategyFactory;

import PolicyProject.policyService.domain.Enums.Enums.CarPolicyEnum.CarPolicyStrategyType;
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
public class CarPolicyWeightStrategyFactory {

    private final Map<String, IWeightStrategy> strategies = new HashMap<>();

    public CarPolicyWeightStrategyFactory() {
        strategies.put(CarPolicyStrategyType.CUSTOMER_AGE.name(), new CustomerAgeStrategy());
        strategies.put(CarPolicyStrategyType.CAR_AGE.name(), new CarAgeStrategy());
        strategies.put(CarPolicyStrategyType.CAR_PRICE.name(), new CarPriceStrategy());

        strategies.put(CarPolicyStrategyType.CAR_TYPE.name(), new CarTypeStrategy());
        strategies.put(CarPolicyStrategyType.CONSTANT.name(), new ConstantStrategy());
        strategies.put(CarPolicyStrategyType.CUSTOMER_GRADE.name(), new CustomerGradeStrategy());

        strategies.put(CarPolicyStrategyType.ENGINE.name(), new EngineClassStrategy());
        strategies.put(CarPolicyStrategyType.GENDER.name(), new GenderStrategy());
        strategies.put(CarPolicyStrategyType.POLICY_TYPE.name(), new PolicyTypeStrategy());
    }

    public IWeightStrategy getStrategy(String type) {
        return strategies.getOrDefault(type, null);
    }
}

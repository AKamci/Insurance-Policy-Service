package PolicyProject.policyService.application.service.StrategyFactory;

import PolicyProject.policyService.domain.Enums.IEnums.IWeightsEnum;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CustomerAgeStrategy.CustomerAgeStrategy;

import java.util.HashMap;
import java.util.Map;

public class WeightStrategyFactory {

    private final Map<String, IWeightStrategy> strategies = new HashMap<>();

    public WeightStrategyFactory() {
        strategies.put("AGE", new CustomerAgeStrategy());
        //strategies.put("CAR_AGE", new CarAgePricingStrategy());
        // Diğer stratejiler buraya eklenebilir
    }

    public IWeightStrategy getStrategy(String type) {
        return strategies.getOrDefault(type, null);
    }
}

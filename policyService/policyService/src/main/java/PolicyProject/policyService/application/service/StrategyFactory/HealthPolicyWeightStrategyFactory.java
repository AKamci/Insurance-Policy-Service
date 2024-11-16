package PolicyProject.policyService.application.service.StrategyFactory;

import PolicyProject.policyService.domain.Enums.Enums.EarthquakePolicyStrategyType;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HealthPolicyWeightStrategyFactory {
    private final Map<String, IWeightStrategy> strategies = new HashMap<>();

    public HealthPolicyWeightStrategyFactory() {


    }

    public IWeightStrategy getStrategy(String type) {
        return strategies.getOrDefault(type, null);
    }
}

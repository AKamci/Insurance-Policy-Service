package PolicyProject.policyService.application.service.StrategyFactory;

import PolicyProject.policyService.domain.Enums.Enums.EarthquakePolicyStrategyType;
import PolicyProject.policyService.domain.Enums.Enums.StrategyType;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy.CustomerAgeStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.EarthQuakePolicyWeightStrategy.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EarthQuakeWeightStrategyFactory {

    private final Map<String, IWeightStrategy> strategies = new HashMap<>();

    public EarthQuakeWeightStrategyFactory() {

        strategies.put(EarthquakePolicyStrategyType.SQUARE_METERS.name(), new SquareMeter_Strategy());
        strategies.put(EarthquakePolicyStrategyType.EARTHQUAKERISK.name(), new EarthQuakeRisk_Strategy());
        strategies.put(EarthquakePolicyStrategyType.CONSTRUCTION_STYLE.name(), new ConstructionStyle_Strategy());
        strategies.put(EarthquakePolicyStrategyType.TOTAL_FLOORS.name(), new TotalFloors_Strategy());
        strategies.put(EarthquakePolicyStrategyType.CONSTRUCTION_YEAR.name(), new ConstructionYear_Strategy());
    }

    public IWeightStrategy getStrategy(String type) {
        return strategies.getOrDefault(type, null);
    }
}

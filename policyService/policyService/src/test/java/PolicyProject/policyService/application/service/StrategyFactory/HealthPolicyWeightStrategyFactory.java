package PolicyProject.policyService.application.service.StrategyFactory;

import PolicyProject.policyService.domain.Enums.Enums.HealthPolicyEnum.HealthPolicyStrategyType;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HealthPolicyWeightStrategyFactory {
    private final Map<String, IWeightStrategy> strategies = new HashMap<>();

    public HealthPolicyWeightStrategyFactory() {
        strategies.put(HealthPolicyStrategyType.AGE.name(), new HealthPolicyCustomerAge_Strategy());
        strategies.put(HealthPolicyStrategyType.WEIGHT.name(), new HealthPolicyCustomerWeight_Strategy());
        strategies.put(HealthPolicyStrategyType.HEIGHT.name(), new HealthPolicyCustomerHeight_Strategy());
        strategies.put(HealthPolicyStrategyType.BMI.name(), new HealthPolicyCustomerBMI_Strategy());
        strategies.put(HealthPolicyStrategyType.BLOODTYPE.name(), new HealthPolicyCustomerBloodType_Strategy());
        strategies.put(HealthPolicyStrategyType.GENDER.name(), new HealthPolicyCustomerGender_Strategy());
        strategies.put(HealthPolicyStrategyType.POLICY_TYPE.name(), new HealthPolicyType_Strategy());
        strategies.put(HealthPolicyStrategyType.CONSTANT.name(), new HealthPolicyConstant_Strategy());
        strategies.put(HealthPolicyStrategyType.BOOLEAN_TYPE.name(), new HealtPolicyBoolen_Strategy());


    }

    public IWeightStrategy getStrategy(String type) {
        return strategies.getOrDefault(type, null);
    }
}

package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.HealthPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.PersonalHealthModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.HealthPolicyWeight;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IHealthPolicyWeightStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class HealthPolicyCustomerAge_Strategy implements IHealthPolicyWeightStrategy {
    @Override
    public BigDecimal calculate(PersonalHealthModel model, HealthPolicyWeight parameter) {
        return parameter.getWeight().multiply(getValue(model));
    }

    @Override
    public BigDecimal getValue(PersonalHealthModel model) {
        LocalDate birthDate = model.customer().getBirthDay();
        LocalDate currentDate = LocalDate.now();

        if (birthDate != null) {
            int age = Period.between(birthDate, currentDate).getYears();
            return BigDecimal.valueOf(age);
        } else {
            return BigDecimal.ZERO;
        }
    }
}

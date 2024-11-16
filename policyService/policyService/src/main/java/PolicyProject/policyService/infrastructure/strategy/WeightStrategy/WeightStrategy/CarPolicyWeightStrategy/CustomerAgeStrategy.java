package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarPolicyWeightStrategy;

import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.ICarPolicyWeightStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class CustomerAgeStrategy  implements ICarPolicyWeightStrategy {
    @Override
    public BigDecimal calculate(LicensePlateModel model, Weights parameter) {
        return parameter.getWeight().multiply(getValue(model));
    }

    @Override
    public BigDecimal getValue(LicensePlateModel model) {

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

package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.WeightStrategy.CarAgeStrategy;

import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;
import PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy.IWeightStrategy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class CarAgeStrategy   implements IWeightStrategy {
    @Override
    public BigDecimal calculate(LicensePlateModel model, Weights parameter) {
        return parameter.getWeight().multiply(getValue(model));
    }

    @Override
    public BigDecimal getValue(LicensePlateModel model) {
        Integer birthYear = model.car().getYear();
        LocalDate currentDate = LocalDate.now();

        if (birthYear != null) {
            LocalDate birthDate = LocalDate.of(birthYear, 1, 1);
            int age = Period.between(birthDate, currentDate).getYears();
            return BigDecimal.valueOf(age);
        } else {
            return BigDecimal.ZERO;
        }
    }
}


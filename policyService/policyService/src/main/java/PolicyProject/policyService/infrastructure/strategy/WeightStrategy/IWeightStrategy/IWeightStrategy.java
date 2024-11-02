package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy;

import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;

import java.math.BigDecimal;

public interface IWeightStrategy {

    BigDecimal calculate(LicensePlateModel model, Weights weights);
    BigDecimal getValue(LicensePlateModel model);
}

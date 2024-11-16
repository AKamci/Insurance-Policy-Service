package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy;

import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.WeightsEntity.Weights;

public interface ICarPolicyWeightStrategy extends IWeightStrategy<LicensePlateModel, Weights> {
}
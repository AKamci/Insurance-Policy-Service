package PolicyProject.policyService.infrastructure.strategy.WeightStrategy.IWeightStrategy;

import PolicyProject.policyService.domain.model.HouseModel;
import PolicyProject.policyService.domain.model.LicensePlateModel;
import PolicyProject.policyService.infrastructure.persistence.entity.EarthQaukeWeights;
import PolicyProject.policyService.infrastructure.persistence.entity.House;
import PolicyProject.policyService.infrastructure.persistence.entity.Weights;

public interface IEarthQuakeWeightStrategy extends IWeightStrategy<HouseModel, EarthQaukeWeights>{
}

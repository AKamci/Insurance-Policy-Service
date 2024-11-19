package PolicyProject.policyService.application.gateways.AuxiliaryGateway.EarthquakePolicy;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;

public interface HouseGateway {
    House getWCustomer(House house);
    House get(House house);
}

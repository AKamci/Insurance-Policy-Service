package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.House;
import PolicyProject.policyService.infrastructure.persistence.entity.LicensePlate;

public interface HouseGateway {
    House getWCustomer(House House);
    House get(String plate);
}

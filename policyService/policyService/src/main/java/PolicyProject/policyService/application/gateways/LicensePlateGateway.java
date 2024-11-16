package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;

public interface LicensePlateGateway {

    LicensePlate getWCustomer(LicensePlate licensePlate);
    LicensePlate get(String plate);

}

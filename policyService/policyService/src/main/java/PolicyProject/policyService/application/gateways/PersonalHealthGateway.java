package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;

public interface PersonalHealthGateway {
    PersonalHealth getWCustomer(PersonalHealth personalHealth);
    PersonalHealth get(String tckn);
}

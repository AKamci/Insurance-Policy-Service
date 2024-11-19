package PolicyProject.policyService.application.gateways.AuxiliaryGateway.HealthPolicy;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;

public interface PersonalHealthGateway {
    PersonalHealth getWCustomer(PersonalHealth personalHealth);
    PersonalHealth get(PersonalHealth personalHealth);
    PersonalHealth create(PersonalHealth personalHealth, Customer customer);
}

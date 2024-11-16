package PolicyProject.policyService.application.gateways;

import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public interface HealthPolicyGateway {

    HealthPolicy create(HealthPolicy healthPolicy, Customer customer, PersonalHealth personalHealth);
    HealthPolicy get(HealthPolicy healthPolicy);
    HealthPolicy update(HealthPolicy healthPolicy);
    HealthPolicy delete(HealthPolicy healthPolicy);
    List<HealthPolicy> getList(Specification<HealthPolicy> healthPolicy, int page, int size);
    List<HealthPolicy> getCarPoliciesByCustomer(String tckn);
    List<HealthPolicy> findByStateAndExpiryDateBefore(PolicyState state, LocalDate currentDate);
    HealthPolicy SetStateCarPolicy(HealthPolicy healthPolicy, PolicyState policyState);
    int getTotal();

}

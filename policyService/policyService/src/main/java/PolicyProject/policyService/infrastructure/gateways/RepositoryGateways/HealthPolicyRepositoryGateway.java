package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.HealthPolicyGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.EarthQuakeRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.HealthPolicyRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.PoliciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor

public class HealthPolicyRepositoryGateway implements HealthPolicyGateway {

    private final HealthPolicyRepository healthPolicyRepository;
    private final PoliciesRepository policiesRepository;
    private Specification<HealthPolicy> specification;

    @Override
    public HealthPolicy create(HealthPolicy healthPolicy, Customer customer, PersonalHealth personalHealth) {
        return null;
    }

    @Override
    public HealthPolicy get(HealthPolicy healthPolicy) {
        return null;
    }

    @Override
    public HealthPolicy update(HealthPolicy healthPolicy) {
        return null;
    }

    @Override
    public HealthPolicy delete(HealthPolicy healthPolicy) {
        return null;
    }

    @Override
    public List<HealthPolicy> getList(Specification<HealthPolicy> healthPolicy, int page, int size) {
        return List.of();
    }

    @Override
    public List<HealthPolicy> getCarPoliciesByCustomer(String tckn) {
        return List.of();
    }

    @Override
    public List<HealthPolicy> findByStateAndExpiryDateBefore(PolicyState state, LocalDate currentDate) {
        return List.of();
    }

    @Override
    public HealthPolicy SetStateCarPolicy(HealthPolicy healthPolicy, PolicyState policyState) {
        return null;
    }

    @Override
    public int getTotal() {
        return 0;
    }
}

package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.HealthPolicyGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.HealthPolicy.PersonalHealth;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.HealthPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.Policies;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.HealthPolicyRepository;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.PoliciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

public class HealthPolicyRepositoryGateway implements HealthPolicyGateway {

    private final HealthPolicyRepository healthPolicyRepository;
    private final PoliciesRepository policiesRepository;
    private Specification<HealthPolicy> specification;

    @Override
    public HealthPolicy create(HealthPolicy healthPolicy, Customer customer, PersonalHealth personalHealth) {
        healthPolicy.setCustomer(customer);
        healthPolicy.setPersonalHealth(personalHealth);
        healthPolicy.setState(PolicyState.CREATED);
        System.out.println(healthPolicy.getCoverage());
        if (healthPolicy.getCoverage() == null)
        {
            System.out.println("earthquakePolicy.getCoverage() is NULL");

        }
        Long calculatedId = (healthPolicy.getCoverage().getId() % 100L) == 0 ? 3L : (healthPolicy.getCoverage().getId() % 100L);
        healthPolicy.getCoverage().setId(calculatedId);
        var entity = healthPolicyRepository.save(healthPolicy);
        return entity;
    }

    @Override
    public HealthPolicy get(HealthPolicy healthPolicy) {
        var entityObject = healthPolicyRepository.findById(healthPolicy.getId());
        return entityObject.orElse(null);
    }

    @Override
    public HealthPolicy update(HealthPolicy healthPolicy) {
        var existingCarPolicy = get(healthPolicy);
        if (existingCarPolicy != null) {
            healthPolicy.setCustomer(existingCarPolicy.getCustomer());
            healthPolicy.setPersonalHealth(existingCarPolicy.getPersonalHealth());
            return healthPolicyRepository.save(healthPolicy);
        }
        return null;
    }

    @Override
    public HealthPolicy delete(HealthPolicy healthPolicy) {
        var entityObject = get(healthPolicy);
        if (entityObject != null) {
            healthPolicyRepository.delete(entityObject);
            Optional<Policies> policies = policiesRepository.findById(healthPolicy.getId());
            Policies policies1 = policies.get();
            policiesRepository.delete(policies1);
            return healthPolicy;
        }
        return null;
    }

    @Override
    public List<HealthPolicy> getList(Specification<HealthPolicy> spec, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HealthPolicy> earthquakePolicyPage = healthPolicyRepository.findAll(spec, pageable);
        specification = spec;
        return earthquakePolicyPage.getContent();
    }

    @Override
    public List<HealthPolicy> getCarPoliciesByCustomer(String tckn) {
        return List.of();
    }

    @Override
    public List<HealthPolicy> findByStateAndExpiryDateBefore(PolicyState state, LocalDate currentDate) {
        return  healthPolicyRepository.findByStateAndExpiryDateBefore(state, currentDate);
    }

    @Override
    public HealthPolicy SetStateCarPolicy(HealthPolicy earthquakePolicy, PolicyState policyState) {
        var existingCarPolicy = get(earthquakePolicy);
        if (existingCarPolicy != null) {
            existingCarPolicy.setState(policyState);
            return healthPolicyRepository.save(existingCarPolicy);
        }
        return null;
    }

    @Override
    public int getTotal() {
        return (int) healthPolicyRepository.count(specification);
    }

}

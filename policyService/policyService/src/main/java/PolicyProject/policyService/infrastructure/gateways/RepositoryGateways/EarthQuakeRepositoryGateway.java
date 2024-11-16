package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.EarthQuakeGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.*;

import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.EarthquakePolicy.House;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.Policies;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.EarthQuakeRepository;
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
public class EarthQuakeRepositoryGateway implements EarthQuakeGateway {

    private final EarthQuakeRepository earthQuakeRepository;
    private final PoliciesRepository policiesRepository;
    private Specification<EarthquakePolicy> specification;


    @Override
    public EarthquakePolicy create(EarthquakePolicy earthquakePolicy, Customer customer, House house) {
        earthquakePolicy.setCustomer(customer);
        earthquakePolicy.setHouse(house);
        earthquakePolicy.setState(PolicyState.CREATED);
        System.out.println(earthquakePolicy.getCoverage());
        if (earthquakePolicy.getCoverage() == null)
        {
            System.out.println("earthquakePolicy.getCoverage() is NULL");

        }
        Long calculatedId = (earthquakePolicy.getCoverage().getId() % 100L) == 0 ? 3L : (earthquakePolicy.getCoverage().getId() % 100L);
        earthquakePolicy.getCoverage().setId(calculatedId);
        var entity = earthQuakeRepository.save(earthquakePolicy);
        return entity;
    }

    @Override
    public EarthquakePolicy get(EarthquakePolicy earthquakePolicy) {
        var entityObject = earthQuakeRepository.findById(earthquakePolicy.getId());
        return entityObject.orElse(null);
    }

    @Override
    public EarthquakePolicy update(EarthquakePolicy earthquakePolicy) {
        var existingCarPolicy = get(earthquakePolicy);
        if (existingCarPolicy != null) {
            earthquakePolicy.setCustomer(existingCarPolicy.getCustomer());
            earthquakePolicy.setHouse(existingCarPolicy.getHouse());
            return earthQuakeRepository.save(earthquakePolicy);
        }
        return null;
    }

    @Override
    public EarthquakePolicy delete(EarthquakePolicy earthquakePolicy) {
        var entityObject = get(earthquakePolicy);
        if (entityObject != null) {
           earthQuakeRepository.delete(entityObject);
           Optional<Policies> policies = policiesRepository.findById(earthquakePolicy.getId());
            Policies policies1 = policies.get();
            policiesRepository.delete(policies1);
            return earthquakePolicy;
        }
        return null;
    }

    @Override
    public List<EarthquakePolicy> getList(Specification<EarthquakePolicy> spec, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EarthquakePolicy> earthquakePolicyPage = earthQuakeRepository.findAll(spec, pageable);
        specification = spec;
        return earthquakePolicyPage.getContent();
    }

    @Override
    public List<EarthquakePolicy> getCarPoliciesByCustomer(String tckn) {
        return List.of();
    }

    @Override
    public List<EarthquakePolicy> findByStateAndExpiryDateBefore(PolicyState state, LocalDate currentDate) {
        return  earthQuakeRepository.findByStateAndExpiryDateBefore(state, currentDate);
    }

    @Override
    public EarthquakePolicy SetStateCarPolicy(EarthquakePolicy earthquakePolicy, PolicyState policyState) {
        var existingCarPolicy = get(earthquakePolicy);
        if (existingCarPolicy != null) {
            existingCarPolicy.setState(policyState);
            return earthQuakeRepository.save(existingCarPolicy);
        }
        return null;
    }

    @Override
    public int getTotal() {
        return (int) earthQuakeRepository.count(specification);
    }

}

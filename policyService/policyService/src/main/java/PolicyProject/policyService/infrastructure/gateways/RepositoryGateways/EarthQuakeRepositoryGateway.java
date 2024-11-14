package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.EarthQuakeGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.House;
import PolicyProject.policyService.infrastructure.persistence.repository.EarthQuakeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
public class EarthQuakeRepositoryGateway implements EarthQuakeGateway {

    private final EarthQuakeRepository earthQuakeRepository;
    private Specification<EarthquakePolicy> specification;


    @Override
    public EarthquakePolicy create(EarthquakePolicy earthquakePolicy, Customer customer, House house) {
        earthquakePolicy.setCustomer(customer);
        earthquakePolicy.setHouse(house);
        earthquakePolicy.setState(PolicyState.CREATED);
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
            earthquakePolicy.setCustomer(existingCarPolicy.getCustomer());
            earthquakePolicy.setHouse(existingCarPolicy.getHouse());
            earthquakePolicy.setState(policyState);
            return earthQuakeRepository.save(earthquakePolicy);
        }
        return null;
    }

    @Override
    public int getTotal() {
        return (int) earthQuakeRepository.count(specification);
    }

}

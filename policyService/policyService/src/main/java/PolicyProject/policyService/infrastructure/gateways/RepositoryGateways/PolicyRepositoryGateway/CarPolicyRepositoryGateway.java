package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways.PolicyRepositoryGateway;

import PolicyProject.policyService.application.gateways.PolicyGateway.CarPolicyGateway;
import PolicyProject.policyService.domain.Enums.Enums.PolicyState;
import PolicyProject.policyService.infrastructure.exception.AlreadyExistsException.EarthquakePolicyAlreadyExistsException;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.entity.AuxiliaryEntity.CarPolicy.LicensePlate;
import PolicyProject.policyService.infrastructure.persistence.entity.PolicyEntity.EarthquakePolicy;
import PolicyProject.policyService.infrastructure.persistence.repository.PolicyRepository.CarPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class CarPolicyRepositoryGateway implements CarPolicyGateway
{

    private final CarPolicyRepository carPolicyRepository;
    private Specification<CarPolicy> specification;

    @Override
    //@CachePut(value = "carPolicyCache", key = "#carPolicy.id")
    public CarPolicy create(CarPolicy carPolicy, Customer customer, LicensePlate licensePlate) {
        List<CarPolicy> existingPolicies = carPolicyRepository.findByCustomerAndPolicyEndDateGreaterThanEqualAndPolicyStartDateLessThanEqual(
                customer,
                carPolicy.getPolicyStartDate(),
                carPolicy.getPolicyEndDate()
        );

        if (existingPolicies != null && !existingPolicies.isEmpty()) {
            throw new EarthquakePolicyAlreadyExistsException("Bu tarih aralığında zaten bir poliçe mevcut.", carPolicy.getId());
        }
        carPolicy.setCustomer(customer);
        carPolicy.setLicensePlate(licensePlate);
        carPolicy.setState(PolicyState.CREATED);
        Long calculatedId = (carPolicy.getCoverage().getId() % 100L) == 0 ? 3L : (carPolicy.getCoverage().getId() % 100L);
        carPolicy.getCoverage().setId(calculatedId);
        var entity = carPolicyRepository.save(carPolicy);
        updateTotalCount();
        return entity;
    }

    @Override
    //@Cacheable(value = "carPolicyCache", key = "#carPolicy.id")
    public CarPolicy get(CarPolicy carPolicy) {
        var entityObject = carPolicyRepository.findById(carPolicy.getId());
        return entityObject.orElse(null);
    }

    @Override
    @Transactional
    //@CachePut(value = "carPolicyCache", key = "#carPolicy.id")
    public CarPolicy update(CarPolicy carPolicy) {
        var existingCarPolicy = get(carPolicy);
        if (existingCarPolicy != null) {
            carPolicy.setCustomer(existingCarPolicy.getCustomer());
            carPolicy.setLicensePlate(existingCarPolicy.getLicensePlate());
            return carPolicyRepository.save(carPolicy);
        }
        return null;
    }

    @Override
    //@CacheEvict(value = "carPolicyCache", key = "#carPolicy.id")
    public CarPolicy delete(CarPolicy carPolicy) {
        var entityObject = get(carPolicy);
        if (entityObject != null) {
            carPolicyRepository.delete(entityObject);
            return carPolicy;
        }
        return null;
    }


   // @Cacheable(value = "carPolicyCache", key = "#page + '-' + #size")
    @Override
    public List<CarPolicy> getList(Specification<CarPolicy> spec, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CarPolicy> carPolicyPage = carPolicyRepository.findAll(spec, pageable);
        specification = spec;
        return carPolicyPage.getContent();
    }



    @Override
    public List<CarPolicy> getCarPoliciesByCustomer(String tckn) {
        var PolicyList = carPolicyRepository.findByCustomer_Tckn(tckn).stream().toList();
        if (PolicyList.isEmpty() )
        {
            return null;
        }
       return PolicyList;
    }

    @Override
    public List<CarPolicy> getCarPoliciesByPlateAndTckn(String plate, String tckn) {
        var PolicyList = carPolicyRepository.findByCustomerTcknAndLicensePlatePlate(tckn, plate);
        if (PolicyList.isEmpty() )
        {
            return null;
        }
        return PolicyList;
    }

    @Override
    public List<CarPolicy> getCarPoliciesBetweenDate(LocalDate startDate, LocalDate endDate) {
        var PolicyList = carPolicyRepository.findByPolicyStartDateBetween(startDate, endDate);
        if (PolicyList.isEmpty() )
        {
            return null;
        }
        return PolicyList;
    }

    @Override
    public List<CarPolicy> findByStateAndExpiryDateBefore(PolicyState state, LocalDate currentDate) {
        return  carPolicyRepository.findByStateAndExpiryDateBefore(state, currentDate);
    }

    public int getTotal() {
        return (int) carPolicyRepository.count(specification);
    }

    @Override
    public List<CarPolicy> findByCustomer_Tckn(String tckn) {
        CarPolicy carPolicy = new CarPolicy();
        carPolicy.getId();

        return List.of();
    }

   // @CacheEvict(value = "totalCarPolicies", allEntries = true)
    public void updateTotalCount() {}


    @Override
    public CarPolicy SetStateCarPolicy(CarPolicy carPolicy, PolicyState policyState) {
        var existingCarPolicy = get(carPolicy);
        if (existingCarPolicy != null) {
            existingCarPolicy.setState(policyState);
            return carPolicyRepository.save(existingCarPolicy);
        }
        return null;
    }



}
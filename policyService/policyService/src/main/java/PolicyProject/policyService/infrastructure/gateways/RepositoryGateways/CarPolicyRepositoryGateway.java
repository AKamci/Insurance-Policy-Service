package PolicyProject.policyService.infrastructure.gateways.RepositoryGateways;

import PolicyProject.policyService.application.gateways.CarPolicyGateway;
import PolicyProject.policyService.infrastructure.persistence.entity.Customer;
import PolicyProject.policyService.infrastructure.persistence.entity.CarPolicy;
import PolicyProject.policyService.infrastructure.persistence.repository.CarPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CarPolicyRepositoryGateway implements CarPolicyGateway {

    private final CarPolicyRepository carPolicyRepository;

    @Override
    @CachePut(value = "carPolicyCache", key = "#carPolicy.id")
    public CompletableFuture<CarPolicy> create(CarPolicy carPolicy, double amount, Customer customer) {
        carPolicy.setCustomer(customer);
        carPolicy.setPolicyAmount(amount);
        return CompletableFuture.supplyAsync(() -> {
            CarPolicy savedEntity = carPolicyRepository.save(carPolicy);
            updateTotalCount();
            return savedEntity;
        });
    }

    @Override
    @Cacheable(value = "carPolicyCache", key = "#carPolicy.id")
    public CompletableFuture<CarPolicy> get(CarPolicy carPolicy) {
        return CompletableFuture.supplyAsync(() ->
                carPolicyRepository.findById(carPolicy.getId()).orElse(null)
        );
    }

    @Override
    @CachePut(value = "carPolicyCache", key = "#carPolicy.id")
    public CompletableFuture<CarPolicy> update(CarPolicy carPolicy) {
        return get(carPolicy).thenApply(existingEntity -> {
            if (existingEntity != null) {
                return carPolicyRepository.save(carPolicy);
            }
            return null;
        });
    }

    @Override
    @CacheEvict(value = "carPolicyCache", key = "#carPolicy.id")
    public CompletableFuture<CarPolicy> delete(CarPolicy carPolicy) {
        return get(carPolicy).thenApply(existingEntity -> {
            if (existingEntity != null) {
                carPolicyRepository.delete(carPolicy);
                updateTotalCount();
                return existingEntity;
            }
            return null;
        });
    }

    @Override
    @Cacheable(value = "carPolicyCache", key = "#page + '-' + #size")
    public CompletableFuture<List<CarPolicy>> getList(Specification<CarPolicy> specification, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return CompletableFuture.supplyAsync(() -> {
            Page<CarPolicy> carPolicyPage = carPolicyRepository.findAll(specification, pageable);
            return carPolicyPage.getContent();
        });
    }

    @Override
    public CompletableFuture<List<CarPolicy>> getCarPoliciesByCustomer(String tckn) {
        return CompletableFuture.supplyAsync(() ->
                carPolicyRepository.findByCustomerTckn(tckn)
        );
    }

    @Override
    public CompletableFuture<List<CarPolicy>> getCarPoliciesByPlateAndTckn(String plate, String tckn) {
        return CompletableFuture.supplyAsync(() ->
                carPolicyRepository.findByCustomerTcknAndLicensePlatePlate(tckn, plate)
        );
    }

    @Override
    public CompletableFuture<List<CarPolicy>> getCarPoliciesBetweenDate(LocalDate startDate, LocalDate endDate) {
        return CompletableFuture.supplyAsync(() ->
                carPolicyRepository.findByPolicyStartDateBetween(startDate, endDate)
        );
    }

    @Cacheable("totalCarPolicies")
    public int getTotal() {
        return (int) carPolicyRepository.count();
    }

    @CacheEvict(value = "totalCarPolicies", allEntries = true)
    public CompletableFuture<Void> updateTotalCount() {
        return CompletableFuture.runAsync(() -> {
            // Logic for updating total count if necessary
        });
    }
}
